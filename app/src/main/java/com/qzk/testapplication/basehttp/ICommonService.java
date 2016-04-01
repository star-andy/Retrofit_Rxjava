package com.qzk.testapplication.basehttp;

import com.qzk.testapplication.Login.model.LoginBean;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
     *
     * @param username
     * @param password
     * @return
     */

    @POST("auth/login")
    Observable<LoginBean> login(@Query("username") String username, @Query("password") String password);

    /**
     * 检查手机号是否存在
     *
     * @param mobile
     * @return
     */
    @POST("auth/mobile_check")
    Observable<CommonModel> mobileCheck(@Query("mobile") String mobile);

    @Headers("Cache-Control: public, max-age=640000, s-maxage=640000 , max-stale=2419200")
    @GET("http://ip.taobao.com/service/getIpInfo.php")
    Observable<GetIpInfoResponse> test(@Query("ip") String ip);

    @GET("http://pkg.fir.im/2c3051a843fb613c07f2114af8ab7ea890ebc076?attname=Imfans.apk_1.0.0.apk&e=1453436599&token=LOvmia8oXF4xnLh0IdH05XMYpH6ENHNpARlmPc-T:mKbowXTF4R8wFLY3Tcy95HGTwlk=")
    @Headers({"Content-Type: image/jpeg"})
    Observable<ResponseBody> down();
}
