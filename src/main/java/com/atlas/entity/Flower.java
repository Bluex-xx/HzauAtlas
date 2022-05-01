package com.atlas.entity;

import lombok.*;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Flower implements Serializable {
    private Integer fid;
    private String name;
    private String florescence;
    private String department;
    private String allegory;
    private Integer pid;
    private String color;
    @Transient
    private List<Picture> pictureList;
    @Transient
    private List<Flower> flowerList;
    @Transient
    private String uid;
    @Transient
    private Picture picture;
    @Transient
    private String information;
    @Transient
    private List<Comment> commentList;
    @Transient
    private Integer liked;
}
