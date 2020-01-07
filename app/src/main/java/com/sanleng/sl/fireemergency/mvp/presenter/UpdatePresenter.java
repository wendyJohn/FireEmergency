package com.sanleng.sl.fireemergency.mvp.presenter;


import android.content.Context;

import com.sanleng.sl.fireemergency.mvp.bean.Version;
import com.sanleng.sl.fireemergency.mvp.http.Http;
import com.sanleng.sl.fireemergency.mvp.http.Request_Interface;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.UpdateContract;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 版本更新
 */
public class UpdatePresenter {
    //获取版本号与下载链接
    public static void GetVersion(final UpdateContract updateContract, final Context context, final String platformkey) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<Version> call = request_Interface.getVersionCall(platformkey, "0", "10");
        call.enqueue(new Callback<Version>() {
            @Override
            public void onResponse(Call<Version> call, Response<Version> response) {
                try {
                    for (int i = 0; i < response.body().getData().getContent().size(); i++) {
                        updateContract.UpdateSuccess(response.body().getData().getContent().get(i).getAppVersion(), response.body().getData().getContent().get(i).getDownloadUrl(), response.body().getData().getContent().get(i).getAppDescribe());
                        PreferenceUtils.setString(context, "download", "http://" + response.body().getData().getContent().get(i).getDownloadUrl());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Version> call, Throwable t) {

            }
        });
    }
}