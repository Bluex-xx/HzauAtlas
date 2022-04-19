package com.atlas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String openid;
    private String profile_photo;
    private String name;
    private Integer uid;
    @Transient
    private String code;
}
