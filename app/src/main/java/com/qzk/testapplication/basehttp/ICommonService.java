package com.qzk.testapplication.basehttp;

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
public interface ICommonService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @POST("auth/login")
     Observable<LoginBean> login(@Query("username")String username, @Query("password")String password);

    /**
     * 检查手机号是否存在
     * @param mobile
     * @return
     */
    @POST("auth/mobile_check")
    Observable<CommonModel> mobileCheck(@Query("mobile") String mobile);
}
