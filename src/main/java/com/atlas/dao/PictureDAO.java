package com.atlas.dao;

import com.atlas.entity.Picture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface PictureDAO {
    List<Picture> recommendcat();
    List<Picture> recommendflower();
    public int likept(Picture picture);
    public int likept2(Picture picture);
    List<Picture> catdetail(Integer cid);
    List<Picture> flowerdetail(Integer fid);
    List<Picture> findbypidcat(List<String> stringList);
    List<Picture> findbypidflower(List<String> stringList);
}
