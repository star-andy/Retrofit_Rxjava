package com.qzk.testapplication.Login.model;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.Login.model
 * Created by QZK on 2016/3/29.
 */
public class LoginData {

    /**
     * access_token : token值[string]
     * expires_in : access_token过期时间段[long],单位秒(s)
     * uid : 用户id[string]
     */

    private String access_token;
    private String expires_in;
    private String uid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
