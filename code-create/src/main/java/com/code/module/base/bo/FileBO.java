package com.code.module.base.bo;

import com.code.module.base.constants.CodeLanguageEnum;
import com.code.module.base.constants.FileTypeEnum;
import lombok.Data;

@Data
public class FileBO {
    //编程语言
    private CodeLanguageEnum codeLanguageEnum;
    //0文件夹 1 文件
    private FileTypeEnum fileTypeEnum;
    //模板名称
    private String templateName;

    //生成文件 文件夹路径
    private String filePath;
}
