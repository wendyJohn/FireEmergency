package com.sanleng.sl.fireemergency.mvp.ui.nfc.activity;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.base.BaseActivity;
import com.sanleng.sl.fireemergency.mvp.presenter.IsBindPresenter;
import com.sanleng.sl.fireemergency.mvp.presenter.UpdatePointPresenter;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.IsbindContract;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.UpdatePointContract;
import com.sanleng.sl.fireemergency.mvp.ui.login.activity.LoginActivity;
import com.sanleng.sl.fireemergency.mvp.ui.patrol.activity.PointPatrolActivity;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;
import com.sanleng.sl.fireemergency.mvp.util.Utils;

/**
 * 巡检点扫描
 *
 * @author Qiaoshi
 */

public class NfcPointActivity extends BaseActivity implements OnClickListener, IsbindContract, UpdatePointContract {
    private RelativeLayout r_back;
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private String type;
    private String ids;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public int getLayoutId() {
        return R.layout.nfcpointactivity;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initView() {
        r_back = findViewById(R.id.r_back);
        r_back.setOnClickListener(this);
        nfcinitview();
        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        ids=intent.getStringExtra("Ids");
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.r_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        try {
            mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    // NFC初始化
    private void nfcinitview() {
        Intent nfcIntent = new Intent(this, getClass());
        nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mPendingIntent = PendingIntent.getActivity(this, 0, nfcIntent, 0);
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mAdapter == null) {
            Toast.makeText(this, "该设备不支持NFC", Toast.LENGTH_SHORT).show();
            return;
        } else if (!mAdapter.isEnabled()) {
            Toast.makeText(this, "NFC功能没打开，请打开", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        getTagInfo(intent);
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
    private void getTagInfo(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        byte[] tagId = tag.getId();
        String str = Utils.ByteArrayToHexString(tagId);
        str = Utils.flipHexStr(str);
        System.out.println("======NFC卡号=======" + str);
        if(type.equals("巡查")){
            isBind(str);
        }
        if(type.equals("激活")){
            UpdatePointPresenter.geUpdatePoint(NfcPointActivity.this,getApplicationContext(),str,ids);
        }
    }

    //判断是否绑定
    private void isBind(String scode) {
        IsBindPresenter.getIsBind(NfcPointActivity.this, getApplicationContext(), scode);
    }


    @Override
    public void Success(String qrcodeCode,String equipmentName,String equipmentposition,String equipmentids) {
        Intent NfcPoint = new Intent(NfcPointActivity.this, PointPatrolActivity.class);
        NfcPoint.putExtra("labelnumber", qrcodeCode);
        NfcPoint.putExtra("devicename", equipmentName);
        NfcPoint.putExtra("equipmentposition", equipmentposition);
        NfcPoint.putExtra("equipmentids", equipmentids);
        startActivity(NfcPoint);
        finish();
    }

    @Override
    public void UpdateSuccess(String msg) {
        if(msg.equals("点位绑定成功")){
            new SVProgressHUD(NfcPointActivity.this).showSuccessWithStatus("激活成功");
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    finish();
                }
            }, 2000);
        }else{
            new SVProgressHUD(NfcPointActivity.this).showErrorWithStatus(msg);
        }
    }

    @Override
    public void UpdateFailed() {
        new SVProgressHUD(NfcPointActivity.this).showErrorWithStatus("激活失败");
    }

    @Override
    public void Failed() {
        new SVProgressHUD(NfcPointActivity.this).showErrorWithStatus("请求异常,请检查标签是否可用");
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