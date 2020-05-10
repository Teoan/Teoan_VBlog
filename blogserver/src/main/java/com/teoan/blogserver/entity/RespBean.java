package com.teoan.blogserver.entity;

/**
 * @author Teoan
 * @description 返回信息类
 * @date 2020/4/24 17:08
 */
public class RespBean {
    private String status;
    private String msg;

    public RespBean(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
