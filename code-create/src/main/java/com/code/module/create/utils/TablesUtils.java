package com.code.module.create.utils;

import com.code.module.base.bo.TableBO;
import com.code.module.base.dto.java.CreateJavaDTO;
import com.code.module.base.utils.ConnectUtils;
import com.code.module.base.utils.ResultToPojoUtils;
import com.code.module.base.vo.ColumnModelVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TablesUtils {
    public static List<TableBO> getTableBOS(CreateJavaDTO createJavaDTO) throws Exception {
        List<TableBO> tableBOS = new ArrayList<>();
        List<String> tableNames = createJavaDTO.getTableNames();
        String databaseName = createJavaDTO.getDatabaseName();
        Connection mysqlConnection = ConnectUtils.getMysqlConnection(createJavaDTO);
        for (String tableName : tableNames) {
            mysqlConnection = ConnectUtils.getMysqlConnection(createJavaDTO);
            String sql = "SELECT * FROM information_schema.columns WHERE table_schema=? and table_name = ?";
            PreparedStatement pst = mysqlConnection.prepareStatement(sql);
            pst.setString(1, databaseName);
            pst.setString(2, tableName);
            ResultSet rs = pst.executeQuery();
            List<ColumnModelVO> tableModelVOS = new ResultToPojoUtils<ColumnModelVO>().convertToPojo(rs, ColumnModelVO.class);
            TableBO tableBO = new TableBO();
            tableBO.setColumnModelVOS(tableModelVOS);
            tableBO.setTableName(tableName);
            tableBO.setBaseFilePath(createJavaDTO.getBaseFilePath());
            tableBO.setPackagePath(createJavaDTO.getPackageName());
            tableBOS.add(tableBO);
        }
        return tableBOS;
    }
}
