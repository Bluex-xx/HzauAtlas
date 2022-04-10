package com.atlas.controller;

import cn.hutool.core.util.BooleanUtil;
import com.atlas.entity.Cat;
import com.atlas.entity.Flower;
import com.atlas.entity.Picture;
import com.atlas.service.Impl.CatServiceImpl;
import com.atlas.service.Impl.FlowerServiceImpl;
import com.atlas.service.Impl.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/picture")
public class PictureController {
    @Autowired
    private PictureServiceImpl pictureService;
    @Autowired
    private CatServiceImpl catService;
    @Autowired
    private FlowerServiceImpl flowerService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //为照片点赞，会存入redis数据库
    @ResponseBody
    @PostMapping("/like")
    public String likept(@RequestBody Picture picture) {
        String uid = picture.getUid();
        String key = "picture:" + uid;
        System.out.println("key" + key);
        System.out.println(picture.getPid().toString());
        Boolean isMember = stringRedisTemplate.opsForSet().isMember(key, picture.getPid().toString());
        System.out.println(isMember + " " + BooleanUtil.isFalse(isMember));
        if (BooleanUtil.isFalse(isMember)) {
            int row = pictureService.likept(picture);
            if (row != 0) {
                stringRedisTemplate.opsForSet().add(key, picture.getPid().toString());
                return "操作成功";
            }
        } else {
            int row = pictureService.likept2(picture);
            stringRedisTemplate.opsForSet().remove(key, picture.getPid().toString());
        }
        return "操作成功";
    }

    //判断是否在表中
    public boolean isliked(@RequestBody Picture picture) {
        String key = "picture:" + picture.getUid();
        //System.out.println("key:"+key);
        Boolean isMember = stringRedisTemplate.opsForSet().isMember(key, picture.getPid().toString());
        //System.out.println("isMember:"+isMember);
        picture.setIslike(BooleanUtil.isTrue(isMember));
        return isMember;
    }

    //主页推荐，根据点赞数量进行排序
    @ResponseBody
    @PostMapping("/recommend")
    public List<Object> recommend(@RequestBody Picture picture){
        List<Cat> catList= catService.recommend();
        List<Flower> flowerList= flowerService.recommend();
        List<Object> objectList=new ArrayList<>();
        if(picture.getType()==1){
            catList.forEach(cat -> {
                cat.getPicture().setUid(picture.getUid());
                isliked(cat.getPicture());
        });
           objectList=new ArrayList<>(catList);
        }else{
            flowerList.forEach(flower -> {
                flower.getPicture().setUid(picture.getUid());
                isliked(flower.getPicture());
            });
            objectList=new ArrayList<>(flowerList);
        }
           return objectList;
    }

    //用户界面
    @ResponseBody
    @PostMapping("/user")
    public List<Picture> userliked(@RequestBody Picture picture){
        String key="picture:"+picture.getUid();
        System.out.println(picture.getUid());
        List<String> stringList=new ArrayList<String>(stringRedisTemplate.opsForSet().members(key)) {};
        System.out.println(stringList);
        List<Picture> pictureList=new ArrayList<>();
        if(picture.getType()==1){
            pictureList=pictureService.findbypidcat(stringList);
        }else{
            pictureList=pictureService.findbypidflower(stringList);
        }
        //System.out.println(integerList);
        pictureList.forEach(picture1 -> {
            picture1.setUid(picture.getUid());
            isliked(picture1);
        });
        return pictureList;
    }
}