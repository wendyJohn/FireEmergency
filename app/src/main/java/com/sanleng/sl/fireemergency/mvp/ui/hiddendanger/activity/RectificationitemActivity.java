package com.sanleng.sl.fireemergency.mvp.ui.hiddendanger.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.presenter.UploadRectification;
import com.sanleng.sl.fireemergency.mvp.presenter.contract.RectificationContract;
import com.sanleng.sl.fireemergency.mvp.ui.login.activity.LoginActivity;
import com.sanleng.sl.fireemergency.mvp.ui.patrol.activity.PhotoActivity;
import com.sanleng.sl.fireemergency.mvp.ui.patrol.activity.PointPatrolActivity;
import com.sanleng.sl.fireemergency.mvp.util.Bimps;
import com.sanleng.sl.fireemergency.mvp.util.FileUtils;
import com.sanleng.sl.fireemergency.mvp.util.PreferenceUtils;
import com.sanleng.sl.fireemergency.mvp.util.Premissions;
import com.sanleng.sl.fireemergency.mvp.util.StringUtils;
import com.sanleng.sl.fireemergency.mvp.widget.dialog.PromptDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 隐患整改办理
 *
 * @author qiaoshi
 */
public class RectificationitemActivity extends Activity implements OnClickListener, RectificationContract, Premissions.PermissionListener {
    private RelativeLayout task_ac_back;
    private Button commit_task;
    private GridView noScrollgridview;
    private GridAdapter adapter;
    public File tempFile;
    private PromptDialog promptDialog;

    private TextView labelnumber;
    private TextView devicename;
    private TextView equipmentposition;
    private TextView rectificationperiod;
    private TextView personliable;
    private TextView hiddendangerdescription;

    private String Ids;
    private String labelnumbers;
    private String devicenames;
    private String equipmentpositions;
    private String rectificationperiods;
    private String personliables;
    private String hiddendangerdescriptions;

