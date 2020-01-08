package com.sanleng.sl.fireemergency.mvp.presenter;

import android.content.Context;

import com.sanleng.sl.fireemergency.mvp.bean.Password;
import com.sanleng.sl.fireemergency.mvp.http.Http;
import com.sanleng.sl.fireemergency.mvp.http.Request_Interface;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.PassContract;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PassPresenter {
    //密码修改
    public static void getPasswork(final PassContract passContract, final Context context, String oldwd, String newwd, String conwd) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<Password> call = request_Interface.getPasswordCall(oldwd,newwd,conwd,PreferenceUtils.getString(context,"FireEmergency_username"));
        call.enqueue(new Callback<Password>() {
            @Override
            public void onResponse(Call<Password> call, Response<Password> response) {
                try {
                    if(response.body().getStatus().equals("0")) {
                        passContract.Success(response.body().getMsg());
                    }
                }catch (NullPointerException e) {
                    e.printStackTrace();
                    passContract.Timeout();
                }
            }

            @Override
            public void onFailure(Call<Password> call, Throwable t) {
                passContract.Failed();
            }
        });
    }


}
