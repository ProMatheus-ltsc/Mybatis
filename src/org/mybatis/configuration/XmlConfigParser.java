package org.mybatis.configuration;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

//解析mybatis-config.xml和userMapper.xml
public class XmlConfigParser {
    public static Configuaration configuaration = new Configuaration();
    public static void parser(InputStream inputStream) throws Throwable{

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);

        //读取4个property标签
        List<Element> elementList = document.selectNodes("//property");
        for(Element element : elementList){
            //取name属性的值
            String valueOfName = element.attributeValue("name");

            //取value属性的值
            String valueOfValue = element.attributeValue("value");
            switch (valueOfName){
                case "driver":
                    configuaration.setDriver(valueOfValue);
                    break;
                case "url":
                    configuaration.setUrl(valueOfValue);
                    break;
                case "username":
                    configuaration.setUsername(valueOfValue);
                    break;
                case "password":
                    configuaration.setPassword(valueOfValue);
                    break;
            }


        }
        //解析userMapper.xml
        //得到所有的mapper标签
        List<Element> mapperElementList = document.selectNodes("//mapper");
        HashMap<String,SqlMapper> sqlMappers = new HashMap<>();
        configuaration.setSqlMappers(sqlMappers);
        for(Element element : mapperElementList){
            String mapperFileName = element.attributeValue("resource");
            parserMapper(mapperFileName);
        }
        System.out.println(configuaration);

    }

    /**
     * 解析UserMapper.xml
     * @param mapperFileName
     * */
    private static void parserMapper(String mapperFileName) throws Throwable {

        //得到类加载器
        ClassLoader classLoader = XmlConfigParser.class.getClassLoader();

        //得到inputStream
        InputStream inputStream = classLoader.getResourceAsStream(mapperFileName);

        //创建saxreader
        SAXReader saxReader = new SAXReader();
        //读xml得到document
        Document document = saxReader.read(inputStream);

        //找到所有select标签
        List<Element> selectElementList = document.selectNodes("//select");

        //遍历所有select标签
        for(Element element : selectElementList){
            //取到select中的id
            String id = element.attributeValue("id");
            //取到sql
            String sql = element.getText();
            //取到select中的resultType
            String resultType = element.attributeValue("resultType");
            //创建sqlMapper
            SqlMapper sqlMapper = new SqlMapper();
            sqlMapper.setId(id);
            sqlMapper.setSql(sql);
            sqlMapper.setResultType(resultType);
            //放到configuration.sqlMappers中
            configuaration.getSqlMappers().put(id,sqlMapper);

        }

    }

}
