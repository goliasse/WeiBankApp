package com.weibank.com.weibankapp;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FirstH5Activity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_h5);
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
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setBuiltInZoomControls(true);
        settings.setLoadWithOverviewMode(true);
        String stra = "http://192.168.1.158:1234/weibank-portal/app/appDownload";
        mWebview.loadUrl(stra);
        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

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
     * 初始化对象方法
     */
    private void assignViews(){

        mWebview = (WebView) findViewById(R.id.webview);
    }
    /**
     * WebView对象
     */
    private WebView mWebview;
}
