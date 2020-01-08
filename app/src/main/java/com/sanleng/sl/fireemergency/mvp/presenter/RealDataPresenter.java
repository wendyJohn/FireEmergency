package com.sanleng.sl.fireemergency.mvp.presenter;

import android.content.Context;

import com.sanleng.sl.fireemergency.mvp.bean.ReadTimeItems;
import com.sanleng.sl.fireemergency.mvp.bean.RealDataBean;
import com.sanleng.sl.fireemergency.mvp.bean.RealtimeData;
import com.sanleng.sl.fireemergency.mvp.http.Http;
import com.sanleng.sl.fireemergency.mvp.http.Request_Interface;
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

public class RealDataPresenter {

    //设备列表
    public static void
    getRealTimeData(final RealTimeData realTimeData, final Context context, final String pageNum) {
        final List<RealDataBean> mylist = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<RealtimeData> call = request_Interface.getReadtimeCall(pageNum, "10", PreferenceUtils.getString(context, "unitcode"));
        call.enqueue(new Callback<RealtimeData>() {
            @Override
            public void onResponse(Call<RealtimeData> call, Response<RealtimeData> response) {
                try {
                    if (response.body().getMsg().equals("获取成功")) {
                        int size = response.body().getData().getTotal();
                        for (int i = 0; i < response.body().getData().getList().size(); i++) {
                            RealDataBean bean = new RealDataBean();
                            String device_id = response.body().getData().getList().get(i).getDevice_id();
                            String build_name = response.body().getData().getList().get(i).getBuild_name();
                            String device_name = response.body().getData().getList().get(i).getDevice_name();
                            String state = response.body().getData().getList().get(i).getState();
                            String contact_name = response.body().getData().getList().get(i).getContact_name();
                            String contact_tel = response.body().getData().getList().get(i).getContact_tel();
                            bean.setId(device_id);
                            bean.setAddress(build_name + "\n" + "名称:" + device_name);
                            bean.setContact_name(contact_name);
                            bean.setContact_tel(contact_tel);
                            bean.setState(state);
                            bean.setDevicename(device_name);
                            mylist.add(bean);
                        }
                        realTimeData.RealTimeDataSuccess(mylist, size);
                    } else {
                        realTimeData.RealTimeDataFailed();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    realTimeData.Timeout();
                }
            }

            @Override
            public void onFailure(Call<RealtimeData> call, Throwable t) {
                realTimeData.RealTimeDataFailed();
            }
        });
    }

    //设备实时数据
    public static void getRealDataItem(final RealItemsContract realItemsContract, final Context context, final String name) {
        final List<ReadTimeItems.PageBean.ListBean> list_temperature = new ArrayList<>();
        final List<ReadTimeItems.PageBean.ListBean> list_current = new ArrayList<>();
        final List<ReadTimeItems.PageBean.ListBean> list_residualcurrent = new ArrayList<>();
        final List<ReadTimeItems.PageBean.ListBean> list_voltage = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.HOST) // 设置 网络请求 Url
                .client(Request_Interface.genericClient(context))
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<ReadTimeItems> call = request_Interface.getReadtimeItemCall("20", "1", PreferenceUtils.getString(context, "unitcode"), name);
        call.enqueue(new Callback<ReadTimeItems>() {
            @Override
            public void onResponse(Call<ReadTimeItems> call, Response<ReadTimeItems> response) {
                if (response.body().getState().equals("ok")) {
                    String buildids = null;
                    String floorids = null;
                    for (int i = 0; i < response.body().getPage().getList().size(); i++) {
                        buildids = response.body().getPage().getList().get(i).getBuildids();
                        floorids = response.body().getPage().getList().get(i).getFloorids();
                        String detector_name = response.body().getPage().getList().get(i).getDetector_name();
                        String current_value = response.body().getPage().getList().get(i).getCurrent_value();
                        String detector_port = response.body().getPage().getList().get(i).getDetector_port();
                        String upper_limit = response.body().getPage().getList().get(i).getUpper_limit();
                        String lower_limit = response.body().getPage().getList().get(i).getLower_limit();

                        if (detector_port.equals("akwd")) {
                            detector_port = "A口";
                        }
                        if (detector_port.equals("bkwd")) {
                            detector_port = "B口";
                        }
                        if (detector_port.equals("ckwd")) {
                            detector_port = "C口";
                        }
                        if (detector_port.equals("dkwd")) {
                            detector_port = "D口";
                        }
                        if (detector_port.equals("sydl")) {
                            detector_port = "A口";
                        }

                        if (detector_name.equals("temperature_detector")) {
                            ReadTimeItems.PageBean.ListBean beana = new ReadTimeItems.PageBean.ListBean();
                            beana.setDetector_name(detector_name);
                            beana.setCurrent_value(current_value);
                            beana.setDetector_port(detector_port);
                            beana.setLower_limit(lower_limit);
                            beana.setUpper_limit(upper_limit);
                            list_temperature.add(beana);
                        }
                        if (detector_name.equals("residualcurrent_detector")) {
                            ReadTimeItems.PageBean.ListBean beanb = new ReadTimeItems.PageBean.ListBean();
                            beanb.setDetector_name(detector_name);
                            beanb.setCurrent_value(current_value);
                            beanb.setDetector_port(detector_port);
                            beanb.setLower_limit(lower_limit);
                            beanb.setUpper_limit(upper_limit);
                            list_current.add(beanb);
                        }
                        if (detector_name.equals("electricity_detector")) {
                            ReadTimeItems.PageBean.ListBean beanc = new ReadTimeItems.PageBean.ListBean();
                            beanc.setDetector_name(detector_name);
                            beanc.setCurrent_value(current_value);
                            beanc.setDetector_port(detector_port);
                            beanc.setLower_limit(lower_limit);
                            beanc.setUpper_limit(upper_limit);
                            list_residualcurrent.add(beanc);
                        }
                        if (detector_name.equals("voltage_detector")) {
                            ReadTimeItems.PageBean.ListBean beand = new ReadTimeItems.PageBean.ListBean();
                            beand.setDetector_name(detector_name);
                            beand.setCurrent_value(current_value);
                            beand.setDetector_port(detector_port);
                            beand.setLower_limit(lower_limit);
                            beand.setUpper_limit(upper_limit);
                            list_voltage.add(beand);
                        }
                    }
                    realItemsContract.RealItemSuccess(list_temperature, list_current, list_residualcurrent, list_voltage, floorids, buildids);
                } else {
                    realItemsContract.RealTimeDataFailed();
                }
            }

            @Override
            public void onFailure(Call<ReadTimeItems> call, Throwable t) {
                realItemsContract.RealTimeDataFailed();
            }
        });
    }


}