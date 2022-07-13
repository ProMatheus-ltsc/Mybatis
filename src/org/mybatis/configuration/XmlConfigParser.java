package org.mybatis.configuration;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
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

        System.out.println(configuaration);

    }

}
