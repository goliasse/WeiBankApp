package com.weibank.com.weibankapp.utils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2016/4/22.
 */
public class WebViewHelper{

    /**
     *  处理方法
     */
    public void handleWebView(WebView webView,String url){

        preHanleWebView(webView,url);
    }
    /**
     *  预处理方法
     */
    private void preHanleWebView(WebView webView,String url){

        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){

                view.loadUrl(url);
                return true;
            }
        });
    }
    private WebViewHelper(){}
    /**
     *  单例模式
     */
    public static WebViewHelper getInstance(){
        synchronized(WebViewHelper.class){
            if(instance == null){

                instance = new WebViewHelper();
            }
        }
        return instance;
    }
    /**
     * WebViewHelper对象
     */
    private static WebViewHelper instance;
}
