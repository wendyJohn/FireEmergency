package com.sanleng.sl.fireemergency.mvp.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @ Fragment管理
 * @ author qiaoshi
 */
public class FragmentInfo {
    private Class<? extends Fragment> clz;
    private String title = "";
    private Bundle arugment;
    private int[] mImagIds = new int[2];
    private int[] mColors = new int[2];

    public FragmentInfo(Class<? extends Fragment> clz, String title, Bundle arugment, int[]
            imagIds, int[] colors) {
        this.clz = clz;
        this.title = title;
        this.arugment = arugment;
        mImagIds = imagIds;
        mColors = colors;
    }

    public Class<? extends Fragment> getClz() {
        return clz;
    }

    public void setClz(Class<? extends Fragment> clz) {
        this.clz = clz;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bundle getArugment() {
        return arugment;
    }

    public void setArugment(Bundle arugment) {
        this.arugment = arugment;
    }

    public int[] getImagIds() {
        return mImagIds;
    }

    public void setImagIds(int[] imagIds) {
        mImagIds = imagIds;
    }

    public int[] getColors() {
        return mColors;
    }

    public void setColors(int[] colors) {
        mColors = colors;
    }


}
