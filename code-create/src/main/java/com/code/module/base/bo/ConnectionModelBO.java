package com.code.module.base.bo;

import com.code.module.base.dto.BaseDTO;
import lombok.Data;

@Data
public class ConnectionModelBO extends BaseDTO {
    //主机
    private String host;
    //端口
    private String port;
    //数据库名称
    private String databaseName;
    //版本
    private String version;
    //数据库类型
    private String type;
    //数据库引擎
    private String engine;
    //username
    private String username;
    //password
    private String password;
}
