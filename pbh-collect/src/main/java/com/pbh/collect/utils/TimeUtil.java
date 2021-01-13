package com.pbh.collect.utils;

import org.springframework.util.StringUtils;

/**
 * Package com.pbh.collect.utils
 * ClassName TimeUtil.java
 * Description 时间处理工具类
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-01-13 下午9:26
 **/
public class TimeUtil {

    /**
     *  中文时间格式处理
     *  1、根据日进行截取
     *  2、然后对下午进行截取，对出现下午字段对小时+12操作
     *  3、对新值重新转换
     * */
    public static String handleDateTimeByCn(String cnTime){
        if(StringUtils.isEmpty(cnTime)){
            return "";
        }

        if(!cnTime.contains("日")){
            return "";
        }
        String s0 = cnTime.split("日")[0];
        String s1 = cnTime.split("日")[1];
        String s22 = s1;
        if(s1.contains("下午")){
            String hms = s1.split("下午")[1];
            Integer s21 = Integer.valueOf(hms.split(":")[0]) + 12;
            if( hms.split(":").length <= 2){
                s22 = s21 + ":" + hms.split(":")[1] + ":00";
            }else {
                s22 = s21 + ":" + hms.split(":")[1] + ":" + hms.split(":")[2];
            }
        }
        String s30 = s0.replace("年","-").replace("月","-").replace("日","") + " " + s22;
        return s30;
    }

    public static void main(String[] args) {
        String dateTimeStr = "2020年12月20日2:11:07";
        String result = handleDateTimeByCn(dateTimeStr);
        System.out.println(result);
    }

}
