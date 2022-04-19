package com.atlas.controller;

import com.atlas.entity.Cat;
import com.atlas.entity.Flower;
import com.atlas.entity.User;
import com.atlas.service.Impl.CatServiceImpl;
import com.atlas.service.Impl.FlowerServiceImpl;
import com.atlas.service.Impl.UserServiceImpl;
import com.atlas.service.WechatService;
import com.atlas.utils.JWTUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.security.auth.kerberos.KerberosTicket;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public Map<String,Object> getcode(@RequestBody User user){
        String code= user.getCode();;
        System.out.println(user);
        //System.out.println(code);
        String openid=wc.codetoopenid(code);
        //System.out.println(uid);
        Map<String,Object> map=new HashMap<>();
        User user1=new User(openid,user.getProfile_photo(),user.getName(),null,null);
        try{
            if(openid==null){
               map.put("msg","code has been used");
            }else{
                //String token= JWTUtils.getToken(uid);
                //String token= DigestUtils.sha256Hex(uid);
                //System.out.println(token);
                //map.put("token",token);
                //user1.setToken(token);
                //System.out.println(user1);
                User user2=userService.find(user1);
                if(user2==null){
                    int uid=userService.insert(user1);
                    user1.setUid(uid);
                    map.put("user",user1);
                }else{
                    userService.update(user1);
                    user1.setUid(user2.getUid());
                    map.put("user",user1);
               }
            }
        }catch (Exception e){
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
