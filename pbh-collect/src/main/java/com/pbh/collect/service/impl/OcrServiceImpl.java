package com.pbh.collect.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pbh.collect.service.OcrService;
import com.pbh.common.utils.AipImageClassifyUtils;
import com.pbh.common.utils.ImageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Package com.pbh.collect.service.impl
 * ClassName OcrServiceImpl.java
 * Description 图像识别接口实现
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-01-11 15:21
 **/
@Slf4j
@Service
public class OcrServiceImpl implements OcrService {

    /**
     * appId
     */
    private String appId = "15116711";

    /**
     * apiKey
     */
    public String apiKey = "DqMGIAK0rAjcPAM6mCKYSMHX";
    /**
     * secretKey
     */
    public String secretKey = "xmdY0B92GVG4xyjzPxxA1Rr5jgGBqsd5";

    /**
     * @see OcrService#takePictureInformation(MultipartFile)
     */
    @Override
    public Map<String,Object> takePictureInformation(MultipartFile file) throws Exception {
        return ocrAipImageParse(ocrAipImage(file));
    }

    public Map<String,Object> ocrAipImageParse(String sb) throws Exception {

        Map<String, Object> map = new HashMap<>(20);

        if (StringUtils.isEmpty(sb)) {
            return null;
        }

        JSONArray a = JSONArray.parseArray(sb);
        for (int i = 0; i < a.size(); i++) {
            JSONObject obj = (JSONObject) a.get(i);
            if ("户外跑".equals(obj.getString("words"))) {
                map.put("runningTime", a.getJSONObject(i + 2).getString("words"));
            }

            if ("公里".equals(obj.getString("words"))) {
                map.put("kilometre", a.getJSONObject(i - 1).getString("words"));
                map.put("consumeTime", a.getJSONObject(i + 1).getString("words"));
                map.put("pace", a.getJSONObject(i + 2).getString("words"));
                map.put("calorie", a.getJSONObject(i + 3).getString("words"));
            }

            if ("平均心率".equals(obj.getString("words"))) {
                map.put("heartRate", a.getJSONObject(i - 3).getString("words"));
                map.put("strideRate", a.getJSONObject(i - 2).getString("words"));
                map.put("stride", a.getJSONObject(i - 1).getString("words"));
            }

        }
        return map;
    }

    private String ocrAipImage(MultipartFile file) throws Exception {
        AipImageClassifyUtils client = new AipImageClassifyUtils(appId, apiKey, secretKey);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        String base64Content = ImageUtils.getImageStr(file.getInputStream());
        org.json.JSONObject res = client.plantDetect(base64Content, new HashMap<String, String>());
        if (org.springframework.util.StringUtils.isEmpty(res)) {
            return "";
        }
        if (!res.has("words_result")) {
            return "";
        }
        System.out.println(res.toString());
        return res.get("words_result").toString();
    }

}
