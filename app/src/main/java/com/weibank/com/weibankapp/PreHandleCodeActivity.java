package com.weibank.com.weibankapp;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.utils.NormalMethods;

public class PreHandleCodeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_handle_code);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        /**
         *  跳转到扫描二维码界面
         */
        Intent intent = new Intent();
        intent.setClass(this, MipcaActivityCapture.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, Constants.STARTACTIVITYFORRESULT);
    }
    @Override
    protected void onActivityResult(int requestCode,
                                     int resultCode, Intent data){
        switch (requestCode){
            case Constants.STARTACTIVITYFORRESULT:
                if(resultCode == RESULT_OK){

                    String resultValue = data.getStringExtra("result");
                    if(NormalMethods.isNotEmpty(resultValue)){

                        //Log.e("myerror",resultValue);
                        Intent intent = new Intent();
                        intent.setClass(this, HandleCodeActivity.class);
                        intent.putExtra("urlvalue", resultValue);
                        startActivity(intent);
                    }else{

                        String errorMsg = "二维码内容为空...";
                        NormalMethods.toastShowContent(this, errorMsg);
                        return;
                    }
                }else{

                    String tip = "扫描二维码失败...";
                    NormalMethods.toastShowContent(this, tip);
                    return;
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
