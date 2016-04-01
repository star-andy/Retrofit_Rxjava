package com.qzk.testapplication.Main.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.qzk.testapplication.Login.present.ILoginPresenter;
import com.qzk.testapplication.Main.present.IMainPresenter;
import com.qzk.testapplication.Main.present.MainPresent;
import com.qzk.testapplication.R;
import com.qzk.testapplication.common.PictureUtils;
import com.qzk.testapplication.customview.AddInitView;

import java.io.File;

/**
 * 当前类注释：
 * 项目名：TestApplication
 * 包名： com.qzk.testapplication.Main.view
 * Created by QZK on 2016/3/30.
 */
public class MainActivity extends Activity implements IMainView, View.OnClickListener {
    private Activity mActivity = MainActivity.this;
    private LinearLayout root;
    private ImageView image;
    private Button upload;
    private Button download;
    private ProgressBar progress_bar;
    private IMainPresenter mainPresenter;
    private ILoginPresenter loginPresenter;
    private Uri caramFlieUri;

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
        mainPresenter.test();

    }

    @Override
    public void updateApk(File update) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + update.toString()), "application/vnd.android.package-archive");
        this.getApplicationContext().startActivity(intent);
    }

    private void initView() {
        root = (LinearLayout) findViewById(R.id.root);
        AddInitView.initViewShow(mActivity, root);
        image = (ImageView) findViewById(R.id.image);
        upload = (Button) findViewById(R.id.upload);
        download = (Button) findViewById(R.id.download);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        image.setOnClickListener(this);
        upload.setOnClickListener(this);
        download.setOnClickListener(this);
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
    public void setBitMap(Bitmap bitmap) {
        image.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image: {
                caramFlieUri = mainPresenter.getImage(mActivity);
            }
            break;
            case R.id.upload:

                break;
            case R.id.download:
                mainPresenter.downLoad();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PictureUtils.TAKEPICTUREREQUEST) {
            mainPresenter.setImage(PictureUtils.getBitmapByUri(mActivity, caramFlieUri));
        }
    }
}
