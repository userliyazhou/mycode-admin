package ${packageName};
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
* @Description:代码生成器生成
* @Author: 代码生成器开发者李亚洲
* @Date:   2021-01-14
* @Version: V1.0
*/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="${className}", description="${className}")
public class ${className} implements Serializable {
private static final long serialVersionUID = 1L;

<#list attributeVOS as attributeVO>
    <#if attributeVO.attrType == "String">
        <#if attributeVO.attrComment?length gt 1>
            @ApiModelProperty(value = "${attributeVO.attrComment}")
        </#if>
        private ${attributeVO.attrType} ${attributeVO.attrValue};
    <#elseif attributeVO.attrType == "Integer">
        <#if attributeVO.attrComment?length gt 1>
            @ApiModelProperty(value = "${attributeVO.attrComment}")
        </#if>
        private ${attributeVO.attrType} ${attributeVO.attrValue};
    <#elseif attributeVO.attrType == "Date">
        <#if attributeVO.attrComment?length gt 1>
            @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @ApiModelProperty(value = "${attributeVO.attrComment}")
        </#if>
        private ${attributeVO.attrType} ${attributeVO.attrValue};
    <#elseif attributeVO.attrType == "Float">
        private ${attributeVO.attrType} ${attributeVO.attrValue};
    <#elseif attributeVO.attrType == "Double">
        private ${attributeVO.attrType} ${attributeVO.attrValue};
    </#if>
</#list>


}