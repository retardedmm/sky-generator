package com.sky.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

public class Login implements Callable<Integer> {
    @Option(names = {"-u", "--user"}, description = "User name")
    String user;

    @Option(names = {"-p", "--密码"}, description = "Passphrase",arity = "0..1", interactive = true , prompt = "请输入密码")
    String password;

    @Option(names = {"-cp","--确认密码"},description = "checkPassword",interactive = true , prompt = "确认密码")
    String checkPassword;

    public Integer call() throws Exception {
        System.out.println("password = " + password);
        System.out.println("checkPassword = " + checkPassword);
        if(password==null || "".equals(password)){
            return 0;
        }else {
            return 1;
        }
    }

    public static void main(String[] args) {
        boolean b=true;
        while(b){
            int res = new CommandLine(new Login()).execute("-u", "user123", "-p",  "-cp");
            b=res==0;
        }

    }
}
