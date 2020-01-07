package com.sanleng.sl.fireemergency.mvp.base;

import android.os.Bundle;

import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.ui.alarm.fragment.AlarmFragment;
import com.sanleng.sl.fireemergency.mvp.ui.home.fragment.HomeFragment;
import com.sanleng.sl.fireemergency.mvp.ui.mine.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @ FragmentTab
 * @ author qiaoshi
 */
public class FragmentTab {
    private static volatile FragmentTab mInstace;
    List<FragmentInfo> mList;
    private FragmentInfo mPerson;
    //    String[] mTiltles = new String[]{"首页", "报警信息", "个人"};
    String[] mTiltles = new String[]{"首页", "个人"};
    int[] mColors = new int[]{R.color.bg_tab_unselect, R.color.text_blues};
    private String tag = "tag";

    private FragmentTab() {
    }

    public static FragmentTab getInstance() {
        if (mInstace == null) {
            synchronized (FragmentTab.class) {
                if (mInstace == null) {
                    mInstace = new FragmentTab();
                }
            }
        }
        return mInstace;
    }

    public void add(FragmentInfo entity) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.add(entity);
    }

    public List<FragmentInfo> getList() {
        if (mList == null) {
            mList = new ArrayList<>();
            Bundle bundle = new Bundle();
            bundle.putString(tag, mTiltles[0]);
            FragmentInfo home = new FragmentInfo(HomeFragment.class, "首页", bundle, new int[]{
                    R.mipmap.icon_home_pressed, R.mipmap.icon_home
            }, mColors);
//            Bundle bundle1 = new Bundle();
//            bundle.putString(tag, mTiltles[1]);
//            FragmentInfo course = new FragmentInfo(AlarmFragment.class, "报警信息", bundle1, new int[]{
//                    R.drawable.icon_course_pressed, R.drawable.icon_course
//            }, mColors);
            Bundle bundle2 = new Bundle();
            bundle.putString(tag, mTiltles[1]);
            FragmentInfo zhibo = new FragmentInfo(MineFragment.class, "个人", bundle2, new int[]{
                    R.mipmap.icon_direct_seeding_pressed, R.mipmap.icon_direct_seeding
            }, mColors);
            mList.add(home);
//            mList.add(course);
            mList.add(zhibo);
        }
        return mList;
    }
}
