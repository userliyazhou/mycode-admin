package com.code.common.utils;

import com.code.common.dto.MysqlConnectDTO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectUtils {

    public static  Connection getMysqlConnection(MysqlConnectDTO mysqlConnectDTO) throws Exception {
        String url =mysqlConnectDTO.getUrl();
        String username=mysqlConnectDTO.getUsername();
        String password=mysqlConnectDTO.getPassword();
        String drive=mysqlConnectDTO.getDrive();
        Class.forName(drive);
        Connection  conn= DriverManager.getConnection(url, username, password);
        return  conn;
    }

}
