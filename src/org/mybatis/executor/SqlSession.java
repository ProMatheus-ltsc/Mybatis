package org.mybatis.executor;

//创建接口的代理对象, 重用代码最好用代理

import java.lang.reflect.Proxy;

public class SqlSession {
    /**
     * @param clazz UserMapper接口的类对象
     * @return 代理对象
     * */
    public Object getMapper(Class clazz){
        ClassLoader classLoader = clazz.getClassLoader();
        /*
        * class $Proxy$ implements UserMapper{
        *       findAll(){invocationHandler.invoke()};
        * }*/
        Class[] interfaces = {clazz};
        //clazz指向UserMapper.class
        MapperProxy mapperProxy = new MapperProxy();
        Object proxy = Proxy.newProxyInstance(classLoader,interfaces,mapperProxy);


        return proxy;
    }

}
