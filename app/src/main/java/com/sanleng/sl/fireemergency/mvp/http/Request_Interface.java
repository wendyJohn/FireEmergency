package com.sanleng.sl.fireemergency.mvp.http;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;

import com.sanleng.sl.fireemergency.mvp.bean.ARectBean;
import com.sanleng.sl.fireemergency.mvp.bean.Dpoint;
import com.sanleng.sl.fireemergency.mvp.bean.Gas;
import com.sanleng.sl.fireemergency.mvp.bean.ImageUpload;
import com.sanleng.sl.fireemergency.mvp.bean.NfcPoint;
import com.sanleng.sl.fireemergency.mvp.bean.Password;
import com.sanleng.sl.fireemergency.mvp.bean.PatrolRecordBean;
import com.sanleng.sl.fireemergency.mvp.bean.ReadTimeItems;
import com.sanleng.sl.fireemergency.mvp.bean.RealtimeData;
import com.sanleng.sl.fireemergency.mvp.bean.RectificationBean;
import com.sanleng.sl.fireemergency.mvp.bean.UpdatePoint;
import com.sanleng.sl.fireemergency.mvp.bean.UploadRectifications;
import com.sanleng.sl.fireemergency.mvp.bean.User;
import com.sanleng.sl.fireemergency.mvp.bean.Users;
import com.sanleng.sl.fireemergency.mvp.bean.Version;
import com.sanleng.sl.fireemergency.mvp.bean.WaterSystem;
import com.sanleng.sl.fireemergency.mvp.bean.WaterSystemStatistics;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Request_Interface {
    //==================================================================2.0新版本===========================================================================

    //登录
    @POST("/platform-basic/api/user/login")
    Call<User> getloginCalls(@Query("username") String username, @Query("passwd") String passwd);

    //获取用户信息
    @POST("/platform-basic/api/userapply/getUserByName")
    Call<Users> getUserCalls(@Query("name") String name);

    //水系统异常统计
    @POST("/sl-baseinfo-manager/api/app/water/count")
    Call<WaterSystemStatistics> getWaterSystemStatisticsCalls(@Query("unitcode") String unitcode);

    //获取水系统数据
    @GET("/sl-baseinfo-manager/api/ownerMonitorModuleInfo/list")
    Call<WaterSystem> getWaterSystemCalls(@Query("page") String page, @Query("limit") String limit, @Query("unitcode") String unitcode);

    //获取燃气监测数据
    @GET("/sl-baseinfo-manager/api/fireLocation/getFirelocationList")
    Call<Gas> getGasCalls(@Query("unitCode") String unitCode);

    //获取电气火灾设备列表
    @POST("/electricshield/api/app/electric/deviceList")
    Call<RealtimeData> getReadtimeCall(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("unit_id") String unit_id);

    //获取设备实时数据
    @POST("/electricshield/api/electricdetector/list")
    Call<ReadTimeItems> getReadtimeItemCall(@Query("limit") String limit, @Query("page") String page, @Query("unit_id") String unit_id, @Query("device_name") String device_name);

    //获取版本ID号
    @POST("/thirdpartypush/api/appversion/list")
    Call<Version> getVersionCall(@Query("platformkey") String platformkey, @Query("page") String page, @Query("size") String size);

    //获取巡查记录列表
    @GET("/sl-firepatrol/api/app/patrol/list")
    Call<PatrolRecordBean> getPatrolRecordCall(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("unitcode") String unitcode);

    //获取未隐患整改列表
    @GET("/sl-firepatrol/api/app/rectification/list")
    Call<RectificationBean> getSHiddenCall(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("unitcode") String unitcode, @Query("username") String username, @Query("state") String state, @Query("type") String type, @Query("scope") String scope);

    //获取已隐患整改列表
    @GET("/sl-firepatrol/api/app/rectification/list")
    Call<ARectBean> getAHiddenCall(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("unitcode") String unitcode, @Query("username") String username, @Query("state") String state, @Query("type") String type, @Query("scope") String scope);

    @Multipart
    @POST("/sl-firepatrol/api/app/patrol/upload")
    Call<ImageUpload> UploadImages(@Part List<MultipartBody.Part> file, @Query("qrcode") String qrcode, @Query("equipmentids") String equipmentids, @Query("status") String status, @Query("username") String username, @Query("desc") String desc, @Query("responsible_person") String responsible_person, @Query("processingPeriod") String processingPeriod);

    @Multipart
    @POST("/sl-firepatrol/api/app/rectification/upload")
    Call<UploadRectifications> UploadRectification(@Part MultipartBody.Part file, @Query("id") String id , @Query("username") String username, @Query("desc") String desc);

    //修改密码
    @POST("/platform-basic/api/user/updatepassword")
    Call<Password> getPasswordCall(@Query("oldpwd") String oldpwd, @Query("newpwd") String newpwd, @Query("confirmpwd") String confirmpwd, @Query("username") String username);

    //获取设备信息，并判断是否绑定
    @POST("/sl-firepatrol/api/app/fpequipment/isbind")
    Call<NfcPoint> getIsbindCall(@Query("unitcode") String unitcode, @Query("qrcode") String qrcode);

    //获取设备点位列表
    @GET("/sl-firepatrol/api/app/fpequipment/list")
    Call<Dpoint> getPositionCall(@Query("pageNum") String pageNum,@Query("pageSize") String pageSize,@Query("unitcode") String unitcode);

    //点位绑定标签号
    @POST("/sl-firepatrol/api/app/fpequipment/update")
    Call<UpdatePoint> getUpdatePositionCall(@Query("unitcode") String unitcode, @Query("equipmentId") String equipmentId, @Query("qrcodeCode") String qrcodeCode, @Query("username") String username);

    //统一添加文件头
    static OkHttpClient genericClient(Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.e("RetrofitLog", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()//okhttp设置部分，此处还可再设置网络参数
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Authorization", PreferenceUtils.getString(context, "JWT"))
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        return client;
    }
}
