package com.qzk.testapplication.Login.model;

import com.qzk.testapplication.basehttp.BaseModel;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.Login.model
 * Created by QZK on 2016/3/29.
 */
public class LoginBean extends BaseModel {
    private LoginData data;

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
