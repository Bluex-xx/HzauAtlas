package com.atlas.controller;

import com.atlas.entity.Cat;
import com.atlas.entity.Codea;
import com.atlas.entity.Flower;
import com.atlas.entity.User;
import com.atlas.service.Impl.CatServiceImpl;
import com.atlas.service.Impl.FlowerServiceImpl;
import com.atlas.service.Impl.UserServiceImpl;
import com.atlas.service.WechatService;
import com.atlas.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cis")
public class WechatController {
    @Autowired
    WechatService wc;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private FlowerServiceImpl flowerService;
    @Autowired
    private CatServiceImpl catService;

    @ResponseBody
    @PostMapping("/login")
    public Map<String,String> getcode(@RequestBody Codea codea){
        String code= codea.getCode();;
        //System.out.println(code);
        String uid=wc.codetoopenid(code);
        Map<String,String> map=new HashMap<>();
        User user1=new User(uid,null);
        User user=userService.find(user1);
        //System.out.println(user1);
        //System.out.println(user);
        List<Cat> catList= catService.recommend();
        List<Flower> flowerList= flowerService.recommend();
        String key1="cat";
        String key2="flower";
        ListOperations<String,Cat> catListOperations= redisTemplate.opsForList();
        ListOperations<String,Flower> flowerListOperations= redisTemplate.opsForList();
        catListOperations.leftPushAll(key1,catList);
        flowerListOperations.leftPushAll(key2,flowerList);

        try{
            if(uid==null){
                map.put("msg","code has been used");
            }else{
                String token= JWTUtils.getToken(uid);
                map.put("token",token);
                user1.setToken(token);
                System.out.println(user1);
                if(user==null){
                    userService.insert(user1);
                }else {
                    userService.update(user1);
                }
            }
        }catch (Exception e){
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
