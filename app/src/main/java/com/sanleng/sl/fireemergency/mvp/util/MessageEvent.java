package com.sanleng.sl.fireemergency.mvp.util;


import java.util.List;

/**
 * author: qiaosdhi
 * DATE: 2018/12/10 0016
 * Description:
 **/
public class MessageEvent {
    private int TAG;
    private String message;
    private List<String> listPath;
    private List<String> listPaths;
    private List<String> imageTitle;
    private List<String> imageTitles;
    private byte[] buffer;

    private String number;
    private String state;

    public MessageEvent(int TAG) {
        this.TAG = TAG;
    }

    public MessageEvent(int TAG, String message) {
        this.TAG = TAG;
        this.message = message;
    }


    public List<String> getListPath() {
        return listPath;
    }

    public void setListPath(List<String> listPath) {
        this.listPath = listPath;
    }

    public List<String> getListPaths() {
        return listPaths;
    }

    public void setListPaths(List<String> listPaths) {
        this.listPaths = listPaths;
    }

    public List<String> getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(List<String> imageTitle) {
        this.imageTitle = imageTitle;
    }

    public List<String> getImageTitles() {
        return imageTitles;
    }

    public void setImageTitles(List<String> imageTitles) {
        this.imageTitles = imageTitles;
    }

    public int getTAG() {
        return TAG;
    }

    public void setTAG(int TAG) {
        this.TAG = TAG;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
