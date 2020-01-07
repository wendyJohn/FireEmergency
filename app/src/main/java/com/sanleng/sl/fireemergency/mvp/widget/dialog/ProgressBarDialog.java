package com.sanleng.sl.fireemergency.mvp.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sanleng.sl.fireemergency.R;


/**
 * @author qiaoshi
 */
public class ProgressBarDialog extends Dialog {
    private Context context;
    private ProgressBar progressBar;
    private TextView tv_text;

    public ProgressBarDialog(Context context) {
        super(context);
    }

    public ProgressBarDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.progressbardialog);
        this.setCancelable(false);// 设置点击屏幕Dialog不消失
        progressBar = findViewById(R.id.progressBar);
        tv_text = findViewById(R.id.tv_text);
    }

    //更新进度条
    public void updateProgress(String progress) {
        progressBar.setProgress(Integer.parseInt(progress));
        tv_text.setText(progress+"%");
    }


}