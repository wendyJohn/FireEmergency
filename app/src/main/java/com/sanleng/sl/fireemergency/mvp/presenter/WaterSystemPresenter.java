package com.sanleng.sl.fireemergency.mvp.presenter;

import android.content.Context;

import com.sanleng.sl.fireemergency.mvp.bean.WaterSystem;
import com.sanleng.sl.fireemergency.mvp.bean.WaterSystemStatistics;
import com.sanleng.sl.fireemergency.mvp.http.Http;
import com.sanleng.sl.fireemergency.mvp.http.Request_Interface;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.WaterSystemContract;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WaterSystemPresenter {
    //水系统列表
    public static void getWaterSystem(final WaterSystemContract waterSystemContract, final Context context, final String page) {
        final List<WaterSystem.PageBean.ListBean> list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<WaterSystem> call = request_Interface.getWaterSystemCalls(page, "10", PreferenceUtils.getString(context,"unitcode"));
        call.enqueue(new Callback<WaterSystem>() {
            @Override
            public void onResponse(Call<WaterSystem> call, Response<WaterSystem> response) {
                try {
                    String states = response.body().getState();
                    if (states.equals("ok")) {
                        int size = response.body().getPage().getTotalCount();
                        for (int i = 0; i < response.body().getPage().getList().size(); i++) {
                            WaterSystem.PageBean.ListBean bean = new WaterSystem.PageBean.ListBean();
                            String ids = response.body().getPage().getList().get(i).getIds();
                            String device_name = response.body().getPage().getList().get(i).getDevice_name();
//                          String build_name = response.body().getPage().getList().get(i).getBuild_name();
                            String device_address = response.body().getPage().getList().get(i).getDevice_address();
                            String current_state = response.body().getPage().getList().get(i).getCurrent_state();
                            String range_min = response.body().getPage().getList().get(i).getRange_min();
                            String range_max = response.body().getPage().getList().get(i).getRange_max();
                            String state = response.body().getPage().getList().get(i).getState();
                            String device_type = response.body().getPage().getList().get(i).getDevice_type();

                            float  current_states = (Float.parseFloat(current_state))/1000;
                            float  range_mins = (Float.parseFloat(range_min))/1000;
                            float  range_maxs = (Float.parseFloat(range_max))/1000;

                            bean.setIds(ids);
                            bean.setDevice_name(device_name);
//                          bean.setBuild_name(build_name);
                            bean.setDevice_address(device_address);
                            bean.setCurrent_state(current_states+"");
                            bean.setRange_max(range_maxs+"");
                            bean.setRange_min(range_mins+"");
                            bean.setState(state);
                            bean.setDevice_type(device_type);
                            list.add(bean);
                        }
                        waterSystemContract.WaterSystemSuccess(list, size);
                    } else {
                        //登录超时
                        waterSystemContract.WaterSystemFailed();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    waterSystemContract.Timeout();
                }
            }

            @Override
            public void onFailure(Call<WaterSystem> call, Throwable t) {
                waterSystemContract.WaterSystemFailed();
            }
        });
    }

    //异常数量统计
    public static void getWaterSystemStatistics(final WaterSystemContract waterSystemContract, final Context context) {
        Retrofit retrofits = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interfaces = retrofits.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<WaterSystemStatistics> calls = request_Interfaces.getWaterSystemStatisticsCalls(PreferenceUtils.getString(context,"unitcode"));
        calls.enqueue(new Callback<WaterSystemStatistics>() {
            @Override
            public void onResponse(Call<WaterSystemStatistics> call, Response<WaterSystemStatistics> response) {
                if(response.body().getMsg().equals("首页统计查询成功")) {
                    int hyrant = response.body().getData().getHyrant();
                    int eqt = response.body().getData().getEqt();
                    int water = response.body().getData().getWater();
                    waterSystemContract.WaterSystemNumberSuccess(hyrant, eqt, water);
                }else{
                    waterSystemContract.WaterSystemFailed();
                }
            }

            @Override
            public void onFailure(Call<WaterSystemStatistics> call, Throwable t) {
                waterSystemContract.WaterSystemFailed();
            }
        });
    }


}
