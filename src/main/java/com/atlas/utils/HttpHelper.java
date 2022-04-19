package com.atlas.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class HttpHelper {

    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        ServletInputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("all")
    public static Map<String, Object> getBodyMap(ServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        String bodyString = getBodyString(request);
        if (StrUtil.isNotEmpty(bodyString)) {
            params = JSON.parseObject(bodyString, Map.class);
        }
        return params;
    }

}


