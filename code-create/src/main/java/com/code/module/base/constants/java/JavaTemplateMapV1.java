package com.code.module.base.constants.java;

public enum JavaTemplateMapV1 {
    UNKNOWN("unknown", "unknown"),
    MAPPER("mapper", "Mapper.ftl"),
    MAPPER_XML("mapper_xml", "MapperXml.ftl"),
    SERVICE("service", "Service.ftl"),
    SERVICE_IMPL("serviceImpl", "Service.ftl"),
    CONTROLLER("controller", "Controller.ftl"),
    ENTITY("entity", "Entity.ftl"),
    API("api", "Api.ftl"),
    API_IMPL("apiImpl", "ApiImpl.ftl");;
    private String code;
    private String value;

    JavaTemplateMapV1(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValueByCode(String code) {
        for (JavaTemplateMapV1 javaTemplateMapV1 : JavaTemplateMapV1.values()) {
            if (code.equalsIgnoreCase(javaTemplateMapV1.code)) {
                return javaTemplateMapV1.value;
            }
        }
        return UNKNOWN.value;
    }
}
