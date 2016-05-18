package com.weibank.com.weibankapp;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.weibank.com.weibankapp.utils.WebViewHelper;

public class SecondH5Activity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_h5);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        /**
         *  调用初始化对象方法
         */
        assignViews();
        String urlValue = "http://www.haosou.com/";
        viewHelper.handleWebView(mMwebview,urlValue);
//        WebSettings settings = mMwebview.getSettings();
//        settings.setJavaScriptEnabled(true);
//        settings.setBuiltInZoomControls(true);
//        settings.setLoadWithOverviewMode(true);
//        settings.setAllowUniversalAccessFromFileURLs(true);
//
//        mMwebview.loadUrl("http://www.haosou.com/");
//        mMwebview.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url){
//
//                view.loadUrl(url);
//                return true;
//            }
//        });
    }
    /**
     *  重写onKeyDown方法
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK && mMwebview.canGoBack()){

            mMwebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){

        mMwebview = (WebView)findViewById(R.id.mwebview);
        viewHelper = WebViewHelper.getInstance();
    }
    /**
     * WebView对象
     */
    private WebView mMwebview;
    /**
     * WebViewHelper对象
     */
    private WebViewHelper viewHelper;
}
