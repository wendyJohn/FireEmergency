package com.sanleng.sl.fireemergency.mvp.ui.patrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.bean.Dpoint;
import com.sanleng.sl.fireemergency.mvp.bean.RectificationBean;
import com.sanleng.sl.fireemergency.mvp.presenter.PointPresenter;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.DevicePointContract;
import com.sanleng.sl.fireemergency.mvp.ui.nfc.activity.NfcPointActivity;
import com.sanleng.sl.fireemergency.mvp.ui.patrol.adapter.DevicePointAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备点位（NFC）
 *
 * @author qiaoshi
 */
public class DevicePointActivity extends AppCompatActivity implements OnClickListener, DevicePointContract {
    private RelativeLayout back;
    private int allpage;
    private int pageNo = 1;
    private List<Dpoint.DataBean.ListBean> allList;// 存放所有数据AlarmBean的list集合
    private boolean is_divPage;// 是否进行分页操作
    private boolean finish = true;// 是否加载完成;
    private ListView pointlistview;
    private DevicePointAdapter devicePointAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pointactivity);
        initview();

    }

    // 初始化数据
    private void initview() {
        allList = new ArrayList<>();
        devicePointAdapter = new DevicePointAdapter();
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        pageNo = 1;
        PointPresenter.getPoint(DevicePointActivity.this, getApplicationContext(), pageNo + "");
        super.onResume();
    }

    // 加载数据
    private void loadData(int size, List<Dpoint.DataBean.ListBean> list) {
        allList.clear();
        int length = 10;
        if (size % length == 0) {
            allpage = size / length;
        } else {
            allpage = size / length + 1;
        }
        allList.addAll(list);
        pointlistview = findViewById(R.id.pointlistview);
        devicePointAdapter.bindData(DevicePointActivity.this, allList);
        if (pageNo == 1) {
            // 没有数据就提示暂无数据。
            pointlistview.setEmptyView(findViewById(R.id.nodata));
            pointlistview.setAdapter(devicePointAdapter);
        }
        devicePointAdapter.notifyDataSetChanged();
        pageNo++;
        finish = true;
        pointlistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /**
                 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=allpage（ 这里因为服务端有allpage页数据）时，加载更多数据。
                 */
                if (is_divPage && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && pageNo <= allpage
                        && finish) {
                    finish = false;
                    PointPresenter.getPoint(DevicePointActivity.this, getApplicationContext(), pageNo + "");
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
        pointlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dpoint.DataBean.ListBean bean = allList.get(position);
                String state = bean.getActivateStatus();
                String ids = bean.getIds();
                if (state.equals("激活")) {
                    new SVProgressHUD(DevicePointActivity.this).showInfoWithStatus("设备已激活");
                }
                if (state.equals("未激活")) {
                    new SVProgressHUD(DevicePointActivity.this).showInfoWithStatus("设备未激活");

                    Intent NfcPoint = new Intent(DevicePointActivity.this, NfcPointActivity.class);
                    NfcPoint.putExtra("type", "激活");
                    NfcPoint.putExtra("Ids", ids);
                    startActivity(NfcPoint);
                }
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
    public void Success(List<Dpoint.DataBean.ListBean> list, int size) {
        loadData(size, list);
    }

    @Override
    public void Failed() {
        new SVProgressHUD(DevicePointActivity.this).showErrorWithStatus("加载失败");
    }

}
