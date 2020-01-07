package com.sanleng.sl.fireemergency.mvp.ui.alarm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.base.BaseFragment;
import com.sanleng.sl.fireemergency.mvp.bean.TabItem;
import com.sanleng.sl.fireemergency.mvp.widget.view.TabIndicatorView;
import com.sanleng.sl.fireemergency.mvp.widget.view.TabItemView;

import java.util.ArrayList;

public class AlarmFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private ListView alarmlistview;
    private TabIndicatorView indicatorView;
    private RelativeLayout search;

    private boolean whether;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alarmchemicals, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        view = getView();
        initView();
    }


    //初始化
    private void initView() {
        search= view.findViewById(R.id.search);
        search.setOnClickListener(this);
        alarmlistview = view.findViewById(R.id.alarmlistview);
        indicatorView = view.findViewById(R.id.tab_indicator_view);
        indicatorView.initTabs(getList(), new TabIndicatorView.OnTabItemCheckListener() {
            @Override
            public void onTabItemCheck(TabItemView itemTabView, int position) {
                if(position==0){//电气火灾
//                    Toast.makeText(getActivity(), "电气火灾", Toast.LENGTH_SHORT).show();
                }
                if(position==1){//水系统
//                    Toast.makeText(getActivity(), "水系统", Toast.LENGTH_SHORT).show();
                }
                if(position==2){//燃气报警
//                    Toast.makeText(getActivity(), "燃气报警", Toast.LENGTH_SHORT).show();
                }
            }
        });
        indicatorView.setDefaultCheckedPos(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 查询功能
            case R.id.search:
                if(whether==false){
//                    linyears.setVisibility(View.VISIBLE);
                    whether=true;
                    return;
                }
                if(whether==true){
//                    linyears.setVisibility(View.GONE);
                    whether=false;
                    return;
                }
                break;
            default:
                break;
        }
    }

    private ArrayList<TabItem> getList() {
        ArrayList<TabItem> list = new ArrayList<>();
        TabItem itema = new TabItem();
        itema.title = "电气火灾";
        itema.position=0;
        list.add(itema);
        TabItem itemb = new TabItem();
        itemb.title = "水系统";
        itemb.position=1;
        list.add(itemb);
        TabItem itemc = new TabItem();
        itemc.title = "燃气报警";
        itemc.position=2;
        list.add(itemc);
        return list;
    }


}
