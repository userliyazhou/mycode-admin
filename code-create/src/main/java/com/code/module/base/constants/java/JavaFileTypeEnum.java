package com.code.module.base.constants.java;


import java.io.File;

public enum JavaFileTypeEnum {
    UNKNOWN("unknown", "unknown", "unknown", "unknown", "unknow"),
    MAPPER("mapper", "java", "Mapper", File.separator + "mapper" + File.separator, ".mapper"),
    MAPPER_XML("mapper_xml", "xml", "", File.separator + "mapper" + File.separator + "xml" + File.separator, ""),
    SERVICE("service", "java", "Service", File.separator + "service" + File.separator, ".service"),
    SERVICE_IMPL("serviceImpl", "java", "ServiceImpl", File.separator + "service" + File.separator + "impl" + File.separator, ".service.impl"),
    CONTROLLER("controller", "java", "Controller", File.separator + "controller" + File.separator, ".controller"),
    ENTITY("entity", "java", "", File.separator + "entity" + File.separator, ".entity"),
    API("api", "java", "Api", File.separator + "api" + File.separator, ".api"),
    API_IMPL("apiImpl", "java", "ApiImpl", File.separator + "api" + File.separator + "impl" + File.separator, ".api.impl");
    private String code;
    private String fileSuffix;
    private String classSuffix;
    private String relationPath;
    private String relationPackagePath;

    public String getRelationPackagePath() {
        return relationPackagePath;
    }

    public void setRelationPackagePath(String relationPackagePath) {
        this.relationPackagePath = relationPackagePath;
    }

    public String getRelationPath() {
        return relationPath;
    }

    public void setRelationPath(String relationPath) {
        this.relationPath = relationPath;
    }

    public String getClassSuffix() {
        return classSuffix;
    }

    public void setClassSuffix(String classSuffix) {
        this.classSuffix = classSuffix;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    JavaFileTypeEnum(String code, String fileSuffix, String classSuffix, String relationPath, String relationPackagePath) {
        this.code = code;
        this.fileSuffix = fileSuffix;
        this.classSuffix = classSuffix;
        this.relationPath = relationPath;
        this.relationPackagePath = relationPackagePath;
    }

    public static String getFileSuffixByCode(String code) {
        for (JavaFileTypeEnum javaFileTypeEnum : JavaFileTypeEnum.values()) {
            if (code.equalsIgnoreCase(javaFileTypeEnum.code)) {
                return javaFileTypeEnum.fileSuffix;
            }
        }
        return UNKNOWN.fileSuffix;
    }

    public static String getClassSuffixByCode(String code) {
        for (JavaFileTypeEnum javaFileTypeEnum : JavaFileTypeEnum.values()) {
            if (code.equalsIgnoreCase(javaFileTypeEnum.code)) {
                return javaFileTypeEnum.classSuffix;
            }
        }
        return UNKNOWN.classSuffix;
    }
}
