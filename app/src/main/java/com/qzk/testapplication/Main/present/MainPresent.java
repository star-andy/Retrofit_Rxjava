package com.qzk.testapplication.Main.present;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.qzk.testapplication.Main.view.IMainView;
import com.qzk.testapplication.application.BaseApplication;
import com.qzk.testapplication.basehttp.BaseSubscriber;
import com.qzk.testapplication.basehttp.CommonHttpResponse;
import com.qzk.testapplication.basehttp.CommonModel;
import com.qzk.testapplication.common.LogUtils;
import com.qzk.testapplication.common.PictureUtils;
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
    public MainPresent(IMainView view){
        this.mainView = view;
    }
    @Override
    public Uri getImage(Context context) {
        return PictureUtils.takePhoto(context);
    }

    @Override
    public void setImage(Bitmap bitmap) {
        this.mainView.setBitMap(bitmap);
    }

    @Override
    public void test() {
        RetrofitUtils.generateCommonService().mobileCheck("13718605021")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<CommonModel>(new CommonHttpResponse<CommonModel>() {
                    @Override
                    public void onSuccess(CommonModel commonModel) {
                        boolean flag = (Boolean)commonModel.getData();
                       mainView.toast(String.valueOf(flag));
                        mainView.dissmissDialog();
                    }
                    @Override
                    public void onError(String msg) {

                    }
                }));
    }

    @Override
    public void downLoad() {
        mainView.showDialog();
        RetrofitUtils.generateCommonService().down().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("errorrrrrrrrrr");

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        BufferedOutputStream bos = null;
                        FileOutputStream fos = null;
                        try {
                            byte[] by = responseBody.bytes();
                            File file = new File(BaseApplication.getContext().getFilesDir(), "download.apk");
                            fos = new FileOutputStream(file);
                            bos = new BufferedOutputStream(fos);
                            bos.write(by);
                            LogUtils.e("length=======>",file.length()+"");
                            mainView.updateApk(file);
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
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Response>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mainView.dissmissDialog();
//                        LogUtils.e("error===>",e.getMessage());
//
//                    }
//
//                    @Override
//                    public void onNext(Response response) {
//                        mainView.dissmissDialog();
//                        LogUtils.e("body",response.body().toString());
////                        BufferedOutputStream bos = null;
////                        FileOutputStream fos = null;
////                        try {
////
////                            byte[] by = response.body()
////                            File dir = new File(BaseApplication.getContext().getFilesDir().getAbsolutePath(),"apk");
////                            if (!dir.exists() && dir.isDirectory())
////                            {
////                                dir.mkdirs();
////                            }
////                           File file = new File(dir+"/download.apk");
////                            fos = new FileOutputStream(file);
////                            bos = new BufferedOutputStream(fos);
////                            bos.write(by);
////                            LogUtils.e("length=======>",file.length()+"");
////                        }catch (IOException e){
////                            e.printStackTrace();
////                        }finally {
////                            if (bos != null)
////                            {
////                                try
////                                {
////                                    bos.close();
////                                }
////                                catch (IOException e)
////                                {
////                                    e.printStackTrace();
////                                }
////                            }
////                            if (fos != null)
////                            {
////                                try
////                                {
////                                    fos.close();
////                                }
////                                catch (IOException e)
////                                {
////                                    e.printStackTrace();
////                                }
////                            }
////                        }
//
//
//                    }
//                });

    }
}
