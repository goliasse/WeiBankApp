package com.weibank.com.weibankapp.views;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 *  Created by Administrator on 2016/4/22.
 */
public class ShowWebView extends WebView{

    public ShowWebView(Context context){

        super(context);
        init();
    }
    public ShowWebView(Context context, AttributeSet attrs){

        super(context, attrs);
        init();
    }
    public ShowWebView(Context context, AttributeSet attrs, int defStyleAttr){

        super(context, attrs, defStyleAttr);
        init();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if(keyCode == KeyEvent.KEYCODE_BACK && this.canGoBack()){

            this.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     *  初始化方法
     */
    private void init(){

        //ShowWebView webView = this;

        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

//        webView.loadUrl(getWebViewUrl());
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url){
//
//                view.loadUrl(url);
//                return true;
//            }
//        });
    }
//    public String getWebViewUrl(){
//
//        return webViewUrl;
//    }
//    public void setWebViewUrl(String webViewUrl){
//
//        this.webViewUrl = webViewUrl;
//    }
    public void loadWebView(String webViewUrl){

        webView.loadUrl(webViewUrl);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){

                view.loadUrl(url);
                return true;
            }
        });
    }
    /**
     *  ShowWebView对象
     */
    private final ShowWebView webView = this;
    /**
     *  url对象
     */
    //private String webViewUrl;
}
