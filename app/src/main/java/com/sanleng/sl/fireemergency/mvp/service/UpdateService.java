package com.sanleng.sl.fireemergency.mvp.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.widget.RemoteViews;

import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.ui.MainActivity;
import com.sanleng.sl.fireemergency.mvp.ui.MyApplication;
import com.sanleng.sl.fireemergency.mvp.util.MessageEvent;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 更新APP
 */
public class UpdateService extends Service {
    private String apkurl;
    private String apkPath;
    private String apkName;
    private boolean canceled = false;
    private NotificationManager manager;
    private Notification notification;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
//            apkurl = intent.getStringExtra("apkurl");
//            apkName = splitString(apkurl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/update";
        setUpNotifiction();
        new Thread(new DownApkRunnable()).start();
    }

    /**
     * 创建通知
     */
    private void setUpNotifiction() {
        manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
        int icon = R.mipmap.ic_launcher;
        CharSequence tickerText = "开始下载更新";
        long when = System.currentTimeMillis();
        notification = new Notification(icon, tickerText, when);

        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notify_update_layout);
        contentView.setTextViewText(R.id.name, "南交消防应急正在下载中...");

        Intent canceledIntent = new Intent("canceled");
        canceledIntent.putExtra("canceled", "canceled");

        PendingIntent canceledPendingIntent = PendingIntent.getBroadcast(UpdateService.this, 1, canceledIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        // contentView.setOnClickPendingIntent(R.id.cancle, canceledPendingIntent);
        notification.contentView = contentView;
        Intent intent = new Intent(UpdateService.this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(UpdateService.this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notification.contentIntent = contentIntent;
        manager.notify(0, notification);// 发送通知
    }

    /**
     * 下载apk
     */
    class DownApkRunnable implements Runnable {
        @Override
        public void run() {
            MessageEvent messageEvent = new MessageEvent(MyApplication.MESSAGE_UPDATE);
            EventBus.getDefault().post(messageEvent);
            downloadApk();
        }
    }

    private int laterate = 0;

    private void downloadApk() {
        try {
            apkurl=PreferenceUtils.getString(this,"download");
            apkName = splitString(apkurl);
            URL url = new URL(apkurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int length = connection.getContentLength();
            int count = 0;
            File apkPathFile = new File(apkPath);
            if (!apkPathFile.exists()) {
                apkPathFile.mkdir();
            }
            File apkFile = new File(apkPath, apkName);
            InputStream in = connection.getInputStream();
            FileOutputStream os = new FileOutputStream(apkFile);
            byte[] buffer = new byte[1024];
            do {
                int numread = in.read(buffer);
                count += numread;
                int progress = (int) (((float) count / length) * 100);// 得到当前进度
                if (progress >= laterate + 1) {// 只有当前进度比上一次进度大于等于1，才可以更新进度
                    laterate = progress;
                    Message msg = new Message();
                    msg.what = 1;
                    msg.arg1 = progress;
                    handler.sendMessage(msg);

                    MessageEvent messageEvent = new MessageEvent(MyApplication.MESSAGE_PROGRESS);
                    messageEvent.setMessage(progress + "");
                    EventBus.getDefault().post(messageEvent);
                }
                if (numread <= 0) {// 下载完毕
                    MessageEvent messageEvent = new MessageEvent(MyApplication.MESSAGE_UPDATEOK);
                    EventBus.getDefault().post(messageEvent);
                    handler.sendEmptyMessage(2);
                    canceled = true;
                    break;
                }
                os.write(buffer, 0, numread);
            } while (!canceled);// 如果没有被取消
            in.close();
            os.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.toString();
            e.printStackTrace();
        }
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:// 更新进度
                    int progress = msg.arg1;
                    if (progress < 100) {
                        RemoteViews contentView = notification.contentView;
                        contentView.setTextViewText(R.id.tv_progress, progress + "%");
                        contentView.setProgressBar(R.id.progressbar, 100, progress, false);
                    } else {// 下载完成，停止服务
                        stopSelf();
                    }
                    manager.notify(0, notification);
                    break;
                case 2:// 安装apk
                    manager.cancel(0);
                    installApk();
                    break;

                default:
                    break;
            }
        }
    };

    /**
     * 安装apk
     */
    private void installApk() {
        File apkFile = new File(apkPath, apkName);
        if (!apkFile.exists()) {
            return;
        }
        Uri uri = null;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (Build.VERSION.SDK_INT >= 24) {//7.0 Android N
                //com.xxx.xxx.fileprovider为上述manifest中provider所配置相同
                uri = FileProvider.getUriForFile(this, "com.sanleng.dangerouscabinet.fileProvider", apkFile);
                intent.setAction(Intent.ACTION_INSTALL_PACKAGE);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//7.0以后，系统要求授予临时uri读取权限，安装完毕以后，系统会自动收回权限，该过程没有用户交互
            } else {//7.0以下
                uri = Uri.fromFile(apkFile);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            startActivity(intent);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String splitString(String url) {
        //取得最后一个/的下标
        int index = url.lastIndexOf("/");
        //将字符串转为字符数组
        char[] ch = url.toCharArray();
        //根据 copyValueOf(char[] data, int offset, int count) 取得最后一个字符串
        String informationId = String.copyValueOf(ch, index + 1, ch.length - index - 1);
        return informationId;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        canceled = true;
        manager.cancel(0);
        stopSelf();
    }
}
