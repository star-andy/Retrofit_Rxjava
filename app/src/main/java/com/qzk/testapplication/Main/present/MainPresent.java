package com.qzk.testapplication.Main.present;

import com.qzk.testapplication.Main.view.IMainView;
import com.qzk.testapplication.basehttp.BaseSubscriber;
import com.qzk.testapplication.basehttp.CommonHttpResponse;
import com.qzk.testapplication.basehttp.GetIpInfoResponse;
import com.qzk.testapplication.common.LogUtils;
import com.qzk.testapplication.common.RetrofitUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.Main.present
 * Created by QZK on 2016/3/31.
 */
public class MainPresent implements IMainPresenter {

    private IMainView mainView;

    public MainPresent(IMainView view) {
        this.mainView = view;
    }

    @Override
    public void test(String ip) {
        RetrofitUtils.generateCommonService().test(ip)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<GetIpInfoResponse>(new CommonHttpResponse<GetIpInfoResponse>() {
                    @Override
                    public void onSuccess(GetIpInfoResponse commonModel) {
                        LogUtils.e(commonModel.data.country);
                        mainView.toast(commonModel.data.country);
                    }

                    @Override
                    public void onError(String msg) {
                        mainView.toast(msg);
                    }
                }));
    }


}
