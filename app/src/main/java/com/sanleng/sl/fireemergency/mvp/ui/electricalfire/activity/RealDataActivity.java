package com.sanleng.sl.fireemergency.mvp.ui.electricalfire.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.base.BaseActivity;
import com.sanleng.sl.fireemergency.mvp.bean.ReadTimeItemData;
import com.sanleng.sl.fireemergency.mvp.bean.RealDataBean;
import com.sanleng.sl.fireemergency.mvp.presenter.RealDataPresenter;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.RealTimeData;
import com.sanleng.sl.fireemergency.mvp.ui.electricalfire.adapter.RealDataAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 智能电气火灾实时数据
 *
 * @author Qiaoshi
 */
public class RealDataActivity extends BaseActivity implements OnClickListener, RealTimeData {
    @BindView(R.id.nodata)
    TextView nodata;
    RelativeLayout back;
    private RealDataAdapter realDataAdapter;//(有数据版)
    private List<RealDataBean> allList;
    private int pageNo = 1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。
    private int allpage;
    private boolean is_divPage;// 是否进行分页操作
    private boolean finish = true;// 是否加载完成;
    ListView realtimedatalslistview;
    private LinearLayout l_realdata, l_realdatas;// 选项名称


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public int getLayoutId() {
        return R.layout.realdataactivity;
    }

    @Override
    protected void initToolbar() {

    }

    //初始化
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        realDataAdapter = new RealDataAdapter();
        back = findViewById(R.id.back);
        l_realdata = findViewById(R.id.l_realdata);
        l_realdatas = findViewById(R.id.l_realdatas);
        back.setOnClickListener(this);
        l_realdata.setOnClickListener(this);
        l_realdatas.setOnClickListener(this);
        allList = new ArrayList<>();
    }

    @Override
    protected void initData() {
        RealDataPresenter.getRealTimeData(RealDataActivity.this,getApplicationContext(),pageNo+"");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    // 加载有数据的
    private void addData(int size, List<RealDataBean> list) {
        int length = 10;
        if (size % length == 0) {
            allpage = size / length;
        } else {
            allpage = size / length + 1;
        }
        allList.addAll(list);
        realtimedatalslistview = findViewById(R.id.realtimedatalslistview);
        realDataAdapter.bindData(RealDataActivity.this, allList);
        if (pageNo == 1) {
            // 没有数据就提示暂无数据。
            realtimedatalslistview.setEmptyView(findViewById(R.id.nodata));
            realtimedatalslistview.setAdapter(realDataAdapter);
        }
        realDataAdapter.notifyDataSetChanged();
        pageNo++;
        finish = true;
        realtimedatalslistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /**
                 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=allpage（ 这里因为服务端有allpage页数据）时，加载更多数据。
                 */
                if (is_divPage && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && pageNo <= allpage
                        && finish) {
                    finish = false;
                    RealDataPresenter.getRealTimeData(RealDataActivity.this,getApplicationContext(),pageNo+"");
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
        realtimedatalslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RealDataBean bean = allList.get(position);
                String name = bean.getDevicename();
                Intent intent = new Intent(RealDataActivity.this, RealDataItemActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void RealTimeDataSuccess(List<RealDataBean> list, int size) {
        addData(size, list);
    }

    @Override
    public void RealTimeDatasSuccess(List<RealDataBean> list, int size) {

    }

    @Override
    public void RealDataItemSuccess(List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_temperature, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_current, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_residualcurrent, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_voltage, String buildids, String floorids, String electricalDetectorInfos) {

    }


    @Override
    public void RealTimeDataFailed() {
        new SVProgressHUD(RealDataActivity.this).showErrorWithStatus("数据请求失败");
    }
}
