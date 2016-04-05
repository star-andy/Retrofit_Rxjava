package com.retrofit_rxjava.sample.basehttp;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.basehttp
 * Created by QZK on 2016/3/30.
 */
public abstract  class CommonHttpResponse<T> {
   public abstract void onSuccess(T t);
   public abstract void onError(String msg);

}
