package com.code.common.utils;

import cn.hutool.json.JSONArray;
import com.alibaba.fastjson.JSON;
import com.code.common.dto.MysqlConnectDTO;
import com.code.common.vo.TableModelVO;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class ResultToPojoUtils<T> {
    private List<T> convertToPojo(Connection conn,String  sql, Class clazz) throws Exception {
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<T> outputList = new ArrayList<>();
        ResultSetMetaData metaData = rs.getMetaData(); //每一行有多少列，每一列的列名称 rs.getObject获取的是对应行对应列对应的值
        int colCount = metaData.getColumnCount(); // 取得所有列的个数
        Field[] fields = clazz.getDeclaredFields(); // 取得业务对象,name 就是属性key，
        while (rs.next()) {
            //每一行记录，每一列遍历一次
            T pojo = (T) clazz.newInstance(); // 每一行就是一个pojo实例
            for(int col=0;col<colCount;col++){
                //取出列key
                String columnKey=metaData.getColumnName(col+1);
                //取出列vule
                String columnValue=(rs.getObject(col+1) == null) ? "" : rs.getObject(col+1).toString();
                for(int pojoNum=0;pojoNum<fields.length;pojoNum++){
                    //取出pojo属性key
                    Field pojoField=fields[pojoNum];
                    String pojoKey=pojoField.getName();
                    if(columnKey.equals(pojoKey)){
                        //如果columnKey和pojoKey相等，赋值进去
                  pojoField.setAccessible(true);
                  pojoField.set(pojo,columnValue);
                        break;
                    }
                }
            }
            outputList.add(pojo);
        }
        System.out.println(JSON.toJSONString(outputList));
        return outputList;
    }

    public static void main(String[] args) throws Exception {
        MysqlConnectDTO mysqlConnectDTO = new MysqlConnectDTO();
        mysqlConnectDTO.setUrl("jdbc:mysql://localhost:3306/fastdp-uc?useUnicode=true&characterEncoding=utf-8&&serverTimezone=Asia/Shanghai");
        mysqlConnectDTO.setDrive("com.mysql.cj.jdbc.Driver");
        mysqlConnectDTO.setUsername("root");
        mysqlConnectDTO.setPassword("root");
        Connection mysqlConnection = new CreateConnectUtils().getMysqlConnection(mysqlConnectDTO);
        String sql = "SELECT * FROM information_schema.tables WHERE table_schema='fastdp-uc'";
        PreparedStatement pst = mysqlConnection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<TableModelVO> tableModelVOS = new ResultToPojoUtils<TableModelVO>().convertToPojo(mysqlConnection,sql, TableModelVO.class);
    }
}
