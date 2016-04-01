package com.qzk.testapplication.Login.view;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.Login
 * Created by QZK on 2016/3/29.
 */
public interface ILoginView {

    void showProgress();

    void hideProgress();

    void showErrorMessage(String msg);

    void loginSuccessed();

}
