package com.code.module.base.constants;

public enum FileTypeEnum {
    Dir("dir", "文件夹"),
    file("file", "文件");

    private String code;
    private String value;

    FileTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

}
