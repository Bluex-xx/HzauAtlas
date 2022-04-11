package com.atlas.controller;

import cn.hutool.core.util.BooleanUtil;
import com.atlas.entity.Cat;
import com.atlas.entity.Flower;
import com.atlas.entity.Picture;
import com.atlas.service.FlowerService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/flower")
public class FlowerController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private FlowerServiceImpl flowerService;

    @Autowired
    private PictureServiceImpl pictureService;

    //花花照片详情页
    @ResponseBody
    @PostMapping("/flowerdetail")
    Flower flowerdetail(@RequestBody Picture picture){
        Flower flower=flowerService.flowerdetail(picture);
        List<Picture> pictureList= pictureService.flowerdetail(flower.getFid());
        String key="picture:"+picture.getUid();
        pictureList.forEach(picture1 -> {
            Boolean isMember=stringRedisTemplate.opsForSet().isMember(key,picture1.getPid().toString());
            picture1.setIslike(BooleanUtil.isTrue(isMember));
        });
        flower.setPictureList(pictureList);
        return flower;
    }

    //搜索界面
    @ResponseBody
    @PostMapping("/search")
    public List<Picture> search(@RequestBody Picture picture){
        List<Flower> flowerList= flowerService.search(picture);
        System.out.println(flowerList);
        List<Picture> pictureList=new ArrayList<>();
        String key="picture:"+picture.getUid();
        flowerList.forEach(flower -> {
            pictureList.addAll(pictureService.flowerdetail(flower.getFid()));
        });
        pictureList.forEach(picture1 -> {
            Boolean isMember=stringRedisTemplate.opsForSet().isMember(key,picture1.getPid().toString());
            picture1.setIslike(BooleanUtil.isTrue(isMember));
        });
        return pictureList;
    }

    //分类界面花期
    @ResponseBody
    @PostMapping("/classifyflorescence")
    public List<Flower> classifyflorescence(@RequestBody Flower flower){
        return flowerService.classifyflorescence(flower);
    }

    //分类界面品种
    @ResponseBody
    @PostMapping("/classifyvariety")
    public List<Flower> classifyvariety(@RequestBody Flower flower){
        return flowerService.classifyvariety(flower);
    }

    //分类花色
    @ResponseBody
    @PostMapping("/classifycolor")
    public List<Flower> classifycolor(@RequestBody Flower flower){
        return flowerService.classifycolor(flower);
    }

    //分类品种
    @ResponseBody
    @PostMapping("/findvariety")
    public List<Flower> findvariety(){
        return flowerService.findvariety();
    }

    //分类花期
    @ResponseBody
    @PostMapping("/findflorescence")
    public List<Flower> findflorescence(){
        return flowerService.findflorescence();
    }

    //分类颜色
    @ResponseBody
    @PostMapping("/findcolor")
    public List<Flower> findcolor(){
        return flowerService.findcolor();
    }
}
