package com.code.module.base.vo;

import com.code.module.base.constants.FileTypeEnum;
import com.code.module.base.constants.java.JavaFileTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class CreatCodeVO extends BaseVO {
    private String saveFilePath;
    private String packageName;
    private String templateName;
    private String urlName;
    private FileTypeEnum fileType;//1java 2 xml 3yml
    private JavaFileTypeEnum javaType;//1mapper 2service 3serviceImpl 4controller
    private String className;
    private List<AttributeVO> attributeVOS;
    private String mapper;//mapperxml
    private String nameSpace;//xml namespace


}
