package com.retrofit_rxjava.sample.Main.present;

import com.google.gson.Gson;
import com.retrofit_rxjava.sample.Main.base.BasePresent;
import com.retrofit_rxjava.sample.Main.view.IMainView;
import com.retrofit_rxjava.sample.application.BaseApplication;
import com.retrofit_rxjava.sample.basehttp.BaseSubscriber;
import com.retrofit_rxjava.sample.basehttp.CommonHttpResponse;
import com.retrofit_rxjava.sample.basehttp.GetIpInfoResponse;
import com.retrofit_rxjava.sample.basehttp.TestBean;
import com.retrofit_rxjava.sample.common.LogUtils;
import com.retrofit_rxjava.sample.common.RetrofitUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.Main.present
 * Created by QZK on 2016/3/31.
 */
public class MainPresent extends BasePresent implements IMainPresenter {

    private IMainView mainView;
    private BaseSubscriber sub1;

    public MainPresent(IMainView view) {
        this.mainView = view;
    }


    @Override
    public void download() {
        mainView.showDialog();
        RetrofitUtils.generateCommonService().down()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.dissmissDialog();
                        mainView.toast("下载文件失败+++++" + e.getMessage());
                        LogUtils.e("errorrrrrrrrrr");

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        mainView.dissmissDialog();
                        BufferedOutputStream bos = null;
                        FileOutputStream fos = null;
                        try {
                            byte[] by = responseBody.bytes();
                            File file = new File(BaseApplication.getContext().getFilesDir(), "download.apk");
                            fos = new FileOutputStream(file);
                            bos = new BufferedOutputStream(fos);
                            bos.write(by);
                            LogUtils.e("length=======>", file.length() + "");
                            mainView.toast("下载文件完成");
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (bos != null) {
                                try {
                                    bos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (fos != null) {
                                try {
                                    fos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        LogUtils.e("success=========");
                    }
                });
    }

    @Override
    public void test(String ip) {
        sub1 = new BaseSubscriber<GetIpInfoResponse>(new CommonHttpResponse<GetIpInfoResponse>() {
            @Override
            public void onSuccess(GetIpInfoResponse commonModel) {
                LogUtils.e(commonModel.data.country);
                mainView.toast(commonModel.data.country);
            }

            @Override
            public void onError(String msg) {

            }
        });

        RetrofitUtils.generateCommonService().test(ip)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sub1);

    }

    @Override
    public void oberParseJsonTest() {
        final String json = "{\"ec\":\"200\",\"em\":\"aaa\"}";
        Observer<TestBean> observer = new Observer<TestBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mainView.toast("Json解析=====error" + e.getMessage());
                LogUtils.e("Json解析=====error", e.getMessage());
            }

            @Override
            public void onNext(TestBean testBean) {
                mainView.toast("Json解析======success" + testBean.getEm());
                LogUtils.e("Json解析======success", testBean.getEc());
            }
        };
        Observable observable = Observable.create(new Observable.OnSubscribe<TestBean>() {
            @Override
            public void call(Subscriber<? super TestBean> subscriber) {
                subscriber.onNext(new Gson().fromJson(json, TestBean.class));
                subscriber.onCompleted();
            }
        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    @Override
    public void cancel() {
        stopObserver(sub1);
    }
}
