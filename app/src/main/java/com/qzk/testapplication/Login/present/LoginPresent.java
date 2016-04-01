package com.qzk.testapplication.Login.present;

import com.qzk.testapplication.Login.model.LoginBean;
import com.qzk.testapplication.Login.view.ILoginView;
import com.qzk.testapplication.basehttp.BaseSubscriber;
import com.qzk.testapplication.basehttp.CommonHttpResponse;
import com.qzk.testapplication.basehttp.CommonModel;
import com.qzk.testapplication.basehttp.GetIpInfoResponse;
import com.qzk.testapplication.common.DataVerificationUtils;
import com.qzk.testapplication.common.LogUtils;
import com.qzk.testapplication.common.RetrofitUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.Login
 * Created by QZK on 2016/3/29.
 */
public class LoginPresent implements ILoginPresenter {
    private ILoginView loginView;

    public LoginPresent(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String userName, String passWord) {
        if (DataVerificationUtils.isEmpty(userName)) {
            loginView.showErrorMessage("用户名不能为空");
            return;
        }
        if (DataVerificationUtils.isEmpty(passWord)) {
            loginView.showErrorMessage("密码不能为空");
            return;
        }
        loginView.showProgress();
        RetrofitUtils.generateCommonService()
                .login(userName, passWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<LoginBean>(new CommonHttpResponse<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        loginView.hideProgress();
                        loginView.loginSuccessed();
                        LogUtils.e("onSuccess", "onSuccess");
                    }
                    @Override
                    public void onError(String msg) {
                        loginView.hideProgress();
                        loginView.showErrorMessage(msg);
                        LogUtils.e("onError",msg);
                    }
                }));
    }

    @Override
    public void mobileCheck(String mobile) {
        if (DataVerificationUtils.isEmpty(mobile)) {
            loginView.showErrorMessage("手机号不能为空");
            return;
        }
        RetrofitUtils.generateCommonService().mobileCheck(mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<CommonModel>(new CommonHttpResponse<CommonModel>() {
                    @Override
                    public void onSuccess(CommonModel commonModel) {
                        boolean flag = (Boolean)commonModel.getData();
                        if(flag){
                            loginView.showErrorMessage("存在");
                        }else{
                            loginView.showErrorMessage("不存在");
                        }
                    }
                    @Override
                    public void onError(String msg) {
                        loginView.showErrorMessage(msg);
                    }
                }));

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
                    }
                    @Override
                    public void onError(String msg) {
                        loginView.showErrorMessage(msg);
                    }
                }));
    }
}
