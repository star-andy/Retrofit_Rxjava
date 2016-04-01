package com.retrofit_rxjava.sample.basehttp;

import rx.Subscriber;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.basehttp
 * Created by QZK on 2016/3/30.
 */
public  class BaseSubscriber<T> extends Subscriber<T>  {
    private CommonHttpResponse<T> _httpResponse;
    public BaseSubscriber (CommonHttpResponse<T> httpResponse){
        this._httpResponse = httpResponse;

    }
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        this._httpResponse.onError(e.getMessage());
    }

    @Override
    public void onNext(T t) {
//        BaseModel data = (BaseModel) t;
//        if (data.getEc().equals("200")){
            this._httpResponse.onSuccess(t);
//        }else{
//            this._httpResponse.onError(data.getEm());
//        }


    }
}
