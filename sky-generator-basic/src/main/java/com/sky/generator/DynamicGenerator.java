package com.sky.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 动态代码生成
 */
public class DynamicGenerator {

    public static void main(String[] args) throws Exception{


    }

    public static void doGenerator(String inputPath,String outputPath,Object model) throws IOException, TemplateException {
        //加载配置类里的内容
        // Create your Configuration instance, and specify if up to what FreeMarker
        // version (here 2.3.22) do you want to apply the fixes that are not 100%
        // backward-compatible. See the Configuration JavaDoc for details.
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

        // Specify the source where the template files come from. Here I set a
        // plain directory for it, but non-file-system sources are possible too:

        String parentPath = new File(inputPath).getParent();
        File parentFile=new File(parentPath);
        //模板文件路径
        cfg.setDirectoryForTemplateLoading(parentFile);

        // Set the preferred charset template files are stored in. UTF-8 is
        // a good choice in most applications:
        cfg.setDefaultEncoding("UTF-8");

        //为什么 FreeMarker 打印奇怪的数字格式 (比如 1,000,000 或 1 000 000 而不是 1000000)--摘自中文官方文档中的FQA模块
        cfg.setNumberFormat("0.######");  // now it will print 1000000
        // where cfg is a freemarker.template.Configuration object

        // Sets how errors will appear.
        // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        //模板文件
        File inputFile=new File(inputPath);
        Template temp = cfg.getTemplate(inputFile.getName());

        Writer out = new FileWriter(outputPath);
        temp.process(model, out);

        out.close();
    }
}
