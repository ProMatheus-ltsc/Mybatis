# MyBatis

## 分析

### 结构

```
表user
id, username
1, admin
2, root
```

```
class User
Integer id
String username
```

``` 
interface UserMapper{
List<User> findAll()
}
```

```
UserMapper.xml
<select id=com.UserMapper.findAll, resultType=User>
select * from User
</select>
```

```
mybatis-config.xml
driver, url, username, password
<mappers>
	<mapper resource=UserMapper.xml>
```



![](https://cdn.nlark.com/yuque/__puml/5a50395097bbe41efd93f52d8a149762.svg)

## 伪代码

## 准备工作

创建表, 创建moudle, 添加mysql驱动, dom4j依赖.
## 建包

```java

package com

import javafx.beans.property.Property;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.util.List;

class User {
    Integer id;
    String username;
}

interface UserMapper {
    List<user> findAll();
}

class TestMybatis() {
    InputStream inputStream  = ClassLoader.getResourceAsStream("mybatis-config.xml");
            XmlConfigParser.parser();
    proxy =sqlSession.getMapper(UserMapper .class);
    list<User> =proxy.findAll();
}
    UserMapper.xml
            <select id = com.UserMapper.findAll resultType=com.user>
        select*from user
        mybatis-config.xml
<property name="driver"value="">
<mappers>
<mapper resource=userMapper.xml>
</mappers>
        package org.mybatis.configuaration

class SqlMapper {
    String id, sql, ResultType;
}

class Configuration {
    String driver, url, username, password;
    HashMap<id, sqlMapper> sqlMappers;
}

class XmlConfigParser {
    parser(InputStream inputStream) {
        使用dom4j解析mybatis - config.xml和userMapper.xml;
        document = SAXReader.read(inputStream);
        List<Element> element = document.selectNodes(Property);
        for(element){
            //property name = driver value = com
            //name = url value = jdbc:mysql//localhost:3306
            //name = username value = root
            valueOfName = element.attributeValue(name);
            valueOfValue = element.attributeValue(value);
            switch(valueOfName){
                case "driver":
                    configuration.setDriver();
                    break;
                case "url":
                    configuration.seturl(valueOfValue);
                case "username":
                    
                    
            }
        }
    }
}
        
package org.mybatis.executor

class sqlSession {
    clazz =UserMapper .

    class
    Object getMapper(

    class clazz)

    {

    }

    class MapperProxy implements InvocationHandler {
        invoke() {
            1. 从XmlConfigParser.configuration中取出url, 连接数据库;
            2. 找到sql, resultSet, list < User >;
        }
    }
}
```
## 实现

Java中都是类

### 配置模块

```
class SqlMapper----------对应xml
String id
String sql
String resultType
```

```
class Configuration------对应配置类
String driver, url, username, password
HashMap<id, sqlMapper> sqlMappers
```

```
class XmlConfigParser--------对xml进行解析
static configuration
```



### 运行SQL语句模块

```
class SqlSession{
Object getMapper(class clazz)//返回的是一个代理对象Object

}
```

```
class TestMybatis{
proxy = sqlSession.getMapper(UserMapper.class)
list<User>=proxy.findAll()
}
```

```
class MapperProxy implements InvocationHandler{ //代理对象实现类
invoke(method){
1. 得到方法名finAll, 得到类名com.UserMapper
 com.UserMapper.findAll
2. XmlConfigParser.configuration有url, driver, username. password, sqlMappers
3. 连接上mysql
4. 得到sqlMapper{select * from user, com.User}
5. 结果集
6. 把每一行数据放到user
7. 再把user放到list
8. return list<User>
}

}
```

### 具体实现
1. 解析xml--------saxReader读取文件
2. 动态代理
3. 封装JDBC




