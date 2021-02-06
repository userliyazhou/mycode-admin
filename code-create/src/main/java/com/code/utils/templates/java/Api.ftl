package ${packageName};
import com.yueyun.framework.vo.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
/**
* @Description:代码生成器生成
* @Author: 代码生成器开发者李亚洲
* @Date:   2021-01-14
* @Version: V1.0
*/
@FeignClient(name = ApiConstant.APPLICATION_NAME,url = "${'$'}{yueyun.gateway.url:}",path=ApiConstant.SERVLET_CONTEXT_PATH)
@RequestMapping("/${rootPathUrl}")
public interface ${className}${classNameSuffix} {
}