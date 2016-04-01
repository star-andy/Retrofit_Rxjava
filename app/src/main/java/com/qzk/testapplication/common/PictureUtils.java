package com.qzk.testapplication.common;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;

import java.io.File;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.common
 * Created by QZK on 2016/3/31.
 */
public class PictureUtils {

    public static final int TAKEPICTUREREQUEST = 100;
    public static Uri takePhoto(Context context){
        File caramFile = new File("/mnt/sdcard/", "tmp_pic_" + SystemClock.currentThreadTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(caramFile));
        ((Activity)context).startActivityForResult(intent, TAKEPICTUREREQUEST);
        return Uri.fromFile(caramFile);
    }
    public static Bitmap getBitmapByUri(Context context, Uri uri) {
        try {
            ContentResolver resolver = context.getContentResolver();
            return MediaStore.Images.Media.getBitmap(resolver, uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
