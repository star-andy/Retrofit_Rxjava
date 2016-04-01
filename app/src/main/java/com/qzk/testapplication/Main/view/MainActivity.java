package com.qzk.testapplication.Main.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.qzk.testapplication.Main.present.IMainPresenter;
import com.qzk.testapplication.Main.present.MainPresent;
import com.qzk.testapplication.R;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.Main.view
 * Created by QZK on 2016/3/30.
 */
public class MainActivity extends Activity implements IMainView {
    private Activity mActivity = MainActivity.this;
    private LinearLayout root;
    private ProgressBar progress_bar;
    private IMainPresenter mainPresenter;

    @Override
    public void toast(String msg) {
        Toast.makeText(mActivity,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mainPresenter = new MainPresent(this);
        mainPresenter.test("63.223.108.42");

    }



    private void initView() {
        root = (LinearLayout) findViewById(R.id.root);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);

    }

    @Override
    public void showDialog() {
        progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dissmissDialog() {
        progress_bar.setVisibility(View.GONE);
    }


}
