package com.atlas.dao;

import com.atlas.entity.Cat;
import com.atlas.entity.Picture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CatDAO {
    List<Cat> recommend();
    Cat catdetail(Picture picture);
    List<Cat> search(Picture picture);
    List<Cat> findall();
}
