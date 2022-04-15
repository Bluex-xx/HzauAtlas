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
    private String uid;
    private String token;
    private String profile_photo;
    private String name;
    @Transient
    private String code;
}
