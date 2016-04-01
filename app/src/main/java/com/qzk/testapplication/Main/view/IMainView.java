package com.qzk.testapplication.Main.view;

import android.graphics.Bitmap;

import java.io.File;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.Main.view
 * Created by QZK on 2016/3/31.
 */
public interface IMainView {

    void setBitMap(Bitmap bitmap);
    void updateApk(File update);
    void showDialog();
    void dissmissDialog();
    void toast(String msg);
}
