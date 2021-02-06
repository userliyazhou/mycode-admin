package com.code.module.combine.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.code.module.base.bo.TableBO;
import com.code.module.base.bo.java.CreateJavaFileBO;
import com.code.module.base.constants.CodeLanguageEnum;
import com.code.module.base.constants.FileTypeEnum;
import com.code.module.base.constants.MysqlCodeTypeMapEnum;
import com.code.module.base.constants.java.JavaFileTypeEnum;
import com.code.module.base.constants.java.JavaTemplateMapV1;
import com.code.module.base.utils.ConvertStringUtils;
import com.code.module.base.vo.AttributeVO;
import com.code.module.base.vo.ColumnModelVO;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CombineJavaUtils {
    //封装文件内容
    public static List<CreateJavaFileBO> convertJavas(List<TableBO> tableBOS) throws Exception {
        List<CreateJavaFileBO> createJavaFileBOS = new ArrayList<>();
        for (JavaFileTypeEnum javaFileTypeEnum : JavaFileTypeEnum.values()) {
            if(javaFileTypeEnum.getCode().equalsIgnoreCase("unknown")){
                continue;
            }
            for (TableBO tableBO : tableBOS) {
                CreateJavaFileBO createJavaFileBO = new CreateJavaFileBO();
                String tableName = tableBO.getTableName();
                String className = ConvertStringUtils.convertBigHump(tableName);
                //类名
                createJavaFileBO.setClassName(className);
                createJavaFileBO.setClassNameSuffix(javaFileTypeEnum.getClassSuffix());
                //java类型
                createJavaFileBO.setJavaFileTypeEnum(javaFileTypeEnum);
//                包名
                createJavaFileBO.setPackageName(tableBO.getPackagePath() + javaFileTypeEnum.getRelationPackagePath());
                //模板名称
                createJavaFileBO.setTemplateName(JavaTemplateMapV1.getValueByCode(javaFileTypeEnum.getCode()));
                //语言
                createJavaFileBO.setCodeLanguageEnum(CodeLanguageEnum.JAVA);
                String filePath = tableBO.getBaseFilePath() + javaFileTypeEnum.getRelationPath() + className + javaFileTypeEnum.getClassSuffix() + "." + javaFileTypeEnum.getFileSuffix();
                boolean empty = FileUtil.isEmpty(new File(filePath));
                if(!empty){
                    throw new Exception("文件已经存在，请检查。"+filePath);
                }
                //文件类型
                createJavaFileBO.setFileTypeEnum(FileTypeEnum.file);
                createJavaFileBO.setFilePath(filePath);
                if (javaFileTypeEnum.getCode().equalsIgnoreCase("entity")) {
                    createJavaFileBO.setClassNameSuffix(javaFileTypeEnum.getClassSuffix());
                    //封装实体内容
                    List<ColumnModelVO> columnModelVOS = tableBO.getColumnModelVOS();
                    List<AttributeVO> attributeVOS = new ArrayList<>();
                    if (CollectionUtil.isNotEmpty(columnModelVOS)) {
                        for (ColumnModelVO columnModelVO : columnModelVOS) {
                            AttributeVO attributeVO = new AttributeVO();
                            //属性类型
                            String data_type = columnModelVO.getDATA_TYPE();
                            attributeVO.setAttrType(MysqlCodeTypeMapEnum.getToType(StringUtils.lowerCase(data_type)));
                            //属性名称
                            String column_name = columnModelVO.getCOLUMN_NAME();
                            String columnName = ConvertStringUtils.convertSmallHump(column_name);
                            attributeVO.setAttrValue(columnName);
                            attributeVO.setAttrComment(columnModelVO.getCOLUMN_COMMENT());
                            attributeVOS.add(attributeVO);
                        }
                    }
                    createJavaFileBO.setAttributeVOS(attributeVOS);
                } else if (javaFileTypeEnum.getCode().equalsIgnoreCase("mapper_xml")) {
                    //包名+className+suffix
                    String nameSpace = tableBO.getPackagePath() + javaFileTypeEnum.getRelationPackagePath() + className;
                    createJavaFileBO.setNameSpace(nameSpace);
                } else if (javaFileTypeEnum.getCode().equalsIgnoreCase("api")) {
                    createJavaFileBO.setRootPathUrl(StrUtil.lowerFirst(className));
                } else if (javaFileTypeEnum.getCode().equalsIgnoreCase("controller")) {
                    createJavaFileBO.setRootPathUrl(StrUtil.lowerFirst(className));
                }
                createJavaFileBOS.add(createJavaFileBO);
            }
        }
        return createJavaFileBOS;

    }

}
