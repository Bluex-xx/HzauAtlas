package com.atlas.dao;

import com.atlas.entity.Cat;
import com.atlas.entity.Picture;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Set;

@Mapper
public interface PictureDAO {
    List<Picture> recommendcat(List<Integer> integerList);
    List<Picture> recommendflower(List<Integer> integerList);
    public int likept(Picture picture);
    public int likept2(Picture picture);
    List<Picture> catdetail(Integer cid);
    List<Picture> flowerdetail(Integer fid);
    List<Picture> findbypidcat(List<String> stringList);
    List<Picture> findbypidflower(List<String> stringList);
    List<Integer> findcid();
    List<Integer> findfid();
}
