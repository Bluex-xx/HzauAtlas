package com.atlas.controller;

import cn.hutool.core.util.BooleanUtil;
import com.atlas.entity.Cat;
import com.atlas.entity.Flower;
import com.atlas.entity.Picture;
import com.atlas.service.Impl.CatServiceImpl;
import com.atlas.service.Impl.FlowerServiceImpl;
import com.atlas.service.Impl.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

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
    @Autowired
    private RedisTemplate redisTemplate;

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
            stringRedisTemplate.opsForSet().add(key, picture.getPid().toString());
            return "操作成功";
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

    @Scheduled(fixedDelay = 300000)
    public void updaterecommend(){
        List<Cat> catList= catService.recommend();
        List<Flower> flowerList= flowerService.recommend();
        List<Cat> catrList= catService.recommend2();
        List<Flower> flowerrList= flowerService.recommend2();
        String key1="cat";
        String key2="flower";
        String key3="catr";
        String key4="flowerr";
        redisTemplate.delete(key1);
        redisTemplate.delete(key2);
        redisTemplate.delete(key3);
        redisTemplate.delete(key4);
        ListOperations<String,Cat> catListOperations= redisTemplate.opsForList();
        ListOperations<String,Flower> flowerListOperations= redisTemplate.opsForList();
        ListOperations<String,Cat> catrListOperations= redisTemplate.opsForList();
        ListOperations<String,Flower> flowerrListOperations= redisTemplate.opsForList();

        catListOperations.rightPushAll(key1,catList);
        flowerListOperations.rightPushAll(key2,flowerList);
        catrListOperations.rightPushAll(key3,catrList);
        flowerrListOperations.rightPushAll(key4,flowerrList);
        System.out.println(new Date());
    }

    //主页推荐，根据点赞数量进行排序
    @ResponseBody
    @PostMapping("/recommend")
    public List<Object> recommend(@RequestBody Picture picture) {
        List<Cat> catList = new ArrayList<>();
        List<Flower> flowerList = new ArrayList<>();
        //List<Picture> pictureList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        ListOperations<String, Cat> catListOperations = redisTemplate.opsForList();
        ListOperations<String, Flower> flowerListOperations = redisTemplate.opsForList();
        //ListOperations<String, Picture> pictureListOperations = redisTemplate.opsForList();

        if (picture.getStatus() == 1) {
            if (picture.getType() == 1) {
                catList = catListOperations.range("cat", 0, -1);
                catList.forEach(cat -> {
                    cat.getPicture().setUid(picture.getUid());
                    isliked(cat.getPicture());
                });
                objectList = new ArrayList<>(catList);
            } else {
                flowerList = flowerListOperations.range("flower", 0, -1);
                flowerList.forEach(flower -> {
                    flower.getPicture().setUid(picture.getUid());
                    isliked(flower.getPicture());
                });
                objectList = new ArrayList<>(flowerList);
            }
        } else {
            if (picture.getType() == 1) {
                catList = catListOperations.range("catr", 0, -1);
                catList.forEach(cat -> {
                    //List<Picture> pictureList=cat.getPictureList();
                    //System.out.println(pictureList);
                    cat.getPicture().setUid(picture.getUid());
                    isliked(cat.getPicture());
                });
                objectList = new ArrayList<>(catList);
            } else {
                flowerList = flowerListOperations.range("flowerr", 0, -1);
                flowerList.forEach(flower -> {
                    flower.getPicture().setUid(picture.getUid());
                    isliked(flower.getPicture());
                });
                objectList = new ArrayList<>(flowerList);
            }
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
