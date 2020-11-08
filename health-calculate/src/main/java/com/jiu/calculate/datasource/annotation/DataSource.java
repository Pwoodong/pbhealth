package com.jiu.calculate.datasource.annotation;

import com.jiu.calculate.datasource.DataSourceEnum;

import java.lang.annotation.*;

/**
 * Package com.jiu.bigscreen.datasource
 * ClassName DataSource.java
 * Description
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-10-23 10:05
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    DataSourceEnum value() default DataSourceEnum.DB1;

}
