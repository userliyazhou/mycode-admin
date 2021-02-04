package code;

import com.CodeApplication;

import com.code.utils.templates.LoadTemplateLocation;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(classes = CodeApplication.class)
public class test {
    @Test
    public void test1(){
        System.out.println("单员测试");
    }
    @Test
    public void test2() throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        ClassTemplateLoader fileTemplateLoader = new ClassTemplateLoader(LoadTemplateLocation.class,"java");
        configuration.setTemplateLoader(fileTemplateLoader);
        configuration.getTemplate("demo.ftl","UTF-8");
    }
}
