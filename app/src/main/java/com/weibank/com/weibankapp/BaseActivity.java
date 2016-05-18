package com.weibank.com.weibankapp;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.weibank.com.weibankapp.utils.NormalMethods;
import com.weibank.com.weibankapp.utils.SystemBarTintManager;

public class BaseActivity extends AppCompatActivity{

//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//
//        super.onCreate(savedInstanceState);
//        setStateBarColor(R.color.colorPrimary);
//    }
//
//    protected void setStateBarColor(int resId) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window win = getWindow();
//            WindowManager.LayoutParams winParams = win.getAttributes();
//            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//            winParams.flags |= bits;
//            win.setAttributes(winParams);
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(resId);
//            tintManager.setStatusBarDarkMode(true, this);
//        }
//    }
    /**
     * 重写onKeyDown方法
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - exitTime > 2000){

                String str = "再按一次退出微银行";
                NormalMethods.toastShowContent(this, str);
                //Toast.makeText(this, "再按一次退出微银行", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else{

                this.finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 计时器
     */
    private long exitTime = 0;
}
