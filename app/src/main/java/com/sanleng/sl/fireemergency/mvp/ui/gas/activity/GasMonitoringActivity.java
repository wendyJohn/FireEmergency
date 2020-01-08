package com.sanleng.sl.fireemergency.mvp.ui.gas.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.base.BaseActivity;
import com.sanleng.sl.fireemergency.mvp.bean.Gas;
import com.sanleng.sl.fireemergency.mvp.presenter.GasPresenter;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.GasContract;
import com.sanleng.sl.fireemergency.mvp.ui.gas.adapter.GasMonitoringAdapter;
import com.sanleng.sl.fireemergency.mvp.ui.login.activity.LoginActivity;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 燃气监测
 *
 * @author Qiaoshi
 */
public class GasMonitoringActivity extends BaseActivity implements OnClickListener, GasContract {
    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.gaslevel)
    TextView waterlevel;
    @BindView(R.id.gaslistview)
    ListView gaslistview;
    @BindView(R.id.nodata)
    TextView nodata;
    @BindView(R.id.gaspressure)
    TextView waterpressure;
    private GasMonitoringAdapter gasMonitoringAdapter;//(有数据版)
    private List<Gas.PageBean.ListBean> allList;
    private int pageNo = 1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。
    private int allpage;
    private boolean is_divPage;// 是否进行分页操作
    private boolean finish = true;// 是否加载完成;
    private Handler handler = new Handler();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.gasmonitoringactivity;
    }

    @Override
    protected void initToolbar() {

    }

    //初始化
    @Override
    protected void initView() {
        allList = new ArrayList<>();
        gasMonitoringAdapter = new GasMonitoringAdapter();
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        GasPresenter.getGas(GasMonitoringActivity.this, getApplicationContext());
    }

    @Override
    public void onResume() {
        handler.postDelayed(runnable, 10 * 3000);
        super.onResume();
    }

    // 加载数据
    private void addData(int size, List<Gas.PageBean.ListBean> list) {
        allList.clear();
        int length = 10;
        if (size % length == 0) {
            allpage = size / length;
        } else {
            allpage = size / length + 1;
        }
        allList.addAll(list);
        gaslistview = findViewById(R.id.gaslistview);
        gasMonitoringAdapter.bindData(GasMonitoringActivity.this, allList);
        if (pageNo == 1) {
            // 没有数据就提示暂无数据。
            gaslistview.setEmptyView(findViewById(R.id.nodata));
            gaslistview.setAdapter(gasMonitoringAdapter);
        }
        gasMonitoringAdapter.notifyDataSetChanged();
        pageNo++;
        finish = true;
        gaslistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /**
                 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=allpage（ 这里因为服务端有allpage页数据）时，加载更多数据。
                 */
                if (is_divPage && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && pageNo <= allpage
                        && finish) {
                    finish = false;
                    GasPresenter.getGas(GasMonitoringActivity.this, getApplicationContext());
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
        gaslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //要做的事情
            GasPresenter.getGas(GasMonitoringActivity.this, getApplicationContext());
            handler.postDelayed(this, 10 * 3000);//实现循环
        }
    };


    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    @Override
    public void GasSuccess(List<Gas.PageBean.ListBean> list, int size) {
        addData(size, list);
    }

    @Override
    public void GasFailed() {

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
