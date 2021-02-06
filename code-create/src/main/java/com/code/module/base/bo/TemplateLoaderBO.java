package com.code.module.base.bo;

import lombok.Data;

@Data
public class TemplateLoaderBO {
    //加载方式  1 使用文件系统加载，2 使用类相对路径加载
    private Integer loaderType;
    private String templateDirPath;
}
