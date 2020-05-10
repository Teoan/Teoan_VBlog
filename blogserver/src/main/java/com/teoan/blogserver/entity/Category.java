package com.teoan.blogserver.entity;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * (Category)实体类
 *
 * @author makejava
 * @since 2020-04-24 15:30:05
 */
public class Category implements Serializable {
    private static final long serialVersionUID = -15103198892848128L;
    
    private Integer id;
    
    private String catename;
    
    private Timestamp date;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}