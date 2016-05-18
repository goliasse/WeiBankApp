package com.weibank.com.weibankapp.utils;
import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/3/30.
 */
public class SharedPreferencesUtil{

    public static void putLoginBooleanValue(Activity activity,boolean bool){

        SharedPreferences share = activity.getSharedPreferences(LOGINFLAG,
                                                                activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putBoolean(LOGINKEY,bool);
        editor.apply();
    }
    public static boolean getLoginBooleanValue(Activity activity){

        SharedPreferences share = activity.getSharedPreferences(LOGINFLAG,
                                                                activity.MODE_PRIVATE);
        return share.getBoolean(LOGINKEY,false);
    }
    private static final String LOGINFLAG = "loginFlag";
    private static final String LOGINKEY = "loginKey";
}
