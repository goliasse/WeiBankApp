package com.weibank.com.weibankapp;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.weibank.com.weibankapp.utils.NormalMethods;

public class LauncherActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        /**
         * 调用初始化对象方法
         */
        assignViews();
        /**
         *  调用初始化动画方法
         */
        initAnim();
        /**
         *  调用初始化监听器方法
         */
        initListener();
//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run(){
//
//                NormalMethods.exitAndSwicthActivity(LauncherActivity.this,UserLoginActivity.class,true);
//            }
//        },2000);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 初始化animation方法
     */
    private void initAnim(){

        mFade = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in);
        mFade.setDuration(500);

        mScale = AnimationUtils.loadAnimation(this,R.anim.welcome_fade_in_scale);
        mScale.setDuration(2000);

        mWelcomeBg.startAnimation(mFade);
    }
    /**
     * 设置animation监听器方法
     */
    private void initListener(){

        mFade.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation){}
            @Override
            public void onAnimationRepeat(Animation animation){}
            @Override
            public void onAnimationEnd(Animation animation){

                mWelcomeBg.startAnimation(mScale);
            }
        });

        mScale.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation){}
            @Override
            public void onAnimationRepeat(Animation animation){}
            @Override
            public void onAnimationEnd(Animation animation){

                NormalMethods.exitAndSwicthActivity(LauncherActivity.this,
                                                    UserLoginActivity.class,true);
            }
        });
    }
    /**
     *  初始化对象方法
     */
    private void assignViews(){

        mWelcomeBg = (ImageView) findViewById(R.id.welcome_bg);

    }
    /**
     * ImageView对象
     */
    private ImageView mWelcomeBg;
    /**
     * Animation对象
     */
    private Animation mFade;
    private Animation mScale;

}
