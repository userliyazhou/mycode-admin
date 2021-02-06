package com.code.utils;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import com.code.module.base.bo.TemplateLoaderBO;
import com.code.module.base.bo.java.CreateJavaFileBO;
import com.code.utils.templates.LoadTemplateLocation;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

@Slf4j
public class FreeMarkUtils {
    private static Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

    //获取Configuration
    public static Configuration handlerConfiguration(List<TemplateLoaderBO> templateLoaderBOS) throws Exception {
        if (CollectionUtil.isEmpty(templateLoaderBOS)) {
            throw new Exception("模板不能为null");
        }
        TemplateLoader[] loaders = new TemplateLoader[templateLoaderBOS.size()];
        for (int i = 0; i < templateLoaderBOS.size(); i++) {
            TemplateLoaderBO templateLoaderBO = templateLoaderBOS.get(i);
            if (templateLoaderBO.getLoaderType() == 1) {
                //绝对路径
                FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(templateLoaderBO.getTemplateDirPath()));
                loaders[i] = fileTemplateLoader;
            } else if (templateLoaderBO.getLoaderType() == 2) {
                //根据FreeMarkUtils类的相对路径
                ClassTemplateLoader fileTemplateLoader = new ClassTemplateLoader(LoadTemplateLocation.class, templateLoaderBO.getTemplateDirPath());
                loaders[i] = fileTemplateLoader;

            } else {
                throw new Exception("模板类型不存在");
            }
        }

        MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);

        cfg.setTemplateLoader(mtl);
        // 设置对象包装器
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        // 设置异常处理器
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }


    //生成java语言项目文件
    public static void writeJavaProjectCode(List<CreateJavaFileBO> createJavaFileBOS, List<TemplateLoaderBO> templateLoaderBOS) throws Exception {
        Configuration configuration = handlerConfiguration(templateLoaderBOS);
        for (CreateJavaFileBO createJavaFileBO : createJavaFileBOS) {
            BufferedWriter out = null;
            String filePath = createJavaFileBO.getFilePath();
            File touch = FileUtil.touch(filePath);
            Template temp = configuration.getTemplate(createJavaFileBO.getTemplateName());
            if(temp==null){
                continue;
            }
            out = new BufferedWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(touch))));
            temp.process(createJavaFileBO, out);
            out.close();
        }
    }
//    public static  void main(String[] s) throws Exception {
//        ArrayList<CreateJavaFileBO> createJavaFileBOS = new ArrayList<>();
//        CreateJavaFileBO createJavaFileBO = new CreateJavaFileBO();
//        createJavaFileBO.setFileDirPath("D://123/");
//        createJavaFileBO.setFileName("89899");
//        createJavaFileBO.setFileSuffix(".java");
//        createJavaFileBO.setPackageName("com.demo");
//        createJavaFileBO.setCodeLanguageEnum(CodeLanguageEnum.JAVA);
//        createJavaFileBO.setTemplateName("java/demo.ftl");
//        createJavaFileBO.setName("jjjjj");
//        createJavaFileBO.setNameSuffix("ioioio");
//        createJavaFileBOS.add(createJavaFileBO);
//        ArrayList<TemplateLoaderBO> templateLoaderBOS = new ArrayList<>();
//        TemplateLoaderBO templateLoaderBO = new TemplateLoaderBO();
//        templateLoaderBO.setLoaderType(2);
//        templateLoaderBO.setTemplateDirPath("");
//        templateLoaderBOS.add(templateLoaderBO);
//        writeJavaProjectCode(createJavaFileBOS,templateLoaderBOS);
//    }


}