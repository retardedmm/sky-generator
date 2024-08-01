package com.sky.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class StaticGenerator {
    public static void main(String[] args) {
        String rootPath = System.getProperty("user.dir");
        String inputPath=rootPath+ File.separator+"sky-generator-demo-projects"+File.separator+"acm-template";
        String outputPath=rootPath;
        //调用Hutool工具类复制文件
        //copyFileByHutool(inputPath,outputPath);
        //调用自我编写手动递归方法复制文件
        copyFile(inputPath,outputPath);
    }

    /**
     * 调用Hutool工具类复制文件
     * @param inputPath 源文件路径
     * @param outputPath 目标文件路径
     */
    public static void copyFileByHutool(String inputPath , String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }

    public static void copyFile(String inputPath , String outputPath){
        //判断输入路径是否为空
        if(inputPath==null){
            throw new NullPointerException("输入路径为空");
        }
        File inputFile = new File(inputPath);
        //判断该文件是否存在
        if(!inputFile.exists()){
            throw new RuntimeException("该文件不存在");
        }
        //判断输入路径与输出路径是否一致
        if(inputPath.equals(outputPath)){
            return;
        }
        File outputFile = new File(outputPath+File.separator+inputFile.getName());
        if(inputFile.isDirectory()){
            copyDirectory(inputFile,outputFile);
        }else{
            copyFile(inputFile,outputFile);
        }

    }
    public static void copyDirectory(File inputFile,File outputFile){
        //如果不存在则创建目录
        if(!outputFile.exists()){
            outputFile.mkdirs();
        }
        String[] childFiles = inputFile.list();
        for(String childFile:childFiles){
            File srcFile = new File(inputFile, childFile);
            File destFile = new File(outputFile, childFile);
            if(srcFile.isDirectory()){
                copyDirectory(srcFile,destFile);
            }else{
                copyFile(srcFile,destFile);
            }
        }
    }

    public static void copyFile(File inputFile,File outputFile){
        //输出路径不存在则创建路径
        if(!outputFile.exists()){
            outputFile.getParentFile().mkdirs();
        }
        try {
            Files.copy(inputFile.toPath(),outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
