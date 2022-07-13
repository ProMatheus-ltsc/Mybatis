package com;

import org.mybatis.configuration.XmlConfigParser;
import org.mybatis.executor.SqlSession;

import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    public static void main(String[] args) throws Throwable{
        //加载mybatis-config.xml
        ClassLoader classLoader = TestMybatis.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("mybatis-config.xml");
        //调用配置模块解析xml
        XmlConfigParser.parser(inputStream);
        //调用mybatis运行模块, 得到接口的代理对象
        SqlSession sqlSession = new SqlSession();
        Object proxy = sqlSession.getMapper(UserMapper.class);
        UserMapper userMapper = (UserMapper) proxy;
        List<User> list = userMapper.findAll();
        //调用mapperProxy.invoke()
        System.out.println(list);




    }
}
