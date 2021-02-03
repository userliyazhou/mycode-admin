package com.code.module.base.vo;

import lombok.Data;

@Data
public class ColumnModelVO {

    private String TABLE_SCHEMA;
    private String TABLE_NAME;
    private String COLUMN_NAME;
    private String IS_NULLABLE;
    private String DATA_TYPE;
    private String COLUMN_TYPE;
    private String COLUMN_COMMENT;
}
