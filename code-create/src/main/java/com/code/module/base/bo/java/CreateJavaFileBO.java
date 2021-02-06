package com.code.module.base.bo.java;

import com.code.module.base.bo.FileBO;
import com.code.module.base.constants.java.JavaFileTypeEnum;
import com.code.module.base.vo.AttributeVO;
import lombok.Data;

import java.util.List;

@Data
public class CreateJavaFileBO extends FileBO {
    //生成不同java项目的文件类型
    private JavaFileTypeEnum javaFileTypeEnum;
    //包名
    private String packageName;
    //名称主体
    private String className;
    //名称后缀
    private String classNameSuffix;
    //实体属性 entity
    private List<AttributeVO> attributeVOS;
    //namaspace
    private String nameSpace;
    //controller  pathUrl
    private String rootPathUrl;

}
