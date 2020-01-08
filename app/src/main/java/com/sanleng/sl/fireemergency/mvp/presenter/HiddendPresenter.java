package com.sanleng.sl.fireemergency.mvp.presenter;

import android.content.Context;

import com.sanleng.sl.fireemergency.mvp.bean.ARectBean;
import com.sanleng.sl.fireemergency.mvp.bean.ReadTimeItems;
import com.sanleng.sl.fireemergency.mvp.bean.RealDataBean;
import com.sanleng.sl.fireemergency.mvp.bean.RealtimeData;
import com.sanleng.sl.fireemergency.mvp.bean.RectificationBean;
import com.sanleng.sl.fireemergency.mvp.http.Http;
import com.sanleng.sl.fireemergency.mvp.http.Request_Interface;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.AiddendContract;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.HiddendContract;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.RealItemsContract;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.RealTimeData;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HiddendPresenter {

    //未整改隐患
    public static void getSHiddend(final HiddendContract hiddendContract, final Context context, final String pageNum) {
        final List<RectificationBean.DataBean.ListBean> mylist = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<RectificationBean> call = request_Interface.getSHiddenCall(pageNum, "10", PreferenceUtils.getString(context, "unitcode"),PreferenceUtils.getString(context, "FireEmergency_username"),"0","0","");
        call.enqueue(new Callback<RectificationBean>() {
            @Override
            public void onResponse(Call<RectificationBean> call, Response<RectificationBean> response) {
                try {
                    int size= response.body().getData().getTotal();
                    for (int i = 0; i <response.body().getData().getList().size() ; i++) {
                        RectificationBean.DataBean.ListBean bean=new RectificationBean.DataBean.ListBean();
                        String ids = response.body().getData().getList().get(i).getIds();
                        String labelnumber = response.body().getData().getList().get(i).getQrcode();
                        String devicename =  response.body().getData().getList().get(i).getFpEquipmentName();
                        String ownedBuildingName =  response.body().getData().getList().get(i).getOwnedBuildingName();
                        String floorNumber =  response.body().getData().getList().get(i).getFloorNumber();
                        String term =  response.body().getData().getList().get(i).getProcessingPeriod();
                        String describe =  response.body().getData().getList().get(i).getDescription();
                        String responsiblePerson =  response.body().getData().getList().get(i).getCreate_user();

                        bean.setIds(ids);
                        bean.setQrcode(labelnumber);
                        bean.setFpEquipmentName(devicename);
                        bean.setOwnedBuildingName(ownedBuildingName + "-" + floorNumber);
                        bean.setProcessingPeriod(term);
                        bean.setCreate_user(responsiblePerson);
                        bean.setDescription(describe);
                        mylist.add(bean);
                    }
                    hiddendContract.Success(mylist,size,"");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RectificationBean> call, Throwable t) {
                hiddendContract.Failed();
            }
        });
    }

    //已整改隐患
    public static void getAHiddend(final AiddendContract hiddendContract, final Context context, final String pageNum, final String scope) {
        final List<ARectBean.DataBean.ListBean> mylist = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<ARectBean> call = request_Interface.getAHiddenCall(pageNum, "10", PreferenceUtils.getString(context, "unitcode"),PreferenceUtils.getString(context, "FireEmergency_username"),"1","0",scope);
        call.enqueue(new Callback<ARectBean>() {
            @Override
            public void onResponse(Call<ARectBean> call, Response<ARectBean> response) {
                try {
                    int size= response.body().getData().getTotal();
                    for (int i = 0; i <response.body().getData().getList().size() ; i++) {
                        ARectBean.DataBean.ListBean bean=new ARectBean.DataBean.ListBean();
                        String labelnumber = response.body().getData().getList().get(i).getQrcode();
                        String devicename =  response.body().getData().getList().get(i).getFpEquipmentName();
                        String ownedBuildingName =  response.body().getData().getList().get(i).getOwnedBuildingName();
                        String floorNumber =  response.body().getData().getList().get(i).getFloorNumber();
                        String term =  response.body().getData().getList().get(i).getProcessTime();
                        String describe =  response.body().getData().getList().get(i).getFinished_description();
                        String responsiblePerson =  response.body().getData().getList().get(i).getFinished_user();

                        bean.setQrcode(labelnumber);
                        bean.setFpEquipmentName(devicename);
                        bean.setOwnedBuildingName(ownedBuildingName + "-" + floorNumber);
                        bean.setProcessingPeriod(term);
                        bean.setCreate_user(responsiblePerson);
                        bean.setDescription(describe);
                        mylist.add(bean);
                    }
                    hiddendContract.Success(mylist,size,scope);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    hiddendContract.Timeout();
                }
            }

            @Override
            public void onFailure(Call<ARectBean> call, Throwable t) {
                hiddendContract.Failed();
            }
        });
    }


}