package com.sanleng.sl.fireemergency.mvp.ui.mine.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.base.BaseActivity;
import com.sanleng.sl.fireemergency.mvp.presenter.PassPresenter;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.PassContract;
import com.sanleng.sl.fireemergency.mvp.ui.login.activity.LoginActivity;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;
import com.sanleng.sl.fireemergency.mvp.util.RSAUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PwdChangeActivity extends BaseActivity implements OnClickListener , PassContract {

    private EditText originalpassword;// 原密码
    private EditText newpassword;// 新密码
    private EditText confirmnewpassword;// 重复新密码
    private Button btn_passwordmodification;// 确定
    private RelativeLayout task_ac_back;
    private SweetAlertDialog sweetAlertDialog;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initListener();
    }

    @Override
    public int getLayoutId() {
        return R.layout.passwordactivity;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initView() {
        originalpassword =findViewById(R.id.originalpassword);
        newpassword =  findViewById(R.id.newpassword);
        confirmnewpassword =  findViewById(R.id.confirmnewpassword);
        btn_passwordmodification = findViewById(R.id.btn_passwordmodification);
        task_ac_back =  findViewById(R.id.task_ac_back);
    }

    @Override
    protected void initData() {

    }

    private void initListener() {
        btn_passwordmodification.setOnClickListener(this);
        task_ac_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_passwordmodification:
                doChangePwd();
                break;
            case R.id.task_ac_back:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 发送修改密码请求
     */
    private void doChangePwd() {
        String pwd = originalpassword.getText().toString().trim();
        String newpwd = newpassword.getText().toString().trim();
        String renewpwd = confirmnewpassword.getText().toString().trim();
        String psaa= RSAUtils.encryptByPublic(pwd);
        String psab= RSAUtils.encryptByPublic(newpwd);
        String psac= RSAUtils.encryptByPublic(renewpwd);
        System.out.println("===========加密=========="+psaa);
        if (isEquale(pwd, newpwd, renewpwd)) {
            return;
        }

        PassPresenter.getPasswork(PwdChangeActivity.this,getApplicationContext(),psaa,psab,psac);

    }


    @Override
    public void Success(String msg) {
        if (msg.equals("修改成功")) {
            sweetAlertDialog = new SweetAlertDialog(PwdChangeActivity.this, SweetAlertDialog.WARNING_TYPE);
            sweetAlertDialog.setContentText("密码修改成功,请重新登录！");
            sweetAlertDialog.setConfirmText("重新登录");
            sweetAlertDialog.setCancelable(false);
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.dismissWithAnimation();
                    // 清空sharepre中的用户名和密码
                    PreferenceUtils.setString(PwdChangeActivity.this, "FireEmergency_usernames", "");
                    Intent loginOutIntent = new Intent(PwdChangeActivity.this, LoginActivity.class);
                    loginOutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginOutIntent);
                    finish();
                }
            });
            sweetAlertDialog.show();
        }
        if (msg.equals("旧密码不正确")) {
            new SweetAlertDialog(PwdChangeActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("旧密码不正确")
                    .show();
        }
        if (msg.equals("用户不存在")) {
            new SweetAlertDialog(PwdChangeActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("用户不存在")
                    .show();
        }
    }

    @Override
    public void Failed() {
        new SweetAlertDialog(PwdChangeActivity.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("温馨提示")
                .setContentText("服务器访问异常!")
                .show();
    }

    /**
     * 检测密码是否一致，是否为空
     *
     * @param pwd
     * @param newpwd
     * @param renewpwd
     * @return true 不合法
     */
    private boolean isEquale(String pwd, String newpwd, String renewpwd) {
        boolean flag = false;
        if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(newpwd) || TextUtils.isEmpty(renewpwd)) {
            Toast.makeText(PwdChangeActivity.this, "信息输入不完整", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (!newpwd.equals(renewpwd)) {
            Toast.makeText(PwdChangeActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return true;
        }
        return flag;
    }
}
