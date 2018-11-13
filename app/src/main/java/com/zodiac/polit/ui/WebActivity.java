package com.zodiac.polit.ui;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.minilive.library.util.StringUtils;
import com.minilive.library.util.Trace;
import com.minilive.library.widget.BrowserLayout;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by john on 2018/9/27.
 */

public class WebActivity extends BaseActivity {
    @BindView(R.id.blWebView)
    BrowserLayout blWebView;

    public static final String KEY_WEB_URL = "key.web.url";
    public static final String KEY_TITLE = "key.title";

    private String title;
    private String url;

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        tvTilte.setBackgroundResource(R.drawable.logo);
        WebView webView = blWebView.getWebView();
        webView.setVerticalScrollBarEnabled(false);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        if (!StringUtils.isEmpty(url)){
            Trace.d("webUrl" , "url-------" + url);
            webView.loadUrl(url);
        }
    }



    @Override
    protected void getBundleExtras(Bundle extras) {
        title = extras.getString(KEY_TITLE);
        url = extras.getString(KEY_WEB_URL);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
