package com.sanleng.sl.fireemergency.mvp.ui.patrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.base.BaseActivity;
import com.sanleng.sl.fireemergency.mvp.bean.PatrolRecordBean;
import com.sanleng.sl.fireemergency.mvp.presenter.PatrolRecordPresenter;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.PatrolRecord;
import com.sanleng.sl.fireemergency.mvp.ui.login.activity.LoginActivity;
import com.sanleng.sl.fireemergency.mvp.ui.patrol.adapter.PatrolRecordAdapter;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 巡查总记录
 *
 * @author Qiaoshi
 */
public class PatrolHandleActivity extends BaseActivity implements OnClickListener, PatrolRecord {

    private RelativeLayout r_back;
    private ListView patrolrecordlslistview;
    private int pageNo = 1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。
    private int allpage;
    private List< PatrolRecordBean.DataBean.ListBean> allList;// 存放所有数据AlarmBean的list集合
    private boolean is_divPage;// 是否进行分页操作
    private boolean finish = true;// 是否加载完成;
    private PatrolRecordAdapter patrolRecordAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.patrolhandleactivity;
    }

    @Override
    protected void initToolbar() {
    }

    @Override
    protected void initView() {
        patrolRecordAdapter = new PatrolRecordAdapter();
        r_back = findViewById(R.id.r_back);
        r_back.setOnClickListener(this);
        allList = new ArrayList<>();
    }

    @Override
    protected void initData() {
        PatrolRecordPresenter.getPatrolRecord(PatrolHandleActivity.this, getApplicationContext(), pageNo + "");
    }

    // 加载数据
    private void loadData(int size, List< PatrolRecordBean.DataBean.ListBean> list) {
        int length = 10;
        if (size % length == 0) {
            allpage = size / length;
        } else {
            allpage = size / length + 1;
        }
        allList.addAll(list);
        patrolrecordlslistview =findViewById(R.id.patrolrecordlslistview);
        patrolRecordAdapter.bindData(PatrolHandleActivity.this, allList);
        if (pageNo == 1) {
            // 没有数据就提示暂无数据。
            patrolrecordlslistview.setEmptyView(findViewById(R.id.nodata));
            patrolrecordlslistview.setAdapter(patrolRecordAdapter);
        }
        patrolRecordAdapter.notifyDataSetChanged();
        pageNo++;
        finish = true;
        patrolrecordlslistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /**
                 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=allpage（ 这里因为服务端有allpage页数据）时，加载更多数据。
                 */
                if (is_divPage && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && pageNo <= allpage
                        && finish) {
                    finish = false;
                    PatrolRecordPresenter.getPatrolRecord(PatrolHandleActivity.this, getApplicationContext(), pageNo + "");
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
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            // 返回
            case R.id.r_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void PatrolRecordSuccess(List<PatrolRecordBean.DataBean.ListBean> list, int size) {
        loadData(size, list);
    }

    @Override
    public void PatrolRecordFailed() {

    }

    @Override
    public void Timeout() {
        // 清空sharepre中的用户名和密码
        new SVProgressHUD(getApplicationContext()).showInfoWithStatus("登录超时，请重新登录");
        new Handler().postDelayed(new Runnable() {
            public void run() {
                PreferenceUtils.setString(getApplicationContext(), "FireEmergency_usernames", "");
                Intent loginOutIntent = new Intent(getApplicationContext(), LoginActivity.class);
                loginOutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginOutIntent);
                finish();
            }
        }, 2000);
    }
}
