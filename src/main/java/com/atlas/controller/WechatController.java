package com.atlas.controller;

import com.atlas.entity.User;
import com.atlas.service.Impl.UserServiceImpl;
import com.atlas.service.WechatService;
import com.atlas.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WechatController {
    @Autowired
    WechatService wc;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/cis")
    public Map<String,String> getcode(@RequestParam(value = "code")String  code){
        System.out.println(code);
        String uid=wc.codetoopenid(code);
        Map<String,String> map=new HashMap<>();
        User user1=new User(uid,null);
        User user=userService.find(user1);
        System.out.println(user1);
        System.out.println(user);
        try{
            String token= JWTUtils.getToken(uid);
            map.put("token",token);
            user1.setToken(token);
            System.out.println(user1);
            if(user==null){
                userService.insert(user1);
            }else {
                userService.update(user1);
            }
        }catch (Exception e){
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
