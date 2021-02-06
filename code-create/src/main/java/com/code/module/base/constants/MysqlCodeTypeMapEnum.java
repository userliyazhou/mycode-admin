package com.code.module.base.constants;

public enum MysqlCodeTypeMapEnum {
    UNKNOWN("unknown", "unknown"),

    INT("int", "Integer"),
    TINYINT("tinyint", "Integer"),
    SMALLINT("smallint", "Integer"),
    MEDIUMINT("mediumint", "Integer"),
    BIGINT("bigint", "Integer"),
    FLOAT("float", "Float"),
    DOUBLE("double", "Double"),
    DECIMAL("Decimal", "Float"),
    DATETIME("datetime", "Date"),
    TIMESTAMP("timestamp", "Date"),
    DATE("date", "Date"),
    TIME("time", "Date"),
    YEAR("year", "Date"),
    CHAR("char", "String"),
    VARCHAR("varchar", "String"),
    TEXT("text", "String"),
    BLOB("blob", "String"),
    ENUM("enum", "String"),
    SET("set", "String");
    public String fromType;
    public String toType;

    MysqlCodeTypeMapEnum(String fromType, String toType) {
        this.fromType = fromType;
        this.toType = toType;
    }

    public static String getToType(String fromType) {
        for (MysqlCodeTypeMapEnum mysqlCodeTypeMapEnum : MysqlCodeTypeMapEnum.values()) {
            if (mysqlCodeTypeMapEnum.fromType.equalsIgnoreCase(fromType)) {
                return mysqlCodeTypeMapEnum.toType;
            }
        }
        return UNKNOWN.toType;
    }
}
