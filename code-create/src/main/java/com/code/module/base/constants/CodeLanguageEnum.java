package com.code.module.base.constants;


public enum CodeLanguageEnum {
    JAVA("java", "java"),
    UNKNOWN("unknown", "unknown");

    private String code;
    private String value;

    CodeLanguageEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValueByCode(String code) {
        for (CodeLanguageEnum codeLanguageEnum : CodeLanguageEnum.values()) {
            if (code.equalsIgnoreCase(codeLanguageEnum.code)) {
                return codeLanguageEnum.value;
            }
        }
        return UNKNOWN.value;
    }
}
