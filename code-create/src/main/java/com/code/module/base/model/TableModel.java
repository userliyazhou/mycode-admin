package com.code.module.base.model;

import lombok.Data;

@Data
public class TableModel {
    private String TABLE_CATALOG;//数据表登记目录
    private String TABLE_SCHEMA; //数据表所属的数据库名
    private String TABLE_NAME;//表名称
    private String TABLE_TYPE;//表类型[system view|base table]
    private String ENGINE;//使用的数据库引擎[MyISAM|CSV|InnoDB]
    private String VERSION;//版本，默认值10
    private String ROW_FORMAT;//行格式[Compact|Dynamic|Fixed]
    private String TABLE_ROWS;//表里所存多少行数据
    private String AVG_ROW_LENGTH;//平均行长度
    private String DATA_LENGTH;//数据长度
    private String MAX_DATA_LENGTH;//最大数据长度
    private String INDEX_LENGTH;//索引长度
    private String DATA_FREE;//空间碎片
    private String AUTO_INCREMENT;//做自增主键的自动增量当前值
    private String CREATE_TIME;//表的创建时间
    private String UPDATE_TIME;//表的更新时间
    private String CHECK_TIME;//表的检查时间
    private String TABLE_COLLATION;//表的字符校验编码集
    private String CHECKSUM;//校验和
    private String CREATE_OPTIONS;//创建选项
    private String TABLE_COMMENT;//表的注释、备注


}
