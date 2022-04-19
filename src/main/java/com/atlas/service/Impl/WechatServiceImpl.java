package com.atlas.service.Impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.atlas.service.WechatService;
import com.github.kevinsawicki.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class WechatServiceImpl implements WechatService {
    @Override
    public String codetoopenid(String code) {
        ParserConfig.getGlobalInstance().setSafeMode(true);
        Map<String, String> data = new HashMap<String, String>();
        data.put("appid", "wx7ebaa80049d72200");
        data.put("secret", "aaa69aea7e5cd27c783d226709f76b36");
        data.put("js_code", code);
        data.put("grant_type", "authorization_code");

        String appid= "wx7ebaa80049d72200";
        String secret="aaa69aea7e5cd27c783d226709f76b36";
        String js_code=code;
        String grant_type="authorization_code";

        String url="https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + js_code + "&grant_type=" + grant_type;

        String response = HttpUtil.createGet(url)
                .execute()
                .charset("utf-8")
                .body();;
        System.out.println("Response was: " + response);
        int begin = response.indexOf("openid");
        String openid = null;
        if (begin == -1) {
            return openid;
        } else {
            openid = response.substring(begin + 9, response.length() - 2);
            //System.out.println("openid"+openid);
        }
        //JSONObject obj = JSON.parseObject(response);//将json字符串转换为json对
        //System.out.println(obj);
        return openid;
    }
}

