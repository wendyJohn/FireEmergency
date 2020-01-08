package com.sanleng.sl.fireemergency.mvp.ui.hiddendanger.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.base.BaseFragment;
import com.sanleng.sl.fireemergency.mvp.bean.RectificationBean;
import com.sanleng.sl.fireemergency.mvp.presenter.HiddendPresenter;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.HiddendContract;
import com.sanleng.sl.fireemergency.mvp.ui.hiddendanger.activity.RectificationitemActivity;
import com.sanleng.sl.fireemergency.mvp.ui.hiddendanger.adapter.SRectificationAdapter;
import com.sanleng.sl.fireemergency.mvp.ui.login.activity.LoginActivity;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 待整改任务
 *
 * @author Qiaoshi
 */
@SuppressLint("ResourceAsColor")
public class SRectificationFragment extends BaseFragment implements HiddendContract {

    private ListView sretifiionlslistview;
    private SRectificationAdapter sRectificationAdapter;
    private View view;
    private int allpage;
    private int pageNo= 1;
    private List<RectificationBean.DataBean.ListBean> allList;// 存放所有数据AlarmBean的list集合
    private boolean is_divPage;// 是否进行分页操作
    private boolean finish = true;// 是否加载完成;
    private TextView text_total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stayrectifionfragment, null);
        initview();
        return view;
    }

    private void initview() {
        allList = new ArrayList<>();
        sRectificationAdapter = new SRectificationAdapter();
        text_total = view.findViewById(R.id.text_total);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        pageNo = 1;
        HiddendPresenter.getSHiddend(SRectificationFragment.this, getActivity(), pageNo + "");
        super.onResume();

    }

    // 加载数据
    private void loadData(int size, List<RectificationBean.DataBean.ListBean> list) {
        text_total.setText("共有" + size + "处待整改隐患");
        allList.clear();
        int length = 10;
        if (size % length == 0) {
            allpage = size / length;
        } else {
            allpage = size / length + 1;
        }
        allList.addAll(list);
        sretifiionlslistview = view.findViewById(R.id.stayretifiionlslistview);
        sRectificationAdapter.bindData(getActivity(), allList);
        if (pageNo == 1) {
            // 没有数据就提示暂无数据。
            sretifiionlslistview.setEmptyView(view.findViewById(R.id.nodata));
            sretifiionlslistview.setAdapter(sRectificationAdapter);
        }
        sRectificationAdapter.notifyDataSetChanged();
        pageNo++;
        finish = true;
        sretifiionlslistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /**
                 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=allpage（ 这里因为服务端有allpage页数据）时，加载更多数据。
                 */
                if (is_divPage && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && pageNo <= allpage
                        && finish) {
                    finish = false;
                    HiddendPresenter.getSHiddend(SRectificationFragment.this, getActivity(), pageNo + "");
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
        sretifiionlslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RectificationBean.DataBean.ListBean bean = allList.get(position);
                String ids = bean.getIds();
                String labelnumber = bean.getQrcode();
                String devicename = bean.getFpEquipmentName();
                String equipmentposition = bean.getOwnedBuildingName();
                String rectificationperiod = bean.getProcessingPeriod();
                String personliable = bean.getCreate_user();
                String hiddendangerdescription = bean.getDescription();


				Intent intent = new Intent(getActivity(), RectificationitemActivity.class);
                intent.putExtra("Ids", ids);
				intent.putExtra("labelnumber", labelnumber);
				intent.putExtra("devicename", devicename);
				intent.putExtra("equipmentposition", equipmentposition);
				intent.putExtra("rectificationperiod", rectificationperiod);
				intent.putExtra("personliable", personliable);
				intent.putExtra("hiddendangerdescription", hiddendangerdescription);
				startActivity(intent);
            }
        });

    }

    @Override
    public void Success(List<RectificationBean.DataBean.ListBean> list, int size,String scope) {
        loadData(size, list);
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
}
