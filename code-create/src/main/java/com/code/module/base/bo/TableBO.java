package com.code.module.base.bo;

import com.code.module.base.vo.ColumnModelVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TableBO {
    private String tableName;
    private List<ColumnModelVO> columnModelVOS;
    @ApiModelProperty(name = "生成代码存放位置", value = "生成代码存放位置")
    private String baseFilePath;
    @ApiModelProperty(name = "包名", value = "包名，默认com.yueyun.create")
    private String packagePath = "com.yueyun.create";

}
