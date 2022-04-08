package com.atlas.service.Impl;

import cn.hutool.core.util.BooleanUtil;
import com.atlas.dao.PictureDAO;
import com.atlas.entity.Cat;
import com.atlas.entity.Picture;
import com.atlas.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Set;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureDAO pictureDAO;

    @Override
    public int likept(Picture picture) {
        return pictureDAO.likept(picture);
    }

    @Override
    public int likept2(Picture picture) {
        return pictureDAO.likept2(picture);
    }

    @Override
    public List<Picture> recommendcat(List<Integer> integerList) {
        return pictureDAO.recommendcat(integerList);
    }

    @Override
    public List<Picture> recommendflower(List<Integer> integerList) {
        return pictureDAO.recommendflower(integerList);
    }

    @Override
    public List<Picture> catdetail(Integer cid) {
        return pictureDAO.catdetail(cid);
    }

    @Override
    public List<Picture> flowerdetail(Integer fid) {
        return pictureDAO.flowerdetail(fid);
    }

    @Override
    public List<Picture> findbypidcat(List<String> stringList) {
        return pictureDAO.findbypidcat(stringList);
    }

    @Override
    public List<Picture> findbypidflower(List<String> stringList) {
        return pictureDAO.findbypidflower(stringList);
    }

    @Override
    public List<Integer> findcid() {
        return pictureDAO.findcid();
    }

    @Override
    public List<Integer> findfid() {
        return pictureDAO.findfid();
    }

}
