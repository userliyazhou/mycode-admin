package com.code.module.base.dto.java;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreateJavaDTO {
    @ApiModelProperty(name = "链接数据库url", value = "链接数据库url")
    private String url;
    @ApiModelProperty(name = "数据驱动", value = "数据驱动默认com.mysql.jdbc.Driver")
    private String drive = "com.mysql.jdbc.Driver";
    @ApiModelProperty(name = "链接数据库用户名", value = "链接数据用户名默认root")
    private String username = "root";
    @ApiModelProperty(name = "链接数据密码", value = "链接数据库密码默认root")
    private String password = "root";
    @ApiModelProperty(name = "使用那个数据库", value = "使用数据库名称")
    private String databaseName;
    @ApiModelProperty(name = "mysql版本", value = "mysql版本默认5.7")
    private String version = "5.7";
    @ApiModelProperty(name = "根据那些表生成代码", value = "根据那些表生成代码")
    private List<String> tableNames;
    @ApiModelProperty(name = "生成代码存放位置", value = "生成代码存放位置")
    private String baseFilePath;
    @ApiModelProperty(name = "包名", value = "包名，默认com.yueyun.create")
    private String packageName = "com.yueyun.create";
}
