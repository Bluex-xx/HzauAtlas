package com.atlas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Transient;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    private Integer id;
    private Integer fid;
    private Integer cid;
    private String uid;
    private String content;
    private String date;
    private String profile_photo;
    private String name;
    @Transient
    private User user;
}