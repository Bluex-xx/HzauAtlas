package com.atlas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Transient;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    private Integer cid;
    private String name;
    private String color;
    private String place;
    private String character;
    private Integer pid;
    private Integer status;
    @Transient
    private Picture picture;
    @Transient
    private String uid;
    @Transient
    private List<Picture> pictureList;
    @Transient
    private List<Cat> catList;
    @Transient
    private Integer liked;
}
