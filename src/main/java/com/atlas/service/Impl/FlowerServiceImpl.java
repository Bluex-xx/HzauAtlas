package com.atlas.service.Impl;

import com.atlas.dao.FlowerDAO;
import com.atlas.entity.Flower;
import com.atlas.entity.Picture;
import com.atlas.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    private FlowerDAO flowerDAO;

    @Override
    public List<Flower> recommend() {
        return flowerDAO.recommend();
    }

    @Override
    public Flower flowerdetail(Picture picture) {
        return flowerDAO.flowerdetail(picture);
    }

    @Override
    public List<Flower> search(Picture picture) {
        return flowerDAO.search(picture);
    }

    @Override
    public List<Flower> classifyvariety(Flower flower) {
        return flowerDAO.classifyvariety(flower);
    }

    @Override
    public List<Flower> classifyflorescence(Flower flower) {
        return flowerDAO.classifyflorescence(flower);
    }

    @Override
    public List<String> findflorescence() {
        return flowerDAO.findflorescence();
    }

    @Override
    public List<String> findvariety() {
        return flowerDAO.findvariety();
    }
}
