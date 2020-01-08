package com.sanleng.sl.fireemergency.mvp.presenter;

import android.content.Context;

import com.sanleng.sl.fireemergency.mvp.bean.Gas;
import com.sanleng.sl.fireemergency.mvp.http.Http;
import com.sanleng.sl.fireemergency.mvp.http.Request_Interface;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.GasContract;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GasPresenter {
    //水系统列表
    public static void getGas(final GasContract gasContract, final Context context) {
        final List<Gas.PageBean.ListBean> list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<Gas> call = request_Interface.getGasCalls(PreferenceUtils.getString(context,"unitcode"));
        call.enqueue(new Callback<Gas>() {
            @Override
            public void onResponse(Call<Gas> call, Response<Gas> response) {
                try {
                    String state=response.body().getState();
                    if(state.equals("ok")){
                        int size = response.body().getPage().getTotalCount();
                        for (int i = 0; i < response.body().getPage().getList().size(); i++) {
                            Gas.PageBean.ListBean bean = new Gas.PageBean.ListBean();
                            String address = response.body().getPage().getList().get(i).getOwned_building_name() + response.body().getPage().getList().get(i).getFloor_number() + response.body().getPage().getList().get(i).getPosition();
                            String current_value =response.body().getPage().getList().get(i).getCurrent_value();
                            bean.setPosition(address);
                            bean.setCurrent_value(current_value);
                            list.add(bean);
                        }
                        gasContract.GasSuccess(list, size);
                    }else{
                        //登录超时
                        gasContract.GasFailed();
                    }
                }catch (NullPointerException e) {
                    e.printStackTrace();
                    gasContract.Timeout();
                }
            }

            @Override
            public void onFailure(Call<Gas> call, Throwable t) {
                gasContract.GasFailed();
            }
        });
    }


}
