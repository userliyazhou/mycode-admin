package com.code.module.base.model;

import lombok.Data;

@Data
public class ColumnModel {
    private String TABLE_CATALOG;
    private String TABLE_SCHEMA;
    private String TABLE_NAME;
    private String COLUMN_NAME;
    private String ORDINAL_POSITION;
    private String COLUMN_DEFAULT;
    private String IS_NULLABLE;
    private String DATA_TYPE;
    private String CHARACTER_MAXIMUM_LENGTH;
    private String CHARACTER_OCTET_LENGTH;
    private String NUMERIC_PRECISION;
    private String NUMERIC_SCALE;
    private String DATETIME_PRECISION;
    private String CHARACTER_SET_NAME;
    private String COLLATION_NAME;
    private String COLUMN_TYPE;
    private String COLUMN_KEY;
    private String EXTRA;
    private String PRIVILEGES;
    private String COLUMN_COMMENT;
}
