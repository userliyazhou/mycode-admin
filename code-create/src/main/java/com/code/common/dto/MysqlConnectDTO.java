package com.code.common.dto;

import lombok.Data;

@Data
public class MysqlConnectDTO {
    private  String url;
    private  String drive;
    private  String  username;
    private  String password;
    private  String databaseName;
    private String version;
}
