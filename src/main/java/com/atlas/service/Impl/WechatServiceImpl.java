package com.atlas.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atlas.service.WechatService;
import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WechatServiceImpl implements WechatService {
    @Override
    public String codetoopenid(String code) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("appid", "wx7ebaa80049d72200");
        data.put("secret", "aaa69aea7e5cd27c783d226709f76b36");
        data.put("js_code", code);
        data.put("grant_type", "authorization_code");

        String response = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session").form(data).body();
        System.out.println("Response was: " + response);
        int begin=response.indexOf("openid");
        String uid=response.substring(begin+9,response.length()-2);
        String uid1=response.substring(begin+7,response.length());
        System.out.println(uid1);
        System.out.println(uid);
        JSONObject obj= JSON.parseObject(response);//将json字符串转换为json对
        System.out.println(obj);
        return uid;
    }
}
