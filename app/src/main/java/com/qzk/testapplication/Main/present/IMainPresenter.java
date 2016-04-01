package com.qzk.testapplication.Main.present;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.Main.present
 * Created by QZK on 2016/3/31.
 */
public interface IMainPresenter {
    Uri getImage(Context context);
    void setImage(Bitmap bitmap);
    void downLoad();
    void test();

}
