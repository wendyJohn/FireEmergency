package com.sanleng.sl.fireemergency.mvp.bean;

public class ImageUpload {

    /**
     * msg : 巡查记录上传成功
     * code : 0
     * data : null
     * state : ok
     */
    private String msg;
    private String code;
    private Object data;
    private String state;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
