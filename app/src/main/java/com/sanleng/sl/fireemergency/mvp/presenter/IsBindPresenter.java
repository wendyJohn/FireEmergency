package com.sanleng.sl.fireemergency.mvp.presenter;

import android.content.Context;

import com.sanleng.sl.fireemergency.mvp.bean.Gas;
import com.sanleng.sl.fireemergency.mvp.bean.NfcPoint;
import com.sanleng.sl.fireemergency.mvp.http.Http;
import com.sanleng.sl.fireemergency.mvp.http.Request_Interface;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.GasContract;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.IsbindContract;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IsBindPresenter {
    //水系统列表
    public static void getIsBind(final IsbindContract isbindContract, final Context context, final String qrcode) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<NfcPoint> call = request_Interface.getIsbindCall(PreferenceUtils.getString(context, "unitcode"), qrcode);
        call.enqueue(new Callback<NfcPoint>() {
            @Override
            public void onResponse(Call<NfcPoint> call, Response<NfcPoint> response) {
                try {
                    if(response.body().getData().getState().equals("1")) {
                        if (response.body().getMsg().equals("获取成功")) {
                            String qrcodeCode = response.body().getData().getEquipment().getQrcodeCode();
                            String equipmentName = response.body().getData().getEquipment().getEquipmentName();
                            String equipmentposition = response.body().getData().getEquipment().getOwnedBuilding() + response.body().getData().getEquipment().getFloorNumber();
                            String equipmentids = response.body().getData().getEquipment().getIds();
                            isbindContract.Success(qrcodeCode, equipmentName, equipmentposition, equipmentids);
                        } else {
                            isbindContract.Failed();
                        }
                    }else{
                        isbindContract.Failed();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    isbindContract.Timeout();
                }
            }

            @Override
            public void onFailure(Call<NfcPoint> call, Throwable t) {
                isbindContract.Failed();
            }
        });
    }


}
