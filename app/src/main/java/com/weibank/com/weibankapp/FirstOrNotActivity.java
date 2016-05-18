package com.weibank.com.weibankapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.weibank.com.weibankapp.constants.Constants;

public class FirstOrNotActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_or_not);

        shared = getSharedPreferences(Constants.SHAREDDATAS, MODE_PRIVATE);
        editor = shared.edit();
        int first = shared.getInt(Constants.FIRST_OR_NOT, 0);

        if(first == 0){

            startActivity(new Intent(this,LoadingActivity.class));
            this.finish();
        }else {

            startActivity(new Intent(this,UserLoginActivity.class));
            this.finish();
        }
        editor.putInt(Constants.FIRST_OR_NOT,++first);
        editor.apply();
    }
    /**
     * 重写onKeyDown方法
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        //防止用户退出
        if(keyCode == KeyEvent.KEYCODE_BACK){

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * SharedPreferences对象
     */
    private SharedPreferences  shared;
    /**
     * Editor对象
     */
    private SharedPreferences.Editor editor;
}
