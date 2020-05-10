package com.teoan.blogserver.entity;

import java.io.Serializable;

/**
 * (Tags)实体类
 *
 * @author makejava
 * @since 2020-04-24 15:33:44
 */
public class Tags implements Serializable {
    private static final long serialVersionUID = -94174165934264179L;
    
    private Integer id;
    
    private String tagname;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

}