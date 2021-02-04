package com.code.module.base.utils;

import com.code.module.base.dto.java.CreateJavaDTO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectUtils {

    public static  Connection getMysqlConnection(CreateJavaDTO createJavaDTO) throws Exception {
        String url =createJavaDTO.getUrl();
        String username=createJavaDTO.getUsername();
        String password=createJavaDTO.getPassword();
        String drive=createJavaDTO.getDrive();
        Class.forName(drive);
        Connection  conn= DriverManager.getConnection(url, username, password);
        return  conn;
    }

}
