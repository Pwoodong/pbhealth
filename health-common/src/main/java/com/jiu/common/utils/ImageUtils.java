package com.jiu.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * Package com.jiu.common.utils
 * ClassName ImageUtils.java
 * Description 图片处理工具类
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-01-11 15:13
 **/
public class ImageUtils {

    /**
     * 图片转换字节数组字符串
     * 1、将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @param in
     * @return java.lang.String
     */
    public static String getImageStr(InputStream in) {
        // 读取图片字节数组
        byte[] data = null;
        try {
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        return Base64.getEncoder().encodeToString(data);
    }

}
