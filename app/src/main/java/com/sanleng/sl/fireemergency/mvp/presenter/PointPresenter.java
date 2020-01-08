package com.sanleng.sl.fireemergency.mvp.presenter;

import android.content.Context;

import com.sanleng.sl.fireemergency.mvp.bean.ARectBean;
import com.sanleng.sl.fireemergency.mvp.bean.Dpoint;
import com.sanleng.sl.fireemergency.mvp.http.Http;
import com.sanleng.sl.fireemergency.mvp.http.Request_Interface;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.DevicePointContract;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PointPresenter {
    //水系统列表
    public static void getPoint(final DevicePointContract devicePointContract, final Context context, final String pageNo) {
        final List<Dpoint.DataBean.ListBean> mylist = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<Dpoint> call = request_Interface.getPositionCall(pageNo,"10",PreferenceUtils.getString(context, "unitcode"));
        call.enqueue(new Callback<Dpoint>() {
            @Override
            public void onResponse(Call<Dpoint> call, Response<Dpoint> response) {
                try {
                    int size= response.body().getData().getTotal();
                    for (int i = 0; i <response.body().getData().getList().size() ; i++) {
                        Dpoint.DataBean.ListBean bean=new Dpoint.DataBean.ListBean();
                        String ids = response.body().getData().getList().get(i).getIds();
                        String equipmentName =  response.body().getData().getList().get(i).getEquipmentName();
                        String activateStatus =  response.body().getData().getList().get(i).getActivateStatus();
                        String ownedBuildingVal =  response.body().getData().getList().get(i).getOwnedBuildingVal();
                        String floorNumberVal =  response.body().getData().getList().get(i).getFloorNumberVal();

                        bean.setIds(ids);
                        bean.setEquipmentName(equipmentName);
                        bean.setActivateStatus(activateStatus);
                        bean.setOwnedBuildingVal(ownedBuildingVal);
                        bean.setFloorNumberVal(floorNumberVal);
                        mylist.add(bean);
                    }
                    devicePointContract.Success(mylist,size);
                }  catch (NullPointerException e) {
                    e.printStackTrace();
                    devicePointContract.Timeout();
                }
            }

            @Override
            public void onFailure(Call<Dpoint> call, Throwable t) {
                devicePointContract.Failed();
            }
        });
    }


}
