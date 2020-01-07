package com.sanleng.sl.fireemergency.mvp.base;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.ui.MyApplication;
import com.sanleng.sl.fireemergency.mvp.util.GeneralUtil;
import com.sanleng.sl.fireemergency.mvp.util.KeyBoardUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * BaseActivity父类
 */
public abstract class BaseActivity extends AppCompatActivity {

    private String TAG;
    public Context context;
    private SystemBarTintManager tintManager;
    private MyApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        context = this;
        ButterKnife.bind(this);
        TAG = this.getClass().getSimpleName();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            setTranslucentStatus(getResources().getColor(R.color.colorPrimaryDark));
        initView();
        initToolbar();
        initData();
        application = (MyApplication) context.getApplicationContext();
        application.addActivity(this);
        checkPermission(context);
    }

    /**
     * 设置状态栏背景状态
     * <p/>
     * 4.4以上，5.0以下
     */
    public void setTranslucentStatus(int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintColor(color);
    }

    /**
     * Base基本类
     */
    public abstract int getLayoutId();

    /**
     * 设置toolbar
     */
    protected abstract void initToolbar();

    /**
     * 设置initView
     */
    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        application = null;
    }

    public View getLoadingTargetView() {
        return null;
    }

    /**
     * 沉浸式状态栏：着色
     */
    public void titlestatusbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            Window window = getWindow();
            ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);

            //First translucent status bar.
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int statusBarHeight = getStatusBarHeight(getApplicationContext());

            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                //如果已经为 ChildView 设置过了 marginTop, 再次调用时直接跳过
                if (lp != null && lp.topMargin < statusBarHeight && lp.height != statusBarHeight) {
                    //不预留系统空间
                    ViewCompat.setFitsSystemWindows(mChildView, false);
                    lp.topMargin += statusBarHeight;
                    mChildView.setLayoutParams(lp);
                }
            }
            View statusBarView = mContentView.getChildAt(0);
            if (statusBarView != null && statusBarView.getLayoutParams() != null && statusBarView.getLayoutParams().height == statusBarHeight) {
                //避免重复调用时多次添加 View
                statusBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                return;
            }
            statusBarView = new View(getApplicationContext());
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
            statusBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));

            //向 ContentView 中添加假 View
            mContentView.addView(statusBarView, 0, lp);
        }
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void checkPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissionStrs = new ArrayList<>();
            int hasWriteSdcardPermission = ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasWriteSdcardPermission != PackageManager.PERMISSION_GRANTED) {
                permissionStrs.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            int hasCameraPermission = ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA);
            if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
                permissionStrs.add(android.Manifest.permission.CAMERA);
            }
            String[] stringArray = permissionStrs.toArray(new String[0]);
            if (permissionStrs.size() > 0) {
                requestPermissions(stringArray, MyApplication.REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }
    }

    /**
     * 点击空白处影藏输入法
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (GeneralUtil.isHideInput(view, ev)) {
                KeyBoardUtils.HideSoftInput(view.getWindowToken(),
                        getApplicationContext());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    //权限设置后的回调函数，判断相应设置，requestPermissions传入的参数为几个权限，则permissions和grantResults为对应权限和设置结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MyApplication.REQUEST_CODE_ASK_PERMISSIONS:
                //可以遍历每个权限设置情况
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //这里写你需要相关权限的操作
                } else {
                    Toast.makeText(context, "权限没有开启", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    public void controlKeyboardLayout(final View root, final View scrollToView) {
        try {

            root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    Rect rect = new Rect();
                    // 获取root在窗体的可视区域
                    root.getWindowVisibleDisplayFrame(rect);
                    // 获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                    int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                    // 若不可视区域高度大于100，则键盘显示
                    if (rootInvisibleHeight > 100) {
                        int[] location = new int[2];
                        // 获取scrollToView在窗体的坐标
                        scrollToView.getLocationInWindow(location);
                        // 计算root滚动高度，使scrollToView在可见区域
                        int srollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                        root.scrollTo(0, srollHeight);
                    } else {
                        // 键盘隐藏
                        root.scrollTo(0, 0);
                    }
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
