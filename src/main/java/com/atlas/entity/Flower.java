package com.atlas.entity;

import lombok.*;

import javax.persistence.Transient;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Flower {
    private Integer fid;
    private String variety;
    private String florescence;
    private String department;
    private String allegory;
    private Integer pid;
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
}
