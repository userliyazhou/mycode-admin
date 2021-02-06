package com.code.module.connection.controller;

import com.code.module.base.dto.java.CreateJavaDTO;
import com.code.module.connection.service.TablesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("根据数据库表生成代码")
@RestController
@RequestMapping("/code/tables")
public class JavaTablesController {
    @Autowired
    private TablesService tablesService;

    //根据表生成代码
    @PostMapping("/creatCode")
    public String creatCode(@RequestBody CreateJavaDTO createJavaDTO)  {
String result="";
        try {
            result =   tablesService.creatCode(createJavaDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return result;
    }
}