    private EditText info_editText;

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int PERMISSON_REQUESTCODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hiddendrectificationactivity);
        initview();
    }

    // 初始化数据
    private void initview() {
        // 创建对象
        promptDialog = new PromptDialog(this);
        // 设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(2000);
        task_ac_back = findViewById(R.id.task_ac_back);
        task_ac_back.setOnClickListener(this);

        commit_task = findViewById(R.id.commit_task);
        commit_task.setOnClickListener(this);

        labelnumber = findViewById(R.id.labelnumber);
        devicename = findViewById(R.id.devicename);
        equipmentposition = findViewById(R.id.equipmentposition);
        rectificationperiod = findViewById(R.id.rectificationperiod);
        personliable = findViewById(R.id.personliable);
        hiddendangerdescription = findViewById(R.id.hiddendangerdescription);
        info_editText = findViewById(R.id.info_editText);

        noScrollgridview = findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridAdapter(this);
        adapter.update();
        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == Bimps.bmp.size()) {
                    new PopupWindows(RectificationitemActivity.this, noScrollgridview);
                } else {
                    Intent intent = new Intent(RectificationitemActivity.this, PhotoActivity.class);
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });
        Intent intent = getIntent();
        Ids = intent.getStringExtra("Ids");
        labelnumbers = intent.getStringExtra("labelnumber");
        devicenames = intent.getStringExtra("devicename");
        equipmentpositions = intent.getStringExtra("equipmentposition");
        rectificationperiods = intent.getStringExtra("rectificationperiod");
        personliables = intent.getStringExtra("personliable");
        hiddendangerdescriptions = intent.getStringExtra("hiddendangerdescription");

        labelnumber.setText(labelnumbers);
        devicename.setText(devicenames);
        equipmentposition.setText(equipmentpositions);
        rectificationperiod.setText(rectificationperiods);
        personliable.setText(personliables);
        hiddendangerdescription.setText(hiddendangerdescriptions);

    }

    // 提交
    public void doCommit() {
        String desc = info_editText.getText().toString().trim();
        if (Bimps.drr.size() > 0) {
            if (!StringUtils.isEmpty(desc)) {
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < Bimps.drr.size(); i++) {
                String Str = Bimps.drr.get(i).substring(Bimps.drr.get(i).lastIndexOf("/") + 1,
                        Bimps.drr.get(i).lastIndexOf("."));
                list.add(FileUtils.SDPATH + Str + ".JPEG");
            }
            UploadRectification.uploadImage(RectificationitemActivity.this, getApplicationContext(), Ids, list, desc);
            } else {
                new SVProgressHUD(RectificationitemActivity.this).showErrorWithStatus("巡查描述不能为空");
                return;
            }
        }else{
            new SVProgressHUD(RectificationitemActivity.this).showErrorWithStatus("照片不能为空");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 执行提交操作(需要更新很多数据)
            case R.id.commit_task:
                String desc = info_editText.getText().toString().trim();
                if ("".equals(desc) || desc == null) {
                    Toast.makeText(RectificationitemActivity.this, "整改描述不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    doCommit();
                }
                break;
            case R.id.task_ac_back:
                finish();
                break;
            default:
                break;
        }

    }

    @Override
    public void Success() {
        new SVProgressHUD(RectificationitemActivity.this).showSuccessWithStatus("处理上传成功");
        new Handler().postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, 2000);
    }

    @Override
    public void Failed() {
        new SVProgressHUD(RectificationitemActivity.this).showErrorWithStatus("处理上传失败");
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

    @Override
    public void noPermission() {

    }

    @Override
    public void hasPermission() {

    }


    @SuppressLint("HandlerLeak")
    public class GridAdapter extends BaseAdapter {
        private LayoutInflater inflater; // 视图容器
        private int selectedPosition = -1;// 选中的位置
        private boolean shape;

        public boolean isShape() {
            return shape;
        }

        public void setShape(boolean shape) {
            this.shape = shape;
        }

        public GridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void update() {

            loading();
        }

        public int getCount() {
            return (Bimps.bmp.size() + 1);
        }

        public Object getItem(int arg0) {

            return null;
        }

        public long getItemId(int arg0) {

            return 0;
        }

        public void setSelectedPosition(int position) {
            selectedPosition = position;
        }

        public int getSelectedPosition() {
            return selectedPosition;
        }

        /**
         * ListView Item设置
         */
        public View getView(int position, View convertView, ViewGroup parent) {
            final int coord = position;
            ViewHolder holder = null;
            if (convertView == null) {

                convertView = inflater.inflate(R.layout.item_published_grida, parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (position == Bimps.bmp.size()) {
                holder.image
                        .setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_addpic_unfocused));
                if (position == 20) {
                    holder.image.setVisibility(View.GONE);
                }
            } else {
                holder.image.setImageBitmap(Bimps.bmp.get(position));

            }

            return convertView;
        }

        public class ViewHolder {
            public ImageView image;
        }

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        adapter.notifyDataSetChanged();
                        break;
                }
                super.handleMessage(msg);
            }
        };

        public void loading() {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        if (Bimps.max == Bimps.drr.size()) {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            break;
                        } else {
                            try {
                                String path = Bimps.drr.get(Bimps.max);
                                System.out.println(path);
                                Bitmap bm = Bimps.revitionImageSize(path);
                                Bimps.bmp.add(bm);
                                String newStr = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
                                FileUtils.saveBitmap(bm, "" + newStr);
                                Bimps.max += 1;
                                Message message = new Message();
                                message.what = 1;
                                handler.sendMessage(message);
                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }
    }

    public class PopupWindows extends PopupWindow {

        public PopupWindows(Context mContext, View parent) {

            View view = View.inflate(mContext, R.layout.item_popupwindows, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_ins));
            LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_in));

            setWidth(LayoutParams.FILL_PARENT);
            setHeight(LayoutParams.FILL_PARENT);
            setBackgroundDrawable(new BitmapDrawable());
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();

            Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
            Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
            Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
            bt1.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (checkPermissions(needPermissions)) {
                        photo();
                    }
                    dismiss();
                }
            });
            bt2.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {

                }
            });
            bt3.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });

        }

    }

    public static boolean hasSDcard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    private static final int TAKE_PICTURE = 0x000000;
    private String path = "";

    @SuppressLint("SimpleDateFormat")
    public void photo() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        openCameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        StringBuffer sDir = new StringBuffer();
        if (hasSDcard()) {
            sDir.append(Environment.getExternalStorageDirectory() + "/photos/" + "/");
        } else {
            String dataPath = Environment.getRootDirectory().getPath();
            sDir.append(dataPath + "/photos/" + "/");
        }

        File fileDir = new File(sDir.toString());
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        tempFile = new File(fileDir, dateFormat.format(date) + ".jpg");

        if (Build.VERSION.SDK_INT >= 24) {//7.0 Android N
//            //com.xxx.xxx.fileprovider为上述manifest中provider所配置相同
            path = tempFile.getPath();
            Uri photoOutputUri = FileProvider.getUriForFile( RectificationitemActivity.this,RectificationitemActivity.this.getPackageName() + ".fileprovider",tempFile);
            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoOutputUri);
            startActivityForResult(openCameraIntent, TAKE_PICTURE);
        } else {//7.0以下
            path = tempFile.getPath();
            Uri imageUri = Uri.fromFile(tempFile);
            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(openCameraIntent, TAKE_PICTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (Bimps.drr.size() < 20 && resultCode == -1) {
                    Bitmap bm = BitmapFactory.decodeFile(path);
                    try {
                        FileOutputStream fos = new FileOutputStream(tempFile);
                        byte[] temp = new byte[1024];
//					 添加时间水印
                        Bitmap newbm = addTimeFlag(bm);
                        newbm.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 把数据写入文件
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    Bimps.drr.add(path);
                }
                break;
        }
    }

    private Bitmap addTimeFlag(Bitmap src) {
        // 获取原始图片与水印图片的宽与高
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas mCanvas = new Canvas(newBitmap);
        // 往位图中开始画入src原始图片
        mCanvas.drawBitmap(src, 0, 0, null);
        // 添加文字
        Paint textPaint = new Paint();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date(System.currentTimeMillis()));
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(100);
//		mCanvas.drawText("经度:" + mylongitude + " ; " + " 纬度:" + mylatitude, 25, (h - 155), textPaint);
        mCanvas.drawText(time, (float) 25, (h - 285), textPaint);
//		mCanvas.drawText("详细地址:" + address, 25, (h - 25), textPaint);
//		mCanvas.drawText(name, (float) 25, (h - 415), textPaint);
        mCanvas.save();
        mCanvas.restore();
        return newBitmap;
    }

    public String getString(String s) {
        String path = null;
        if (s == null)
            return "";
        for (int i = s.length() - 1; i > 0; i++) {
            s.charAt(i);
        }
        return path;
    }

    protected void onRestart() {
        adapter.update();
        super.onRestart();
    }


    /**
     * 返回true 已经开启了需要的权限 false 未开启权限
     *
     * @param permissions
     * @return
     */
    public boolean checkPermissions(String... permissions) {
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            List<String> needRequestPermissonList = findDeniedPermissions(permissions);
            if (null != needRequestPermissonList
                    && needRequestPermissonList.size() > 0) {
                try {
                    String[] array = needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]);
                    Method method = getClass().getMethod("requestPermissions", new Class[]{String[].class, int.class});
                    method.invoke(this, array, PERMISSON_REQUESTCODE);
                    return false;
                } catch (Exception e) {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        try {
            for (String perm : permissions) {
                Method checkSelfMethod = getClass().getMethod("checkSelfPermission", String.class);
                Method shouldShowRequestPermissionRationaleMethod = getClass().getMethod("shouldShowRequestPermissionRationale",
                        String.class);
                if ((Integer) checkSelfMethod.invoke(this, perm) != PackageManager.PERMISSION_GRANTED
                        || (Boolean) shouldShowRequestPermissionRationaleMethod.invoke(this, perm)) {
                    needRequestPermissonList.add(perm);
                }
            }
        } catch (Throwable e) {

        }
        return needRequestPermissonList;
    }
}
