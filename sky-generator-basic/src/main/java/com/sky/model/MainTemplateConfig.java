package com.sky.model;

import lombok.Data;

/**
 * 模板配置类
 * 设置默认值防止用户不传入，或者在传入地方做判空处理
 */
@Data
public class MainTemplateConfig {

    //作者
    private String author="zhangtianlin";

    //输出文本
    private String outputText="SUM:";

    //是否加入循环代码
    private Boolean loop=false;

}
