package com.sanleng.sl.fireemergency.mvp.bean;

public class User {
    /**
     * msg : 登录失败
     * status : 1
     */

    private String msg;
    private String status;
    private String jwt;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
