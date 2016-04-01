package com.qzk.testapplication.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qzk.testapplication.R;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.customview
 * Created by QZK on 2016/3/31.
 */
public class AddInitView {

    public static void initViewShow(Context context, ViewGroup group){
        RelativeLayout contanier = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.initemptyview,null);
        group.addView(contanier, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }


}
