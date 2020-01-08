package com.sanleng.sl.fireemergency.mvp.ui.electricalfire.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.base.BaseActivity;
import com.sanleng.sl.fireemergency.mvp.bean.ReadTimeItems;
import com.sanleng.sl.fireemergency.mvp.presenter.RealDataPresenter;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.RealItemsContract;
import com.sanleng.sl.fireemergency.mvp.ui.electricalfire.adapter.RealDataItemAdapter;
import com.sanleng.sl.fireemergency.mvp.ui.login.activity.LoginActivity;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import java.util.List;

import butterknife.ButterKnife;

/**
 * 智能电气火灾实时数据
 *
 * @author Qiaoshi
 */
public class RealDataItemActivity extends BaseActivity implements OnClickListener, RealItemsContract {
    private String devicename;
    private RelativeLayout back;
    private ListView dataitemlistviewa;
    private ListView dataitemlistviewb;
    private ListView dataitemlistviewc;
    private ListView dataitemlistviewd;
    private TextView e_name;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.realdataitemactivity);
        initView();

    }

    @Override
    public int getLayoutId() {
        return R.layout.realdataitemactivity;
    }

    @Override
    protected void initToolbar() {

    }

    //初始化
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        devicename= intent.getStringExtra("name");
        e_name = findViewById(R.id.e_name);
        e_name.setText(devicename);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        RealDataPresenter.getRealDataItem(RealDataItemActivity.this, getApplicationContext(), devicename);
    }

    @Override
    public void onResume() {
        handler.postDelayed(runnable, 10 * 3000);
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //要做的事情
            RealDataPresenter.getRealDataItem(RealDataItemActivity.this, getApplicationContext(), devicename);
            handler.postDelayed(this, 10 * 3000);//实现循环
        }
    };
    private Handler handler = new Handler();

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    @Override
    public void RealTimeDataFailed() {
        new SVProgressHUD(RealDataItemActivity.this).showErrorWithStatus("数据请求失败");
    }

    @Override
    public void RealItemSuccess(List<ReadTimeItems.PageBean.ListBean> list_temperature, List<ReadTimeItems.PageBean.ListBean> list_current, List<ReadTimeItems.PageBean.ListBean> list_residualcurrent, List<ReadTimeItems.PageBean.ListBean> list_voltage, String buildids, String floorids) {
        dataitemlistviewa = findViewById(R.id.dataitemlistviewa);
        dataitemlistviewb = findViewById(R.id.dataitemlistviewb);
        dataitemlistviewc = findViewById(R.id.dataitemlistviewc);
        dataitemlistviewd = findViewById(R.id.dataitemlistviewd);
        RealDataItemAdapter realDataItemAdaptera = new RealDataItemAdapter(RealDataItemActivity.this, list_temperature);
        dataitemlistviewa.setAdapter(realDataItemAdaptera);
        RealDataItemAdapter realDataItemAdapterb = new RealDataItemAdapter(RealDataItemActivity.this, list_residualcurrent);
        dataitemlistviewb.setAdapter(realDataItemAdapterb);
        RealDataItemAdapter realDataItemAdapterc = new RealDataItemAdapter(RealDataItemActivity.this, list_current);
        dataitemlistviewc.setAdapter(realDataItemAdapterc);
        RealDataItemAdapter realDataItemAdapterd = new RealDataItemAdapter(RealDataItemActivity.this, list_voltage);
        dataitemlistviewd.setAdapter(realDataItemAdapterd);
    }

    @Override
    public void Timeout() {
        // 清空sharepre中的用户名和密码
        new SVProgressHUD(getApplicationContext()).showInfoWithStatus("登录超时，请重新登录");
        new Handler().postDelayed(new Runnable() {
            public void run() {
                PreferenceUtils.setString(getApplicationContext(), "FireEmergency_usernames", "");
                Intent loginOutIntent = new Intent(getApplicationContext(), LoginActivity.class);
                loginOutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginOutIntent);
                finish();
            }
        }, 2000);
    }

}
