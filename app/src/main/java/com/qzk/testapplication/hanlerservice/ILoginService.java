package com.qzk.testapplication.hanlerservice;

import com.qzk.testapplication.Login.model.LoginBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.hanlerservice
 * Created by QZK on 2016/3/29.
 */
public interface ILoginService {
    @POST("auth/login")
    Observable<LoginBean> login(@Query("username")String username, @Query("password")String password);
}
