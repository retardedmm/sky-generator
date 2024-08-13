package com.sky.generator;

import com.sky.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 动静结合生成java文件
 */
public class MainGenerator {
    public static void main(String[] args) {
        String rootPath = System.getProperty("user.dir");
        String inputPathStatic=rootPath+ File.separator+"sky-generator-demo-projects"+File.separator+"acm-template";
        String outputPathStatic=rootPath;
        //递归方法复制文件--静态复制
        StaticGenerator.copyFile(inputPathStatic,outputPathStatic);

        String inputPathDynamic=rootPath+"/sky-generator-basic/src/main/resources/templates/MainTemplate.java.ftl";
        String outputPathDynamic=rootPath+"/acm-template/src/com/sky/acm/MainTemplate.java";
        MainTemplateConfig mainTemplateConfig=new MainTemplateConfig();
        try {
            //Freemarker模板塞值--动态生成
            DynamicGenerator.doGenerator(inputPathDynamic,outputPathDynamic,mainTemplateConfig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
