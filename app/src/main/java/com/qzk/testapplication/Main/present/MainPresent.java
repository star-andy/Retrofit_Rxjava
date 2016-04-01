package com.qzk.testapplication.Main.present;

import com.qzk.testapplication.Main.view.IMainView;
import com.qzk.testapplication.application.BaseApplication;
import com.qzk.testapplication.basehttp.BaseSubscriber;
import com.qzk.testapplication.basehttp.CommonHttpResponse;
import com.qzk.testapplication.basehttp.GetIpInfoResponse;
import com.qzk.testapplication.common.LogUtils;
import com.qzk.testapplication.common.RetrofitUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;
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
                        mainView.toast("下载文件失败+++++"+e.getMessage());
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
                            LogUtils.e("length=======>",file.length()+"");
                            mainView.toast("下载文件完成");
                        }catch (IOException e){
                            e.printStackTrace();
                        }finally {
                            if (bos != null)
                            {
                                try
                                {
                                    bos.close();
                                }
                                catch (IOException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                            if (fos != null)
                            {
                                try
                                {
                                    fos.close();
                                }
                                catch (IOException e)
                                {
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
