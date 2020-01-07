package com.sanleng.sl.fireemergency.mvp.presenter;

import android.content.Context;

import com.sanleng.sl.fireemergency.mvp.bean.UploadRectifications;
import com.sanleng.sl.fireemergency.mvp.http.Http;
import com.sanleng.sl.fireemergency.mvp.http.Request_Interface;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.RectificationContract;
import com.sanleng.sl.fireemergency.mvp.util.Bimps;
import com.sanleng.sl.fireemergency.mvp.util.FileUtils;
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

public class UploadRectification {
    public static void uploadImage(RectificationContract rectificationContract, final Context context, String ids, List<String> compressFile, String desc) {
        //单张图片`
        final RequestBody requestFile =
                RequestBody.create(MediaType.parse("image/jpg"), new File(compressFile.get(0)));
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", new File(compressFile.get(0)).getName(), requestFile);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<UploadRectifications> call = request_Interface.UploadRectification(body, ids, PreferenceUtils.getString(context, "FireEmergency_username"), desc);
        call.enqueue(new Callback<UploadRectifications>() {
            @Override
            public void onResponse(Call<UploadRectifications> call, Response<UploadRectifications> response) {
                try {

                    if (response.body().getMsg().equals("处理成功")) {
                        rectificationContract.Success();
                        Bimps bimp = new Bimps();
                        bimp.bmp = new ArrayList<>();
                        bimp.drr = new ArrayList<>();
                        bimp.max = 0;
                        FileUtils.deleteDir();
                    } else {
                        rectificationContract.Failed();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UploadRectifications> call, Throwable t) {
                rectificationContract.Failed();
            }
        });
    }
}
