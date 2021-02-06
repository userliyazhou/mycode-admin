package com.code.module.base.constants;

public enum FileSuffixEnum {
    DIR("dir", "文件夹"),
    FILE("file", "文件");

    private String code;
    private String value;

    FileSuffixEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

}
