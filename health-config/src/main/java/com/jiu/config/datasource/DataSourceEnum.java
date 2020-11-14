package com.jiu.config.datasource;

/**
 * Package com.jiu.config.datasource
 * ClassName DataSourceEnum.java
 * Description
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-10-23 10:05
 **/
public enum DataSourceEnum {

    DB1("db1"),DB2("db2");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }

}
