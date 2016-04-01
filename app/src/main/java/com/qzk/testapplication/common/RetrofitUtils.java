package com.qzk.testapplication.common;


import com.qzk.testapplication.basehttp.BaseInterceptor;
import com.qzk.testapplication.basehttp.ICommonService;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.common
 * Created by QZK on 2016/3/29.
 */
public class RetrofitUtils {
    private static Retrofit _instance;

    public static ICommonService generateCommonService() {
        if (_instance == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = null;
            final TrustManager[] trustManager = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[0];
                        }
                    }
            };
            try {
                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustManager, new SecureRandom());
                SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                client = new OkHttpClient.Builder().addInterceptor(interceptor).sslSocketFactory(sslSocketFactory).addInterceptor(new BaseInterceptor()).hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }).build();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            _instance = new Retrofit.Builder().baseUrl(ConstantUtils.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(client).build();
        }
        return _instance.create(ICommonService.class);
    }

}
