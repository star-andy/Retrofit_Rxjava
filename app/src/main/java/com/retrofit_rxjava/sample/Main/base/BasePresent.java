package com.retrofit_rxjava.sample.Main.base;

import com.retrofit_rxjava.sample.basehttp.BaseSubscriber;

/**
 * 当前类注释：Present基类
 * 项目名：Retrofit_Rxjava
 * 包名： com.qzk.testapplication.Main.present
 * Created by QYang on 2016/4/1.
 */
public class BasePresent {

    /**
     * 当前方法注释：当activity结束时取消订阅，避免内存泄漏
     * 包名：MainPresent
     * Created by QYang on 2016/4/1 15:55
     */
    public void stopObserver(BaseSubscriber ...baseSubscribers) {
        for(BaseSubscriber base : baseSubscribers){
            if (base != null && base.isUnsubscribed()) {
                base.unsubscribe();
            }
        }

    }
}
