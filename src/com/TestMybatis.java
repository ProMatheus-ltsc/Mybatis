package com;

import org.mybatis.configuration.XmlConfigParser;

import java.io.InputStream;

public class TestMybatis {
    public static void main(String[] args) throws Throwable{
        //加载mybatis-config.xml
        ClassLoader classLoader = TestMybatis.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("mybatis-config.xml");
        //调用配置模块解析xml
        XmlConfigParser.parser(inputStream);


    }
}
