package com.qzk.testapplication.basehttp;

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
//        LogUtils.e("请求URL",request.url().toString());
//        LogUtils.e("请求Method",request.method());
//        LogUtils.e("请求参数","==");
//        Set<String> names = url.queryParameterNames();
//        Iterator<String> iterator = names.iterator();
//        while (iterator.hasNext()){
//            String name = iterator.next();
//            LogUtils.e("参数名："+name,"参数值："+url.queryParameter(name));
//        }
        return chain.proceed(request);
    }
}
