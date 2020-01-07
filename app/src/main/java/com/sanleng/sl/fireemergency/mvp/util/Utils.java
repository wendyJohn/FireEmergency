package com.sanleng.sl.fireemergency.mvp.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.sanleng.sl.fireemergency.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by litonghui on 2018/5/11.
 */

public class Utils {

    public static Uri mUritempFile;
    public static File mTempFile;
    public static final int CAMERA_REQUEST_CODE = 101, PHOTO_REQUEST_CODE = 102, CROP_PHOTO_REUQEST_CODE = 103;
    public static String imageName = System.currentTimeMillis() + ".png";

    public static final String TAG = "file-face";

    public static boolean saveBitmapToFile(String savePath, Bitmap bitmap) {
        boolean result = false;
        FileOutputStream out = null;

        File dir = new File(Environment.getExternalStorageDirectory() + File.separator + "TrackImage");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(dir, savePath);
        if (f.exists()) {
            f.delete();
        }
        try {
            out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static boolean saveStringToFile(String savePath, String landmark) {
        boolean result = false;
        FileOutputStream out = null;

        File dir = new File(Environment.getExternalStorageDirectory() + File.separator + "BDFace");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(dir, savePath);
        try {
            out = new FileOutputStream(f, true);
            out.write(landmark.getBytes());
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String saveToFile(File dir, String fileName, byte[] content) {
        try {
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, fileName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content);
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getFromFile(String fileName) {
        byte[] content = new byte[2048];
        File file = new File(fileName);
        try {
            FileInputStream stream = new FileInputStream(file);
            stream.read(content);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static Bitmap getBitmapFromFile(String fileName) {
        File file = new File(fileName);
        try {
            FileInputStream stream = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            stream.close();
            return bitmap;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap getBitmap(Context context, String imageName) {
        Bitmap bmp = null;
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(imageName);
            bmp = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    public static Bitmap getBitmap(String imageName) {
        Bitmap bmp = null;
        File file = new File(imageName);
        try {
            FileInputStream stream = new FileInputStream(file);
            bmp = BitmapFactory.decodeStream(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    public static byte[] getByte(Context context,String imageName) {
        try {
            InputStream is = context.getAssets().open(imageName);
            byte[] fileBytes = new byte[is.available()];
            is.read(fileBytes);
            is.close();
            return fileBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void printfByte(byte[] content) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte c : content) {
            stringBuilder.append(c).append(" ");
        }
        Log.e(TAG, stringBuilder.toString());
    }

    public static ArrayList<File> refreshFileList(String strPath) {
        ArrayList<File> fileList = new ArrayList<>();
        File dir = new File(strPath);
        File[] files = dir.listFiles();
        if (null == files) {
            return null;
        }

        for (int i = 0, size = files.length; i < size; i++) {
            // 如果是文件夹
            if (files[i].isDirectory()) {
                // 遍历此路径，执行此方法
                ArrayList<File> refreshFileList = refreshFileList(files[i].getAbsolutePath());
                if (null != refreshFileList) {
                    fileList.addAll(refreshFileList);
                }
            } else {
                // 添加到文件列表
                fileList.add(files[i]);
            }
        }
        return fileList;
    }



    /**
     * 8位灰度转Bitmap
     * <p>
     * 图像宽度必须能被4整除
     *
     * @param data   裸数据
     * @param width  图像宽度
     * @param height 图像高度
     * @return
     */
    public static Bitmap convert8bit(byte[] data, int width, int height) {
        byte[] Bits = new byte[data.length * 4]; //RGBA 数组

        int i;
        for (i = 0; i < data.length; i++) {
            // 原理：4个字节表示一个灰度，则RGB  = 灰度值，最后一个Alpha = 0xff;
            Bits[i * 4] = Bits[i * 4 + 1] = Bits[i * 4 + 2] = data[i];
            Bits[i * 4 + 3] = -1; //0xff
        }

        // Bitmap.Config.ARGB_8888 表示：图像模式为8位
        Bitmap bmp = Bitmap
                .createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bmp.copyPixelsFromBuffer(ByteBuffer.wrap(Bits));

        return bmp;
    }

    public static void drawShape(int[] shape, Bitmap img) {
        // draw lines
        int Pointcolor = Color.GREEN;
        int LineColor = Color.BLUE;

        Canvas canvas = new Canvas(img);
        Paint paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setColor(LineColor);

        if (shape.length == 144) {
            int nComponents = 9;

            int comp1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
            int comp2[] = {13, 14, 15, 16, 17, 18, 19, 20, 13, 21};
            int comp3[] = {22, 23, 24, 25, 26, 27, 28, 29, 22};
            int comp4[] = {30, 31, 32, 33, 34, 35, 36, 37, 30, 38};
            int comp5[] = {39, 40, 41, 42, 43, 44, 45, 46, 39};
            int comp6[] = {47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 47};
            int comp7[] = {51, 57, 52};
            int comp8[] = {58, 59, 60, 61, 62, 63, 64, 65, 58};
            int comp9[] = {58, 66, 67, 68, 62, 69, 70, 71, 58};
            int nPoints[] = {13, 10, 9, 10, 9, 11, 3, 9, 9};
            int idx[][] = {comp1, comp2, comp3, comp4, comp5, comp6, comp7, comp8, comp9};

            for (int i = 0; i < nComponents; ++i) {
                for (int j = 0; j < nPoints[i] - 1; ++j) {
                    canvas.drawLine(shape[idx[i][j] << 1], shape[1 + (idx[i][j] << 1)],
                            shape[idx[i][j + 1] << 1], shape[1 + (idx[i][j + 1] << 1)], paint);
                }
            }
        }

        paint.setStrokeWidth(6);
        paint.setColor(Pointcolor);
        // draw landmark points
        for (int i = 0; i < shape.length / 2; ++i) {
            canvas.drawCircle(shape[i << 1], shape[1 + (i << 1)], 2, paint);
        }
    }

    public static ArrayList<File> listFiles(String strPath) {
        return refreshFileList(strPath);
    }

    public static int NetWork(Context context) {
        int State = -1;
        //获取网络工具类
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取网络工作状态
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            //获取网络类型
            int type = networkInfo.getType();
            //判断网络类型是否为WIFI
            if (type == ConnectivityManager.TYPE_WIFI) {
                return 1;
            }
            //判断网络类型是否为移动网络
            else if (type == ConnectivityManager.TYPE_MOBILE) {
                return 0;
            }
            //如果都不是返回-1
            else {
                return -1;
            }
        }
        return State;
    }

    public static String nowTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    public static String flipHexStr(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= s.length() - 2; i = i + 2) {
            result.append(new StringBuilder(s.substring(i, i + 2)).reverse());
        }
        return result.reverse().toString();
    }

    public static String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        String out = "";

        for (j = 0; j < inarray.length; ++j) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }

    /**
     * 拍照
     */
    public static void camera(Activity activity) {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            mTempFile = new File(Environment.getExternalStorageDirectory(), imageName);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriForFile(activity, mTempFile));
            activity.startActivityForResult(intent, CAMERA_REQUEST_CODE);
        } else {
            Toast.makeText(activity, "请确认已插入SD卡", Toast.LENGTH_SHORT).show();
        }
    }

    private static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context, context.getPackageName() + ".FileProvider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    /**
     * 选取图片
     */
    public static void photo(Activity activity) {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {// 如果挂载成功。
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            activity.startActivityForResult(intent, PHOTO_REQUEST_CODE);
        } else {
            Toast.makeText(activity, "请确认已插入SD卡", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 裁剪图片
     *
     * @param uri
     */
    public static void cropPhoto(Activity activity, Uri uri) {
        mUritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + imageName);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUritempFile);
        activity.startActivityForResult(intent, CROP_PHOTO_REUQEST_CODE);
    }

    /**
     * 将图片保存到sd卡
     *
     * @param sdPath
     * @param bitmap
     * @return
     */
    public static File setPicToSdCard(String sdPath,Bitmap bitmap) {

        File file = new File(sdPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(sdPath + imageName);
        try {
            file.createNewFile();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 图片压缩
     *
     * @param fPath
     * @return
     */
    public static Bitmap decodeFile(String fPath) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inDither = false; // Disable Dithering mode
        opts.inPurgeable = true; // Tell to gc that whether it needs free
        opts.inInputShareable = true; // Which kind of reference will be used to
        BitmapFactory.decodeFile(fPath, opts);

        final int REQUIRED_SIZE = 400;
        int scale = 1;
        if (opts.outHeight > REQUIRED_SIZE || opts.outWidth > REQUIRED_SIZE) {
            final int heightRatio = Math.round((float) opts.outHeight
                    / (float) REQUIRED_SIZE);
            final int widthRatio = Math.round((float) opts.outWidth
                    / (float) REQUIRED_SIZE);
            scale = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        Log.i("cyc", "scal =" + scale);
        opts.inJustDecodeBounds = false;
        opts.inSampleSize = scale;
        Bitmap bm = BitmapFactory.decodeFile(fPath, opts).copy(Bitmap.Config.ARGB_8888, false);
        return bm;
    }

    /**
     * 图片压缩
     *
     * @param context
     * @param uri
     * @return
     */
    public static Bitmap decodeUri(Context context, Uri uri) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; //只读取图片尺寸
        resolveUri(context, uri, options);

        final int REQUIRED_SIZE = 400;
        int scale = 1;
        if (options.outHeight > REQUIRED_SIZE || options.outWidth > REQUIRED_SIZE) {
            final int heightRatio = Math.round((float) options.outHeight
                    / (float) REQUIRED_SIZE);
            final int widthRatio = Math.round((float) options.outWidth
                    / (float) REQUIRED_SIZE);
            scale = heightRatio < widthRatio ? heightRatio : widthRatio;//
        }

        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;//读取图片内容
        options.inPreferredConfig = Bitmap.Config.ARGB_8888; //根据情况进行修改
        Bitmap bitmap = null;
        try {
            bitmap = resolveUriForBitmap(context, uri, options);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private static void resolveUri(Context context, Uri uri, BitmapFactory.Options options) {
        if (uri == null) {
            return;
        }
        String scheme = uri.getScheme();
        if (ContentResolver.SCHEME_CONTENT.equals(scheme) ||
                ContentResolver.SCHEME_FILE.equals(scheme)) {
            InputStream stream = null;
            try {
                stream = context.getContentResolver().openInputStream(uri);
                BitmapFactory.decodeStream(stream, null, options);
            } catch (Exception e) {
                Log.w("resolveUri", "Unable to open content: " + uri, e);
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        Log.w("resolveUri", "Unable to close content: " + uri, e);
                    }
                }
            }
        } else if (ContentResolver.SCHEME_ANDROID_RESOURCE.equals(scheme)) {
            Log.w("resolveUri", "Unable to close content: " + uri);
        } else {
            Log.w("resolveUri", "Unable to close content: " + uri);
        }
    }

    private static Bitmap resolveUriForBitmap(Context context, Uri uri, BitmapFactory.Options options) {
        if (uri == null) {
            return null;
        }

        Bitmap bitmap = null;
        String scheme = uri.getScheme();
        if (ContentResolver.SCHEME_CONTENT.equals(scheme) ||
                ContentResolver.SCHEME_FILE.equals(scheme)) {
            InputStream stream = null;
            try {
                stream = context.getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(stream, null, options);
            } catch (Exception e) {
                Log.w("resolveUriForBitmap", "Unable to open content: " + uri, e);
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        Log.w("resolveUriForBitmap", "Unable to close content: " + uri, e);
                    }
                }
            }
        } else if (ContentResolver.SCHEME_ANDROID_RESOURCE.equals(scheme)) {
            Log.w("resolveUriForBitmap", "Unable to close content: " + uri);
        } else {
            Log.w("resolveUriForBitmap", "Unable to close content: " + uri);
        }

        return bitmap;
    }


    /**
     * 获取图片的uri
     *
     * @param context
     * @param imageFile
     * @return
     */
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }
}
