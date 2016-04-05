package com.retrofit_rxjava.sample.basehttp;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.common
 * Created by QZK on 2016/3/29.
 */
public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().addHeader("user-agent", "android").build();
        // 添加新的参数
        HttpUrl url = request.url().newBuilder().addQueryParameter("scope","withball_v2_android").build();
        request = request.newBuilder().url(url).build();
        Response  response = chain.proceed(request);
        String cacheControl = request.cacheControl().toString();
        if (TextUtils.isEmpty(cacheControl)) {
            cacheControl = "public, max-age=60";
        }
        return response.newBuilder()
                .header("Cache-Control", cacheControl)
                .removeHeader("Pragma")
                .build();

    }
}
