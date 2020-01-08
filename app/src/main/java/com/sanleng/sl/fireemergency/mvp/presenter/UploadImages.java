package com.sanleng.sl.fireemergency.mvp.presenter;

import android.content.Context;

import com.sanleng.sl.fireemergency.mvp.bean.ImageUpload;
import com.sanleng.sl.fireemergency.mvp.http.Http;
import com.sanleng.sl.fireemergency.mvp.http.Request_Interface;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.PatroContract;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadImages {
    public static void uploadImage(PatroContract patroContract, final Context context, List<String> compressFile, String scrode, String ids, String stuas, String desc, String person, String time) {
        //多张图片
        List<MultipartBody.Part> list = new ArrayList<>();
        if (compressFile.size() > 0) {
            for (int i = 0; i < compressFile.size(); i++) {
                final RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), new File(compressFile.get(i)));
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", new File(compressFile.get(i)).getName(), requestFile);
                list.add(body);
            }
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<ImageUpload> call = request_Interface.UploadImages(list, scrode, ids, stuas, PreferenceUtils.getString(context, "FireEmergency_username"), desc, person, time);


        call.enqueue(new Callback<ImageUpload>() {
            @Override
            public void onResponse(Call<ImageUpload> call, Response<ImageUpload> response) {
                try {
                    String msg = response.body().getMsg();
                    if (msg.equals("巡查记录上传成功")) {
                        patroContract.Success();
                    } else {
                        patroContract.Failed();
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                    patroContract.Timeout();
                }
            }

            @Override
            public void onFailure(Call<ImageUpload> call, Throwable t) {
                patroContract.Failed();
            }
        });
    }
}
