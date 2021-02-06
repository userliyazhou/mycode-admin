package com.code.module.connection.service;

import com.code.module.base.bo.SaveFilePostionBO;
import com.code.module.base.bo.TableBO;
import com.code.module.base.bo.TemplateLoaderBO;
import com.code.module.base.bo.java.CreateJavaFileBO;
import com.code.module.base.dto.java.CreateJavaDTO;
import com.code.module.base.utils.ConnectUtils;
import com.code.module.base.utils.ResultToPojoUtils;
import com.code.module.base.vo.ColumnModelVO;
import com.code.module.base.vo.TableModelVO;
import com.code.module.create.utils.TablesUtils;
import com.code.module.project.service.JavaProjectService;
import com.code.utils.FreeMarkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TablesService {
    @Autowired
    private JavaProjectService javaProjectService;

    public List<TableModelVO> getTables(CreateJavaDTO createJavaDTO) {
        String databaseName = createJavaDTO.getDatabaseName();
        Connection mysqlConnection = null;
        try {
            mysqlConnection = ConnectUtils.getMysqlConnection(createJavaDTO);
            String sql = "SELECT * FROM information_schema.tables WHERE table_schema=?";
            PreparedStatement pst = mysqlConnection.prepareStatement(sql);
            pst.setString(1, databaseName);
            ResultSet rs = pst.executeQuery();
            List<TableModelVO> tableModelVOS = new ResultToPojoUtils<TableModelVO>().convertToPojo(rs, TableModelVO.class);
            return tableModelVOS;
        } catch (Exception e) {
            log.info("查询表失败");
            return null;
        } finally {
            try {
                mysqlConnection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<ColumnModelVO> getColumns(CreateJavaDTO createJavaDTO, String tableName) {
        String databaseName = createJavaDTO.getDatabaseName();
        Connection mysqlConnection = null;
        try {
            mysqlConnection = ConnectUtils.getMysqlConnection(createJavaDTO);
            String sql = "SELECT * FROM information_schema.columns WHERE table_schema=? and table_name = ?";
            PreparedStatement pst = mysqlConnection.prepareStatement(sql);
            pst.setString(1, databaseName);
            pst.setString(2, tableName);
            ResultSet rs = pst.executeQuery();
            List<ColumnModelVO> tableModelVOS = new ResultToPojoUtils<ColumnModelVO>().convertToPojo(rs, ColumnModelVO.class);
            return tableModelVOS;
        } catch (Exception e) {
            log.info("查询表失败");
            return null;
        } finally {
            try {
                mysqlConnection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public String creatCode(CreateJavaDTO createJavaDTO) throws Exception {
        //获取表结构
        List<TableBO> tableBOS = TablesUtils.getTableBOS(createJavaDTO);
        SaveFilePostionBO saveFilePostionBO = new SaveFilePostionBO();
        saveFilePostionBO.setBaseFilePath(createJavaDTO.getBaseFilePath());
        saveFilePostionBO.setPackagePath(createJavaDTO.getPackageName());
        //组装对象
        List<CreateJavaFileBO> createJavaFileBOS = javaProjectService.combineCreateJavaFileBO(tableBOS, saveFilePostionBO);
        List<TemplateLoaderBO> templateLoaderBOS = new ArrayList<>();
        TemplateLoaderBO templateLoaderBO = new TemplateLoaderBO();
        templateLoaderBO.setTemplateDirPath("java");
        templateLoaderBO.setLoaderType(2);
        templateLoaderBOS.add(templateLoaderBO);
        FreeMarkUtils.writeJavaProjectCode(createJavaFileBOS, templateLoaderBOS);
        return "代码生成成功。路径" + createJavaDTO.getBaseFilePath();
    }

}