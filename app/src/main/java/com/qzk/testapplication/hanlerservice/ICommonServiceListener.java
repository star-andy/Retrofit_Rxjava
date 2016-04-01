package com.qzk.testapplication.hanlerservice;

import com.qzk.testapplication.common.BaseModel;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.hanlerservice
 * Created by QZK on 2016/3/29.
 */
public interface ICommonServiceListener {
    void onSuccess(BaseModel model);
    void onFailure(String msg);
}
