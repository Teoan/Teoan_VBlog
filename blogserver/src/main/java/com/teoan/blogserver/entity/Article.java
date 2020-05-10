package com.teoan.blogserver.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (Article)实体类
 *
 * @author makejava
 * @since 2020-04-24 15:24:39
 */
public class Article implements Serializable {
    private static final long serialVersionUID = 474006127944594616L;
    
    private Integer id;
    
    private String title;
    /**
    * md文件源码
    */
    private String mdcontent;
    /**
    * html源码
    */
    private String htmlcontent;
    
    private String summary;
    
    private Integer cid;
    
    private Integer uid;
    
    private Date publishdate;
    
    private Date edittime;
    /**
    * 0表示草稿箱，1表示已发表，2表示已删除
    */
    private Integer state;
    
    private Integer pageview;

    private List<Tags> tags;

    private String[] dynamicTags;

    private String nickname;

    private String cateName;



    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }


    public String[] getDynamicTags() {
        return dynamicTags;
    }

    public void setDynamicTags(String[] dynamicTags) {
        this.dynamicTags = dynamicTags;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMdcontent() {
        return mdcontent;
    }

    public void setMdcontent(String mdcontent) {
        this.mdcontent = mdcontent;
    }

    public String getHtmlcontent() {
        return htmlcontent;
    }

    public void setHtmlcontent(String htmlcontent) {
        this.htmlcontent = htmlcontent;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Date getEdittime() {
        return edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPageview() {
        return pageview;
    }

    public void setPageview(Integer pageview) {
        this.pageview = pageview;
    }

}