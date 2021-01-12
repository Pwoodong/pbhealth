package com.pbh.common.utils;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Package com.pbh.common.utils
 * ClassName ObjectTransformUtil.java
 * Description 对象转换工具类
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-15 19:03
 **/
@Slf4j
public class ObjectTransformUtil {

    /**
     * List<Map<String, Object>> 到 List<T> 数据转换
     */
    public static <T> List<T> setList(List<Map<String, Object>> srcList, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        srcList.forEach(x -> {
            try {
                T t = clazz.newInstance();
                Field[] fields = t.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (!"serialVersionUID".equals(field.getName())) {
                        //设置对象的访问权限，保证对private的属性的访问
                        field.setAccessible(true);
                        //读取配置转换字段名，并从map中取出数据
                        Object v = x.get(field.getName());
                        field.set(t, convert(v, field.getType()));
                    }
                }
                list.add(t);
            } catch (Exception ex) {
                log.error(ex.toString());
            }
        });
        return list;
    }

    /**
     * Field类型转换
     */
    private static <T> T convert(Object obj, Class<T> type) {
        if (obj != null && StringUtils.isNotBlank(obj.toString())) {
            if (type.equals(String.class)) {
                return (T) obj.toString();
            } else if (type.equals(BigDecimal.class)) {
                return (T) new BigDecimal(obj.toString());
            }
            //其他类型转换......
        }
        return null;
    }

}
