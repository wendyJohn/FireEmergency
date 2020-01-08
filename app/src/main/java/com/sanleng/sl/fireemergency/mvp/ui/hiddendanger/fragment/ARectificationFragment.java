package com.sanleng.sl.fireemergency.mvp.ui.hiddendanger.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.base.BaseFragment;
import com.sanleng.sl.fireemergency.mvp.bean.ARectBean;
import com.sanleng.sl.fireemergency.mvp.bean.RectificationBean;
import com.sanleng.sl.fireemergency.mvp.presenter.HiddendPresenter;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.AiddendContract;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.HiddendContract;
import com.sanleng.sl.fireemergency.mvp.ui.hiddendanger.adapter.ARectificationAdapter;
import com.sanleng.sl.fireemergency.mvp.ui.login.activity.LoginActivity;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 已整改任务
 *
 * @author Qiaoshi
 */
@SuppressLint("ResourceAsColor")
public class ARectificationFragment extends BaseFragment implements AiddendContract {

    private ListView alreadyretifiionlslistview;
    private ARectificationAdapter aRectificationAdapter;
    private View view;
    private TextView text_todays, text_thisweeks, text_thismonths;
    private TextView problemdescription;
    private int pageNo = 1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。
    private int allpage;
    private List<ARectBean.DataBean.ListBean> allList;// 存放所有数据AlarmBean的list集合
    private boolean is_divPage;// 是否进行分页操作
    private boolean finish = true;// 是否加载完成
    private String scope;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alreadrectifionfragment, null);
        initview();
        return view;
    }

    private void initview() {
        aRectificationAdapter = new ARectificationAdapter();
        text_todays = view.findViewById(R.id.text_todays);
        text_thisweeks = view.findViewById(R.id.text_thisweeks);
        text_thismonths = view.findViewById(R.id.text_thismonths);
        problemdescription = view.findViewById(R.id.problemdescription);

        text_todays.setOnClickListener(new MyOnClickListener(0));
        text_thisweeks.setOnClickListener(new MyOnClickListener(1));
        text_thismonths.setOnClickListener(new MyOnClickListener(2));
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        pageNo = 1;
        allList = new ArrayList<>();
        scope = "day";
        loadData();
        super.onResume();
    }

    //加载数据
    private void loadData() {
        HiddendPresenter.getAHiddend(ARectificationFragment.this, getActivity(), pageNo + "", scope);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void Success(List<ARectBean.DataBean.ListBean> list, int size, String scope) {
        if (scope.equals("day")) {
            problemdescription.setText("今日共有" + size + "处已整改隐患");
        }
        if (scope.equals("week")) {
            problemdescription.setText("本周共有" + size + "处已整改隐患");
        }
        if (scope.equals("month")) {
            problemdescription.setText("本月共有" + size + "处已整改隐患");
        }
        allList.clear();
        int length = 10;
        if (size % length == 0) {
            allpage = size / length;
        } else {
            allpage = size / length + 1;
        }
        allList.addAll(list);
        alreadyretifiionlslistview = view.findViewById(R.id.alreadyretifiionlslistview);
        aRectificationAdapter.bindData(getActivity(), allList);
        if (pageNo == 1) {
            // 没有数据就提示暂无数据。
            alreadyretifiionlslistview.setEmptyView(view.findViewById(R.id.nodata));
            alreadyretifiionlslistview.setAdapter(aRectificationAdapter);
        }
        aRectificationAdapter.notifyDataSetChanged();
        pageNo++;
        finish = true;
        alreadyretifiionlslistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /**
                 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=allpage（ 这里因为服务端有allpage页数据）时，加载更多数据。
                 */
                if (is_divPage && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && pageNo <= allpage
                        && finish) {
                    finish = false;
                    HiddendPresenter.getAHiddend(ARectificationFragment.this, getActivity(), pageNo + "", scope);
                } else if (pageNo > allpage && finish) {
                    finish = false;
                    // 如果pageNo>allpage则表示，服务端没有更多的数据可供加载了。
                }
            }

            // 当：第一个可见的item（firstVisibleItem）+可见的item的个数（visibleItemCount）=
            // 所有的item总数的时候， is_divPage变为TRUE，这个时候才会加载数据。
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                is_divPage = (firstVisibleItem + visibleItemCount == totalItemCount);
            }
        });
    }

    @Override
    public void Failed() {
        new SVProgressHUD(getActivity()).showErrorWithStatus("加载失败");
    }

    @Override
    public void Timeout() {
        // 清空sharepre中的用户名和密码
        new SVProgressHUD(getActivity()).showInfoWithStatus("登录超时，请重新登录");
        new Handler().postDelayed(new Runnable() {
            public void run() {
                PreferenceUtils.setString(getActivity(), "FireEmergency_usernames", "");
                Intent loginOutIntent = new Intent(getActivity(), LoginActivity.class);
                loginOutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginOutIntent);
                getActivity().finish();
            }
        }, 2000);
    }

    /**
     * 头标点击监听
     */
    private class MyOnClickListener implements OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @SuppressLint("ResourceType")
        public void onClick(View v) {
            switch (index) {
                case 0:
                    text_todays.setTextColor(getResources().getColor(R.color.white));
                    text_thisweeks.setTextColor(getResources().getColor(R.color.blue));
                    text_thismonths.setTextColor(getResources().getColor(R.color.blue));
                    text_todays.setBackground(getResources().getDrawable(R.drawable.text_rounded));
                    text_thisweeks.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thismonths.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    pageNo = 1;
                    allList = new ArrayList<>();
                    scope = "day";
                    loadData();
                    break;
                case 1:
                    text_todays.setTextColor(getResources().getColor(R.color.blue));
                    text_thisweeks.setTextColor(getResources().getColor(R.color.white));
                    text_thismonths.setTextColor(getResources().getColor(R.color.blue));
                    text_todays.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thisweeks.setBackground(getResources().getDrawable(R.drawable.text_rounded));
                    text_thismonths.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    pageNo = 1;
                    allList = new ArrayList<>();
                    scope = "week";
                    loadData();
                    break;
                case 2:
                    text_todays.setTextColor(getResources().getColor(R.color.blue));
                    text_thisweeks.setTextColor(getResources().getColor(R.color.blue));
                    text_thismonths.setTextColor(getResources().getColor(R.color.white));
                    text_todays.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thisweeks.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thismonths.setBackground(getResources().getDrawable(R.drawable.text_rounded));
                    pageNo = 1;
                    allList = new ArrayList<>();
                    scope = "month";
                    loadData();
                    break;
            }
        }
    }
}
