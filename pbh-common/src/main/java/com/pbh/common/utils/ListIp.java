package com.pbh.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Package com.pbh.common.utils
 * ClassName ListIp.java
 * Description 外网IP
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-03-02 上午12:07
 **/
public class ListIp {

    public static String getWebIp(String strUrl) {
        try {
            URL url = new URL(strUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            StringBuffer sb = new StringBuffer("");
            String webContent = "";
            while ((s = br.readLine()) != null) {
                sb.append(s + "\r\n");
            }
            br.close();
            webContent = sb.toString();
            int start = webContent.indexOf("<h2>") + 4;
            int end = webContent.indexOf("</h2>");
            System.out.println("webContent=" + webContent);
            System.out.println("start=" + start);
            System.out.println("end=" + end);
            if (start < 0 || end < 0) {
                return null;
            }
            webContent = webContent.substring(start, end);
            return webContent;
        } catch (Exception e) {
            e.printStackTrace();
            return "error open url:" + strUrl;
        }
    }

    public static void main(String[] args) {
        String ip = getWebIp("http://www.net.cn/static/customercare/yourip.asp");
        System.out.println("public ip is :" + ip);
    }

}
