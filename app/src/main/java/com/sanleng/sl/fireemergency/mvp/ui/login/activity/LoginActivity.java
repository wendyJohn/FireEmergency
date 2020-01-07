package com.sanleng.sl.fireemergency.mvp.ui.login.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.base.BaseActivity;
import com.sanleng.sl.fireemergency.mvp.presenter.LoginPresenter;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.LoginContract;
import com.sanleng.sl.fireemergency.mvp.ui.MainActivity;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;
import com.sanleng.sl.fireemergency.mvp.util.RSAUtils;
import com.sanleng.sl.fireemergency.mvp.util.StringUtils;
import com.sanleng.sl.fireemergency.mvp.widget.dialog.PromptDialog;

/**
 * 登陆界面
 * 2019/1/9
 *
 * @author qiaoshi
 */
public class LoginActivity extends BaseActivity implements OnClickListener, LoginContract {
    private EditText login_number;
    private EditText login_password;
    private Button login_btn;
    private String userName;
    private String password;
    private String lastAccount;
    private String lastPwd;
    private CheckBox whether_contact;
    private PromptDialog promptDialog;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initView() {
        // 创建对象
        promptDialog = new PromptDialog(this);
        // 设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(2000);
        login_btn = findViewById(R.id.login_btn);
        login_number = findViewById(R.id.login_number);
        login_password = findViewById(R.id.login_password);
        login_btn.setOnClickListener(this);
        login_password.setOnClickListener(this);
//        controlKeyboardLayout(scrollviewRootLayout, login_btn);
        whether_contact = findViewById(R.id.whether_contact);
        whether_contact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    PreferenceUtils.setInt(LoginActivity.this, "state", 1);
                } else {
                    PreferenceUtils.setInt(LoginActivity.this, "state", 0);
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        login_number.setText(PreferenceUtils.getString(this, "FireEmergency_username"));
        int state = PreferenceUtils.getInt(LoginActivity.this, "state");
        if (state == 1) {
            // 记住上次登录的信息
            lastAccount = PreferenceUtils.getString(this, "FireEmergency_usernames");
            if (!StringUtils.isEmpty(lastAccount)) {
                login_number.setText(lastAccount);
                login_password.setText(lastPwd);
                Intent intent_pwdchange = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent_pwdchange);
                finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                userName = login_number.getText().toString().trim();
                password = login_password.getText().toString().trim();
                String psaa= RSAUtils.encryptByPublic(password);
                System.out.println("===========加密=========="+psaa);
                if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
                    promptDialog.showLoading("正在登录...");
                    LoginPresenter.GetLogin(LoginActivity.this, getApplicationContext(), userName, psaa);
                } else {
                    Toast.makeText(LoginActivity.this, "输入的用户和密码不能为空", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void LoginSuccess(String msg) {
        if (msg.equals("登录成功")) {
            promptDialog.showSuccess("登录成功");
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    // 等待1000毫秒后销毁此页面，并提示登陆成功
                    Intent intent_pwdchange = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent_pwdchange);
                    finish();
                }
            }, 1000);
        } else {
            promptDialog.showError(msg);
        }
    }

    @Override
    public void LoginFailed() {
        promptDialog.showError("登录失败");
    }
}
