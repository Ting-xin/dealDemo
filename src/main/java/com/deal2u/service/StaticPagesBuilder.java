package com.deal2u.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileWriter;

/**
 * @ClassName StaticPagesBuilder
 * @Description 生成静态页面
 * @Author zhngzhng
 * @Date 2022/7/21
 **/
@Service
public class StaticPagesBuilder {
    public void brandPageBuilder() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        String servicePath = getServicePath();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        Context context = new Context();
        String htmlPath = servicePath + "/staticPage";
        File temp = new File(htmlPath);
        if (!temp.exists()) temp.mkdirs();
        String htmlFile = htmlPath + "/" + ".html";
        try {
            FileWriter write = new FileWriter(htmlFile);
            templateEngine.process("", context, write);
            write.flush();
            write.close();
        }catch (Exception e) {

        }

    }

    private String getServicePath() {
        String servicePath = System.getProperty("user.dir") + "/src/main/resources/templates";

        return servicePath.replaceAll("\\\\", "/");
    }
}
