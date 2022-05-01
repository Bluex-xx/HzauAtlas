package com.atlas.service.Impl;

import com.atlas.dao.CatDAO;
import com.atlas.entity.Cat;
import com.atlas.entity.Picture;
import com.atlas.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService {
    @Autowired
    private CatDAO catDAO;

    @Override
    public List<Cat> recommend() {
        return catDAO.recommend();
    }

    @Override
    public List<Cat> recommend2() {
        return catDAO.recommend2();
    }

    @Override
    public Cat catdetail(Picture picture) {
        return catDAO.catdetail(picture);
    }

    @Override
    public List<Cat> search(Picture picture) {
        return catDAO.search(picture);
    }

    @Override
    public List<Cat> findall(Cat cat) {
        return catDAO.findall(cat);
    }

    @Override
    public List<Cat> findcolor() {
        return catDAO.findcolor();
    }
}
