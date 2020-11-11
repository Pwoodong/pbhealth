package com.jiu.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Package com.jiu.common.utils
 * ClassName HttpClientUtils.java
 * Description Http工具
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-08 16:26
 **/
@Slf4j
public class HttpClientUtils {

    /**
     * POST请求
     *
     * @param requestUrl  请求URL
     * @param requestData 请求参数
     * @return 请求结果
     */
    public static String post(String requestUrl, String requestData) {
        OutputStream out = null;
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            out =connection.getOutputStream();
            out.write(requestData.getBytes());
            out.flush();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                log.error("Http请求失败失败码：" + responseCode);
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (MalformedURLException e) {
            log.error("请求URL格式错误", e);
        } catch (IOException e) {
            log.error("http请求失败", e);
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(reader);
        }
        return result.toString();
    }

    /**
     * 电子运维服务单POST请求
     *
     * @param requestUrl  请求URL
     * @param requestData 请求参数
     * @return 请求结果
     */
    public static String postService(String requestUrl, String requestData) {
        OutputStream out = null;
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            connection.setRequestProperty("SOAPAction", "http://tempuri.org/AddMyToReadList");
            out =connection.getOutputStream();
            out.write(requestData.getBytes());
            out.flush();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                log.error("Http请求失败失败码：" + responseCode);
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (MalformedURLException e) {
            log.error("请求URL格式错误", e);
        } catch (IOException e) {
            log.error("http请求失败", e);
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(reader);
        }
        return result.toString();
    }
}
