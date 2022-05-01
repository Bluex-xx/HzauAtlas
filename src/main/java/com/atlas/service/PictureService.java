package com.atlas.service;

import com.atlas.entity.Picture;

import java.util.List;
import java.util.Set;

public interface PictureService {
    public int likept(Picture picture);
    public int likept2(Picture picture);
    List<Picture> recommendcat();
    List<Picture> recommendflower();
    List<Picture> catdetail(Integer cid);
    List<Picture> flowerdetail(Integer fid);
    List<Picture> findbypidcat(List<String> stringList);
    List<Picture> findbypidflower(List<String> stringList);
    List<Integer> findcid();
    List<Integer> findfid();
}
