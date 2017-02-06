package com.jhonlee.app.view.activity;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jhonlee.app.R;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.ProgressListener;
import com.kymjs.rxvolley.http.VolleyError;
import com.kymjs.rxvolley.toolbox.FileUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by JhoneLee on 2017/2/6.
 */
public class ShowImageActivity extends Activity{

    @BindView(R.id.image)
    protected ImageView mImage;


    private WallpaperManager wpManager;
    private  String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        wpManager = WallpaperManager.getInstance(this);
        initview();
    }
    private void initview(){
        url = getIntent().getStringExtra("url");
        Glide.with(this).load(url)
                .placeholder(android.R.drawable.ic_search_category_default)
                .error(android.R.drawable.stat_notify_error)
                .into(mImage);
    }
    @OnClick({R.id.iv_down,R.id.iv_fav,R.id.iv_share,R.id.iv_menu,R.id.btn_set_wallpaper,R.id.iv_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                break;
            case R.id.iv_fav:
                Toast.makeText(this, "喜欢", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_down:
                RxVolley.download(FileUtils.getSDCardPath() + "/wallpaper/" + System.currentTimeMillis() + ".png"
                        , url
                        , new ProgressListener() {
                            @Override
                            public void onProgress(long transferredBytes, long totalSize) {

                            }
                        }, new HttpCallback() {
                            @Override
                            public void onSuccess(String t) {
                                Toast.makeText(ShowImageActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure(VolleyError error) {
                                Toast.makeText(ShowImageActivity.this, "下载失败" + error.toString(), Toast.LENGTH_SHORT).show();
                                Log.i("error",error.toString());
                            }
                        });
                break;
            case R.id.iv_menu:

                break;
            case R.id.btn_set_wallpaper:
                setDesktopWallpaper();
                break;
        }
    }
    //设置桌面壁纸
    private void setDesktopWallpaper() {
        Glide.with(this).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                try {
                    wpManager.setBitmap(resource);
                    Toast.makeText(ShowImageActivity.this, "桌面壁纸设置成功", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(ShowImageActivity.this, "桌面壁纸设置失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
