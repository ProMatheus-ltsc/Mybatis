package org.mybatis.configuration;

import java.util.HashMap;

//方mybatis-config.xml中的信息
public class Configuaration {
    String driver,url,username,password;
    HashMap<String,SqlMapper> sqlMappers;

    @Override
    public String toString() {
        return "Configuaration{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sqlMappers=" + sqlMappers +
                '}';
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<String, SqlMapper> getSqlMappers() {
        return sqlMappers;
    }

    public void setSqlMappers(HashMap<String, SqlMapper> sqlMappers) {
        this.sqlMappers = sqlMappers;
    }
}
