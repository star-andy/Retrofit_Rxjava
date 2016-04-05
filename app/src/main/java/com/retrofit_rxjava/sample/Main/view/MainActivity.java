package com.retrofit_rxjava.sample.Main.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.retrofit_rxjava.sample.Main.base.BaseActivity;
import com.retrofit_rxjava.sample.Main.present.IMainPresenter;
import com.retrofit_rxjava.sample.Main.present.MainPresent;
import com.retrofit_rxjava.sample.R;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.Main.view
 * Created by QZK on 2016/3/30.
 */
public class MainActivity extends BaseActivity implements IMainView,View.OnClickListener {
    private Activity mActivity = MainActivity.this;
    private LinearLayout root;
    private Button download;
    private Button parseJson;
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
//        mainPresenter.test("63.223.108.42");

    }





    private void initView() {
        root = (LinearLayout) findViewById(R.id.root);
        download = (Button)findViewById(R.id.download);
        parseJson = (Button)findViewById(R.id.parseJson);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        download.setOnClickListener(this);
        parseJson.setOnClickListener(this);

    }

    @Override
    public void showDialog() {
        progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dissmissDialog() {
        progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.download:{
                mainPresenter.download();
            }
            break;
            case R.id.parseJson:{
                mainPresenter.oberParseJsonTest();
            }
            break;
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.cancel();
    }
}
