package com.weibank.com.weibankapp;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.utils.WebViewHelper;
import com.weibank.com.weibankapp.views.ShowWebView;

public class HandleCodeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_code);
        if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.KITKAT){

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        /**
         *  调用初始化对象方法
         */
        assignViews();
        intent = this.getIntent();
        urlValue = intent.getStringExtra("urlvalue");
        Log.e("myerror", urlValue);
        if(urlValue.matches(Constants.URL)){

            mWebview.setVisibility(View.VISIBLE);
            mResultLayout.setVisibility(View.GONE);
            mResultTv.setVisibility(View.GONE);
            //http://weixin.qq.com/r/_EPD25TEAsl9rdC49xbq
            //http://weibo.com/1653603955/Dkwbiq59M?type=comment
            //webViewHelper.handleWebView(mWebview,urlValue);
        }else{

           mWebview.setVisibility(View.GONE);
           mResultLayout.setVisibility(View.VISIBLE);
           mResultTv.setVisibility(View.VISIBLE);

           mResultTv.setText(urlValue);
        }
    }
    /**
     *  初始化对象方法
     */
    private void assignViews(){

        //webViewHelper = WebViewHelper.getInstance();

        //mWebview = (WebView) findViewById(R.id.webview);
        mWebview = (ShowWebView) findViewById(R.id.webview);
        mResultLayout = (LinearLayout) findViewById(R.id.resultLayout);
        mResultTv = (TextView) findViewById(R.id.resultTv);
    }
    /**
     * WebViewHelper对象
     */
    //private WebViewHelper webViewHelper;
    /**
     *  WebView对象
     */
    private ShowWebView mWebview;
    //private WebView mWebview;
    /**
     *  LinearLayout对象
     */
    private LinearLayout mResultLayout;
    /**
     * TextView对象
     */
    private TextView mResultTv;
    /**
     *  Intent对象
     */
    private Intent intent;
    /**
     *  url
     */
    private String urlValue = "";
}
