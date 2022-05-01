package com.atlas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Transient;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Picture implements Serializable {
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
    @Transient
    private String name;
    @Transient
    private Integer status;
}
