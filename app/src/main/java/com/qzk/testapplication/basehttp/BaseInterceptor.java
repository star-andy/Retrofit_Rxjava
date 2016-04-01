package com.qzk.testapplication.basehttp;

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
//        if(!NetworkAvailableUtils.isNetwork(BaseApplication.getContext())){
//            request = request.newBuilder().addHeader("Cache-Control", "only-if-cached").cacheControl(CacheControl.FORCE_CACHE).build();
//            LogUtils.e("NetError");
//        }else{
//            request.newBuilder()
//                    .header("Cache-Control", "public, max-stale=2419200")
//                    .build();
//        }
        Response  response = chain.proceed(request);
//        if(NetworkAvailableUtils.isNetwork(BaseApplication.getContext())){
//            int maxAge = 0 * 60; // 有网络时 设置缓存超时时间0个小时
//            response.newBuilder()
//                    .header("Cache-Control", "public, max-age=" + maxAge)
//                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
//                    .build();
////            LogUtils.e("Server",response.body().string());
//        }else{
//            int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
//            response.newBuilder()
//                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                    .removeHeader("Pragma")
//                    .build();
//            LogUtils.e("Cache");
//        }
//        LogUtils.e("请求URL",request.url().toString());
//        LogUtils.e("请求Method",request.method());
//        LogUtils.e("请求参数","==");
//        Set<String> names = url.queryParameterNames();
//        Iterator<String> iterator = names.iterator();
//        while (iterator.hasNext()){
//            String name = iterator.next();
//            LogUtils.e("参数名："+name,"参数值："+url.queryParameter(name));
//        }
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
