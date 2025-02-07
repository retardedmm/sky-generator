package com.sky.cli.example;

import picocli.CommandLine;

@CommandLine.Command(name = "SubCommandExample", version = "SubCommandExample 1.0", mixinStandardHelpOptions = true)
public class SubCommandExample implements Runnable {

    public static void main(String[] args) {
        String[] myArgs=new String[]{"--help"};
        new CommandLine(new SubCommandExample())
                .addSubcommand(new add())
                .addSubcommand(new query())
                .execute(myArgs);
    }

    @Override
    public void run() {
        System.out.println("主命令");
    }

    @CommandLine.Command(name = "add" ,description = "添加", mixinStandardHelpOptions = true)
    static class add implements Runnable{

        @Override
        public void run() {
            System.out.println("添加");
        }
    }

    @CommandLine.Command(name = "query" ,description = "查询", mixinStandardHelpOptions = true)
    static class query implements Runnable{

        @Override
        public void run() {
            System.out.println("查询");
        }
    }

}
