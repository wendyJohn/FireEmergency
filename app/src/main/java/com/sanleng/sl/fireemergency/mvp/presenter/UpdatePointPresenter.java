package com.sanleng.sl.fireemergency.mvp.presenter;

import android.content.Context;

import com.sanleng.sl.fireemergency.mvp.bean.NfcPoint;
import com.sanleng.sl.fireemergency.mvp.bean.UpdatePoint;
import com.sanleng.sl.fireemergency.mvp.http.Http;
import com.sanleng.sl.fireemergency.mvp.http.Request_Interface;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.IsbindContract;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.UpdatePointContract;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdatePointPresenter {
    //点位绑定卡号
    public static void geUpdatePoint(final UpdatePointContract updatePointContract, final Context context, final String qrcode, final String ids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<UpdatePoint> call = request_Interface.getUpdatePositionCall(PreferenceUtils.getString(context, "unitcode"), ids, qrcode, PreferenceUtils.getString(context, "FireEmergency_username"));
        call.enqueue(new Callback<UpdatePoint>() {
            @Override
            public void onResponse(Call<UpdatePoint> call, Response<UpdatePoint> response) {
                try {
                    String msg = response.body().getMsg();
                    updatePointContract.UpdateSuccess(msg);
                }catch (NullPointerException e) {
                    e.printStackTrace();
                    updatePointContract.Timeout();
                }
            }

            @Override
            public void onFailure(Call<UpdatePoint> call, Throwable t) {
                updatePointContract.UpdateFailed();
            }
        });
    }


}
