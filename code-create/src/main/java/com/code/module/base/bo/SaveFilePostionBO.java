package com.code.module.base.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SaveFilePostionBO {
    @ApiModelProperty(name = "生成代码存放位置", value = "生成代码存放位置")
    private String baseFilePath;
    @ApiModelProperty(name = "包名", value = "包名，默认com.yueyun.create")
    private String packagePath = "com.yueyun.create";
}
