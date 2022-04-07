package com.atlas.service;

import com.atlas.entity.Flower;
import com.atlas.entity.Picture;

import java.util.List;

public interface FlowerService {
    List<Flower> recommend();
    Flower flowerdetail(Picture picture);
    List<Flower> search(Picture picture);
    List<Flower> classifyvariety();
    List<Flower> classifyflorescence();
}
