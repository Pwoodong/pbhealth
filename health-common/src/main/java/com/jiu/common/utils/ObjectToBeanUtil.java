package com.jiu.common.utils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Package com.jiu.common.utils
 * ClassName ObjectToBeanUtil.java
 * Description 对象转bean工具类
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-12-15 下午11:28
 **/
public class ObjectToBeanUtil {

    /**
     * 把List<Object[]>转换成List<T>
     */
    public static <T> List<T> objectToBean(List<Object> objList, Class<T> clz) throws Exception{
        if (objList==null || objList.size()==0) {
            return null;
        }

        Class<?>[] cz = null;
        Constructor<?>[] cons = clz.getConstructors();
        for (Constructor<?> ct : cons) {
            Class<?>[] clazz = ct.getParameterTypes();
            if (objList.size() == clazz.length) {
                cz = clazz;
                break;
            }
        }

        List<T> list = new ArrayList<T>();
        for (Object obj : objList) {
            Constructor<T> cr = clz.getConstructor(cz);
            list.add(cr.newInstance(obj));
        }
        return list;
    }

}

