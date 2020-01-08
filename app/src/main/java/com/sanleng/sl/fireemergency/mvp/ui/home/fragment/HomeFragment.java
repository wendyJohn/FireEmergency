package com.sanleng.sl.fireemergency.mvp.ui.home.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.ui.gas.activity.GasMonitoringActivity;
import com.sanleng.sl.fireemergency.mvp.ui.electricalfire.activity.RealDataActivity;
import com.sanleng.sl.fireemergency.mvp.ui.hiddendanger.activity.RectificationActivity;
import com.sanleng.sl.fireemergency.mvp.ui.home.adapter.FunctionAdapter;
import com.sanleng.sl.fireemergency.mvp.ui.monitoring.activity.MonitoringActivity;
import com.sanleng.sl.fireemergency.mvp.ui.nfc.activity.NfcPointActivity;
import com.sanleng.sl.fireemergency.mvp.ui.patrol.activity.DevicePointActivity;
import com.sanleng.sl.fireemergency.mvp.ui.patrol.activity.PatrolHandleActivity;
import com.sanleng.sl.fireemergency.mvp.ui.patrol.activity.PointPatrolActivity;
import com.sanleng.sl.fireemergency.mvp.ui.video.activity.VideoActivity;
import com.sanleng.sl.fireemergency.mvp.ui.watersystem.activity.WaterSystemActivity;
import com.sanleng.sl.fireemergency.mvp.util.GlideImageLoader;
import com.sanleng.sl.fireemergency.mvp.widget.view.MyGridView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
@SuppressLint("InflateParams")
public class HomeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private View view;
    private MyGridView itemGrid;
    private FunctionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        view = getView();
        initView();

    }

    public void initView() {
        itemGrid = view.findViewById(R.id.item_grid);
        adapter = new FunctionAdapter(getActivity(), R.array.myfunction_home,
                R.array.myfunction_home_icon);
        itemGrid.setAdapter(adapter);
        itemGrid.setOnItemClickListener(this);

        Banner banner = (Banner) view.findViewById(R.id.banner);
        //本地图片数据（资源文件）
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.banner);
//        list.add(R.mipmap.banner);
//        list.add(R.mipmap.banner);
        //本地数据（资源文件）
        List<String> titlelist = new ArrayList<>();
        titlelist.add("南京交通职业技术学院智慧消防");
//        titlelist.add("智慧消防知识");
//        titlelist.add("智慧消防大数据");
        banner.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)  //设置banner样式
                .setBannerAnimation(Transformer.Default) //设置banner动画效果
                .isAutoPlay(true)//设置自动轮播，默认为true
                .setDelayTime(2000)//设置轮播时间
                .setBannerTitles(titlelist)//设置标题集合（当banner样式有显示title时）
                .setIndicatorGravity(BannerConfig.CENTER) //设置指示器位置（当banner模式中有指示器时）
                .start();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            //电气火灾
            case 0:
                Intent intent_RealTimeData = new Intent(getActivity(), RealDataActivity.class);
                startActivity(intent_RealTimeData);
                break;
            // 水系统
            case 1:
                Intent WaterSystemintent = new Intent(getActivity(), WaterSystemActivity.class);
                startActivity(WaterSystemintent);
                break;
            // 燃气监测
            case 2:
                Intent GasMonitoring = new Intent(getActivity(), GasMonitoringActivity.class);
                startActivity(GasMonitoring);
                break;
            // 火灾报警
            case 3:
                Intent HostMonitoring = new Intent(getActivity(), MonitoringActivity.class);
                startActivity(HostMonitoring);
                break;
            // 视频监控
            case 4:
                Intent Video = new Intent(getActivity(), VideoActivity.class);
                startActivity(Video);
                break;
            //巡查记录
            case 5:
                Intent PatrolHandle = new Intent(getActivity(), PatrolHandleActivity.class);
                startActivity(PatrolHandle);
                break;
            // 巡查点扫描
            case 6:
                Intent NfcPoint = new Intent(getActivity(), NfcPointActivity.class);
                NfcPoint.putExtra("type", "巡查");
                NfcPoint.putExtra("Ids", "");
                startActivity(NfcPoint);
                break;
            // 巡查点位
            case 7:
                Intent DevicePoint = new Intent(getActivity(), DevicePointActivity.class);
                startActivity(DevicePoint);
                break;
            // 隐患整改
            case 8:
                Intent Rectification = new Intent(getActivity(), RectificationActivity.class);
                startActivity(Rectification);
                break;
            default:
                break;
        }
    }
}
