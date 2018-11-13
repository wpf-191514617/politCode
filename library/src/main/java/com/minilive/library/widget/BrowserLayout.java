package com.minilive.library.widget;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.minilive.library.R;
import com.minilive.library.util.Trace;
import com.minilive.library.widget.listener.OnLoadUrlCallBackListener;

/**
 * Created by Administrator on 2018/2/8.
 */

public class BrowserLayout extends LinearLayout {

    private OnLoadUrlCallBackListener mOnLoadUrlCallBackListener;
    public void setOnLoadUrlCallBackListener(OnLoadUrlCallBackListener mOnLoadUrlCallBackListener) {
        this.mOnLoadUrlCallBackListener = mOnLoadUrlCallBackListener;
    }
    private Context mContext = null;
    private WebView mWebView = null;
    private View mBrowserControllerView = null;

    private int mBarHeight = 5;
    private ProgressBar mProgressBar = null;
    private String mLoadUrl;
    public BrowserLayout(Context context) {
        super(context);
        init(context);
    }
    public BrowserLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(final Context context) {
        mContext = context;
        setOrientation(VERTICAL);
        mProgressBar = (ProgressBar) LayoutInflater.from(context).inflate(R.layout.progress_horizontal, null);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);
        addView(mProgressBar, LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mBarHeight, getResources().getDisplayMetrics()));
        mWebView = new WebView(context);
        /*LayoutParams params = (LayoutParams) mWebView.getLayoutParams();
        params.weight = LayoutParams.MATCH_PARENT;
        params.height = LayoutParams.MATCH_PARENT;*/
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        LayoutParams lps = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
        addView(mWebView, lps);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    if(mOnLoadUrlCallBackListener != null){
                        mOnLoadUrlCallBackListener.onFinish(mLoadUrl);
                    }
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }


        });
       /* mWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mLoadUrl = url;
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                *//*if(!url.startsWith("http")&&!url.startsWith("https")){
                    Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse(url));
                    getContext().startActivity(intent);
                    return true;
                }*//*
               *//* Log.e("用户单击超连接", url);
                //判断用户单击的是那个超连接
                String tag = "tel";
                if (url.contains(tag)) {
                    String mobile = url.substring(url.lastIndexOf("/") + 1);
                    Log.e("mobile----------->",mobile);
                    Intent mIntent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse(mobile);
                    mIntent.setData(data);
                    //Android6.0以后的动态获取打电话权限
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        context.startActivity(mIntent);
                        //这个超连接,java已经处理了，webview不要处理
                        return true;
                    }else{
                        //申请权限
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},1);
                        return true;
                    }
                }else {*//*
                   // view.loadUrl(url);
                //}

                return false;
            }
        });*/
//        addView(mBrowserControllerView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }
    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }
    public boolean canGoBack() {
        return null != mWebView ? mWebView.canGoBack() : false;
    }
    public boolean canGoForward() {
        return null != mWebView ? mWebView.canGoForward() : false;
    }
    public void goBack() {
        if (null != mWebView) {
            mWebView.goBack();
        }
    }
    public void goForward() {
        if (null != mWebView) {
            mWebView.goForward();
        }
    }
    public WebView getWebView() {
        return mWebView != null ? mWebView : null;
    }
    public void hideBrowserController() {
        mBrowserControllerView.setVisibility(View.GONE);
    }
    public void showBrowserController() {
        mBrowserControllerView.setVisibility(View.VISIBLE);
    }


}
