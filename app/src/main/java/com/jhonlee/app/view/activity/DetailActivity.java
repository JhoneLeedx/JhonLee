package com.jhonlee.app.view.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.jhonlee.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JhoneLee on 2017/2/6.
 */

public class DetailActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView mView;
    @BindView(R.id.rl_loading)
    protected RelativeLayout mRlLoading;
    private String mUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initView();

    }
    private void initView(){
        mUrl = getIntent().getStringExtra("url");
        mView.loadUrl(mUrl);
        mView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mView.getSettings().setUseWideViewPort(true);
        mView.getSettings().setLoadWithOverviewMode(true);

        mView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    //加载完网页进度条消失
                    mRlLoading.setVisibility(View.GONE);
                } else {
                    //开始加载网页时显示进度条
                    mRlLoading.setVisibility(View.VISIBLE);
                    //设置进度值
                }

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK&&mView.canGoBack()){
            mView.goBack();
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }
}

