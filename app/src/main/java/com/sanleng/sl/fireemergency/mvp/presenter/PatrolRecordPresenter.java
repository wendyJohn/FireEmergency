package com.sanleng.sl.fireemergency.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.sanleng.sl.fireemergency.mvp.bean.PatrolRecordBean;
import com.sanleng.sl.fireemergency.mvp.bean.ReadTimeItems;
import com.sanleng.sl.fireemergency.mvp.bean.RealDataBean;
import com.sanleng.sl.fireemergency.mvp.bean.RealtimeData;
import com.sanleng.sl.fireemergency.mvp.http.Http;
import com.sanleng.sl.fireemergency.mvp.http.Request_Interface;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.PatrolRecord;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.RealItemsContract;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.RealTimeData;
import com.sanleng.sl.fireemergency.mvp.ui.login.activity.LoginActivity;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatrolRecordPresenter {

    //巡查记录
    public static void
    getPatrolRecord(final PatrolRecord patrolRecord, final Context context, final String pageNum) {
        final List<PatrolRecordBean.DataBean.ListBean> mylist = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<PatrolRecordBean> call = request_Interface.getPatrolRecordCall(pageNum, "10", PreferenceUtils.getString(context, "unitcode"));
        call.enqueue(new Callback<PatrolRecordBean>() {
            @Override
            public void onResponse(Call<PatrolRecordBean> call, Response<PatrolRecordBean> response) {
                try {
                    int size = response.body().getData().getTotal();

                    for (int i = 0; i < response.body().getData().getList().size(); i++) {
                        PatrolRecordBean.DataBean.ListBean bean = new PatrolRecordBean.DataBean.ListBean();

                        String equipment_name = response.body().getData().getList().get(i).getEquipment_name();
                        String create_user = response.body().getData().getList().get(i).getCreate_user();
                        String equipment_status = response.body().getData().getList().get(i).getEquipment_status();
                        String inspection_time= response.body().getData().getList().get(i).getInspection_time();

                        bean.setEquipment_name(equipment_name);
                        bean.setCreate_user(create_user);
                        bean.setInspection_time(inspection_time);
                        bean.setEquipment_status(equipment_status);

                        mylist.add(bean);
                    }
                    patrolRecord.PatrolRecordSuccess(mylist, size);

                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("=============登录超时请重新登录============");
                    patrolRecord.Timeout();
                }
            }

            @Override
            public void onFailure(Call<PatrolRecordBean> call, Throwable t) {
                patrolRecord.PatrolRecordFailed();
            }
        });
    }


}