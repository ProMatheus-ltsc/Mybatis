package org.mybatis.configuration;

//封装<select>中的信息
public class SqlMapper {
    String id;
    String sql;
    String resultType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    @Override
    public String toString() {
        return "SqlMapper{" +
                "id='" + id + '\'' +
                ", sql='" + sql + '\'' +
                ", resultType='" + resultType + '\'' +
                '}';
    }
}
