package org.mybatis.executor;

import com.User;
import org.mybatis.configuration.Configuaration;
import org.mybatis.configuration.SqlMapper;
import org.mybatis.configuration.XmlConfigParser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;

//mybatis最核心的东西
public class MapperProxy implements InvocationHandler {
    /**
     * Processes a method invocation on a proxy instance and returns
     * the result.  This method will be invoked on an invocation handler
     * when a method is invoked on a proxy instance that it is
     * associated with.
     *
     * @param proxy  the proxy instance that the method was invoked on
     * @param method the {@code Method} instance corresponding to
     *               the interface method invoked on the proxy instance.  The declaring
     *               class of the {@code Method} object will be the interface that
     *               the method was declared in, which may be a superinterface of the
     *               proxy interface that the proxy class inherits the method through.
     * @param args   an array of objects containing the values of the
     *               arguments passed in the method invocation on the proxy instance,
     *               or {@code null} if interface method takes no arguments.
     *               Arguments of primitive types are wrapped in instances of the
     *               appropriate primitive wrapper class, such as
     *               {@code java.lang.Integer} or {@code java.lang.Boolean}.
     * @return the value to return from the method invocation on the
     * proxy instance.  If the declared return type of the interface
     * method is a primitive type, then the value returned by
     * this method must be an instance of the corresponding primitive
     * wrapper class; otherwise, it must be a type assignable to the
     * declared return type.  If the value returned by this method is
     * {@code null} and the interface method's return type is
     * primitive, then a {@code NullPointerException} will be
     * thrown by the method invocation on the proxy instance.  If the
     * value returned by this method is otherwise not compatible with
     * the interface method's declared return type as described above,
     * a {@code ClassCastException} will be thrown by the method
     * invocation on the proxy instance.
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        System.out.println("invoke 方法名=" + methodName);
        //得到方法所在接口的接口名 com.UserMapper
        String interfaceName = method.getDeclaringClass().getName();

        //拼出id com.UserMapper.findAll
        String id = interfaceName + "." + methodName;
        //从XmlConfigParser中得到Configuration
        Configuaration configuaration = XmlConfigParser.configuaration;
        //得到driver
        String driver = configuaration.getDriver();
        //得到url
        String url = configuaration.getUrl();
        //连接数据库的用户名
        String username = configuaration.getUsername();
        //连接数据库的密码
        String password = configuaration.getPassword();
        //得到UserMapper.xml中的<select>
        SqlMapper sqlMapper = configuaration.getSqlMappers().get(id);
        if(sqlMapper == null){
            return null;

        }
        String sql = sqlMapper.getSql();
        String resultType = sqlMapper.getResultType();

        //连接数据库
        //加载驱动
        Class.forName(driver);


        //得到连接
        Connection connection = DriverManager.getConnection(url,username,password);
        System.out.println(connection);

        //执行sql
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(resultSet);

        //将结果集转成list
        //集合不指定放的数据的类型, 可以放任何对象
        ArrayList userList = new ArrayList<>();

        int row = 0;
        while(resultSet.next()){
            row++;
            //创建对象
            Class clazz = Class.forName(resultType);
            Object object = clazz.newInstance();
            //把某个列的值放到对应的属性上
            //表id, username两个列
            //对象is, username属性上
            //得到更的列的信息
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            //遍历列
            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                String columnName = metaData.getColumnName(columnIndex);
                System.out.println("columnName=" + columnName);
                //创建属性field
                Field field = clazz.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(object,resultSet.getObject(columnName));
            }
            userList.add(object);
        }
        return userList;
    }
}
