package com.jiu.common.utils;

import org.json.JSONObject;
import com.baidu.aip.http.AipRequest;
import com.baidu.aip.imageclassify.AipImageClassify;

import java.util.HashMap;

/**
 * Package com.jiu.common.utils
 * ClassName AipImageClassifyUtils.java
 * Description
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2021-01-11 15:14
 **/
public class AipImageClassifyUtils extends AipImageClassify {

    public AipImageClassifyUtils(String appId, String apiKey, String secretKey) {
        super(appId, apiKey, secretKey);
    }

    @Override
    public JSONObject plantDetect(String base64Content, HashMap<String, String> options) {
        AipRequest request = new AipRequest();
        this.preOperation(request);
        request.addBody("image", base64Content);
        if (options != null) {
            request.addBody(options);
        }
        request.setUri("https://aip.baidubce.com/rest/2.0/ocr/v1/webimage");
        this.postOperation(request);
        return this.requestServer(request);
    }

}
