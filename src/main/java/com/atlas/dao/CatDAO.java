package com.atlas.dao;

import com.atlas.entity.Cat;
import com.atlas.entity.Picture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CatDAO {
    List<Cat> recommend();
    List<Cat> recommend2();
    Cat catdetail(Picture picture);
    List<Cat> search(Picture picture);
    List<Cat> findall(Cat cat);
    List<Cat> findcolor();
}
