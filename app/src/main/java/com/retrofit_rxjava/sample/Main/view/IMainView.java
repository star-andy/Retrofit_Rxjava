package com.retrofit_rxjava.sample.Main.view;

import com.retrofit_rxjava.sample.Main.base.IBaseView;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.Main.view
 * Created by QZK on 2016/3/31.
 */
public interface IMainView extends IBaseView {
    void showDialog();
    void dissmissDialog();
    void toast(String msg);
}
