package com.qzk.testapplication.Login.present;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.Login
 * Created by QZK on 2016/3/29.
 */
public interface ILoginPresenter {
    void login(String userName,String passWord);
    void mobileCheck(String mobile);
}
