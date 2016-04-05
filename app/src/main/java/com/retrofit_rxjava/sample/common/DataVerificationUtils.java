package com.retrofit_rxjava.sample.common;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.common
 * Created by QZK on 2016/3/29.
 */
public class DataVerificationUtils {

    public static boolean isEmpty(String str){
        boolean flag = false;
        if(str == null){
            flag = true;
        }else{
            if(str.trim().equals("")){
                flag = true;
            }
        }
        return flag;

    }
}
