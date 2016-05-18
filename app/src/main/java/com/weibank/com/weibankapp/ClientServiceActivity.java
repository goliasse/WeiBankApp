package com.weibank.com.weibankapp;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weibank.com.weibankapp.utils.AnimationMethods;

public class ClientServiceActivity extends UEBaseCompatActivity
                                    implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_service);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        /**
         * 调用初始化对象方法
         */
        assignViews();
        //AnimationMethods.setupWindowFade(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            //返回上一页面
            case R.id.rl_topview:
                ClientServiceActivity.this.finish();
                break;
            //拨打电话
            case R.id.phone_call:
                    Intent intent =
                            new Intent("android.intent.action.CALL",
                                       Uri.parse("tel:02131233901"));
                    this.startActivity(intent);
                break;
        }
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){
        /**
         * ImageView对象
         */
        mCliectserviceIcon = (ImageView) findViewById(R.id.cliectservice_icon);
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
        /**
         * TextView对象
         */
        mClientPhoneNumber = (TextView) findViewById(R.id.client_phone_number);
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("客户服务");
        /**
         * Button对象
         */
        mPhoneCall = (Button) findViewById(R.id.phone_call);
        mPhoneCall.setOnClickListener(this);
    }
    /**
     * ImageView对象
     */
    private ImageView mCliectserviceIcon;
    private RelativeLayout mRlTopview;
    /**
     * TextView对象
     */
    private TextView mClientPhoneNumber;
    private TextView mTopviewText;
    /**
     * Button对象
     */
    private Button mPhoneCall;
}
