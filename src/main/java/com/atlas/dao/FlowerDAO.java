package com.atlas.dao;

import com.atlas.entity.Cat;
import com.atlas.entity.Flower;
import com.atlas.entity.Picture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FlowerDAO {
    List<Flower> recommend();
    Flower flowerdetail(Picture picture);
    List<Flower> search(Picture picture);
    List<Flower> classifyflorescence(Flower flower);
    List<Flower> classifyvariety(Flower flower);
    List<Flower> classifycolor(Flower flower);
    List<Flower> findflorescence();
    List<Flower> findvariety();
    List<Flower> findcolor();
}
