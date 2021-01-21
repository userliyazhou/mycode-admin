package com.code.common.vo;
import lombok.Data;

@Data
public class TableModelVO {
    private String TABLE_SCHEMA; //数据表所属的数据库名
    private String TABLE_NAME;//表名称
    private String TABLE_TYPE;//表类型[system view|base table]
    private String TABLE_COMMENT;//表的注释、备注
}
