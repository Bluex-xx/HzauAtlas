package com.atlas.service;

import com.atlas.entity.Cat;
import com.atlas.entity.Picture;

import java.util.List;

public interface CatService {
    List<Cat> recommend();
    Cat catdetail(Picture picture);
    List<Cat> search(Picture picture);
    List<Cat> findall(Cat cat);
    List<Cat> findcolor();
}
