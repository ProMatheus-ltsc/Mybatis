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



