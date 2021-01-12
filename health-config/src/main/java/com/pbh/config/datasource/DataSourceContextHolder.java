package com.pbh.config.datasource;

/**
 * Package com.pbh.config.datasource
 * ClassName DataSourceContextHolder.java
 * Description
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-10-23 10:03
 **/
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new InheritableThreadLocal<>();

    /**
     *  设置数据源
     * @param db
     */
    public static void setDataSource(String db){
        contextHolder.set(db);
    }

    /**
     * 取得当前数据源
     * @return
     */
    public static String getDataSource(){
        return contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clear(){
        contextHolder.remove();
    }

}
