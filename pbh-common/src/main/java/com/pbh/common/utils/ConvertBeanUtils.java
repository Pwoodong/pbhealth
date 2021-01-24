package com.pbh.common.utils;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Package com.pbh.common.utils
 * ClassName ConvertUtils.java
 * Description 实体转换工具类
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-01-24 下午5:03
 **/
@Slf4j
public class ConvertBeanUtils {

    public static <T, U> U translate(T t, Class<U> uClass, String message) {
        try {
            return BeanUtil.toBean(t, uClass);
        } catch (Exception e) {
            log.error(message+"出现异常."+e.getMessage());
            return null;
        }
    }

}
