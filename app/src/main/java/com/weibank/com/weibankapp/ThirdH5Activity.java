package com.weibank.com.weibankapp;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.text.DecimalFormat;

public class ThirdH5Activity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_h5);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        /**
         * 调用初始化对象方法
         */
        assignViews();
        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(true);
        settings.setLoadWithOverviewMode(true);
        //DisplayMetrics metrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //DecimalFormat format = new DecimalFormat("0.00");
        //float h5width = 420;
        //String result = format.format((float)(metrics.widthPixels) / h5width);
        //mWebview.setInitialScale((int)((Float.valueOf(result)) * 100));
        mWebview.loadUrl("http://www.weibank.cn/");
        mWebview.setWebViewClient(new WebViewClient(){
            @Override
             public boolean shouldOverrideUrlLoading(WebView view, String url){

                view.loadUrl(url);
                return true;
            }
        });
    }
    /**
     * 重写onKeyDown方法
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()){

            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     *  初始化对象方法
     */
    private void assignViews(){

        mWebview = (WebView) findViewById(R.id.webview);
    }
    /**
     * WebView对象
     */
    private WebView mWebview;
}
