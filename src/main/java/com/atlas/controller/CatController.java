package com.atlas.controller;

import cn.hutool.core.util.BooleanUtil;
import com.atlas.entity.Cat;
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
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/cat")
public class CatController {
    @Autowired
    private CatServiceImpl catService;

    @Autowired
    private PictureServiceImpl pictureService;

    @Autowired
    private FlowerServiceImpl flowerService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /*@ResponseBody
    @PostMapping("/recommend")
    public List<Object> recommend(@RequestBody Cat cat){
        if(cat.getStatus()==2){
            List<Object> objectList=new ArrayList<>(flowerService.recommend());
            return objectList;
        }else {
            List<Object> objectList=new ArrayList<>(catService.recommend());
            return objectList;
        }
    }*/

    //猫猫照片详情页
    @ResponseBody
    @PostMapping("/catdetail")
    Cat catdetail(@RequestBody Picture picture){
        Cat cat=catService.catdetail(picture);
        List<Picture> pictureList= pictureService.catdetail(cat.getCid());
        String key="picture:"+picture.getUid();
        pictureList.forEach(picture1 -> {
            Boolean isMember=stringRedisTemplate.opsForSet().isMember(key,picture1.getPid().toString());
            picture1.setIslike(BooleanUtil.isTrue(isMember));
        });
        cat.setPictureList(pictureList);
        return cat;
    }

    //搜索界面
    @ResponseBody
    @PostMapping("/search")
    public List<Picture> search(@RequestBody Picture picture){
        List<Cat> catList=catService.search(picture);
        System.out.println(catList);
        List<Picture> pictureList=new ArrayList<>();
        String key="picture:"+picture.getUid();
        catList.forEach(cat -> {
            pictureList.addAll(pictureService.catdetail(cat.getCid()));
        });
        pictureList.forEach(picture1 -> {
            Boolean isMember=stringRedisTemplate.opsForSet().isMember(key,picture1.getPid().toString());
            picture1.setIslike(BooleanUtil.isTrue(isMember));
        });
        return pictureList;
    }

    //分类界面
    @ResponseBody
    @PostMapping("/classifycolor")
    public List<Cat> findall(){
        return catService.findall();
    }

}
