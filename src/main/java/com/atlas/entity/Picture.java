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
public class Picture {
    private Integer pid;
    private Integer fid;
    private Integer cid;
    private String store;
    private Integer liked;
    @Transient
    private Boolean islike;
    @Transient
    private String uid;
    @Transient
    private String information;
    @Transient
    private Integer type;
}
