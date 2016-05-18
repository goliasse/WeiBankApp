package com.weibank.com.weibankapp;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.weibank.com.weibankapp.beans.UserBean;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.constants.UserOperationParams;
import com.weibank.com.weibankapp.utils.AnimationMethods;
import com.weibank.com.weibankapp.utils.HttpUtilsHelper;
import com.weibank.com.weibankapp.utils.MD5Util;
import com.weibank.com.weibankapp.utils.NormalMethods;
import com.weibank.com.weibankapp.views.LoadingProgressDialog;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.LogRecord;

public class UserLoginActivity extends EBaseCompatActivity
                                implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        /**
         *  调用初始化方法
         */
        assignViews();
//        AnimationMethods.setupWindowSlide(UserLoginActivity.this);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            //用户登录
            case R.id.user_login:
//                NormalMethods.exitAndSwicthActivity(UserLoginActivity.this,
//                                                    MainActivity.class,true);

                final String account = mLoginaccountInput.getText().toString().trim();
                final String pwd = mLoginpwdInput.getText().toString().trim();
                //final String requestUrl = Constants.WEBURL + "app/login";
                new LoginThread(account,pwd,Constants.USERLOGIN_URL).start();

//                final String requestUrl = Constants.WEBURL + "app/getHomePageInfo";
//                new GetProjectDataThread(requestUrl).start();

//                if(NormalMethods.isNetWorkConnected(UserLoginActivity.this)){
//                    if(NormalMethods.isNotEmpty(account) && NormalMethods.isNotEmpty(pwd)){
//
//                        httpUtilsHelper = HttpUtilsHelper.getInstance();
//                        Map<String,String> map = new HashMap<>();
//                        map.put(MOBILE, account);
//                        map.put(PASSWORD, MD5Util.getMD5(pwd));
//                        httpUtilsHelper.post(requestUrl, map, new RequestCallBack<String>(){
//                            @Override
//                            public void onFailure(HttpException e, String s){
//
//                                Toast.makeText(UserLoginActivity.this,
//                                              "网络连接失败,请稍后再试",
//                                               Toast.LENGTH_SHORT).show();
//                            }
//                            @Override
//                            public void onSuccess(ResponseInfo<String> responseInfo){
//
//                                Log.e("myerror", responseInfo.result);
//                                JSONObject object = JSON.parseObject(responseInfo.result);
//                                String info = object.getString(RETURN_CODE);
//                                if(NormalMethods.isNotEmpty(info)
//                                        && info.equals(UserOperationParams.BACK_RETURN_CODE)){
//
//                                    NormalMethods.exitAndSwicthActivity(UserLoginActivity.this,
//                                                                        MainActivity.class,true);
//                                }else{
//                                    Toast.makeText(UserLoginActivity.this, "登录失败,请稍后再试",
//                                                   Toast.LENGTH_SHORT).show();
//                                    return;
//                                }
//                            }
//                        });
//                    }else{
//
//                        Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }else{
//
//                    Toast.makeText(this, "您的网络好像有点问题~~", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                break;
            //用户注册
            case R.id.user_register:
                startActivity(new Intent(UserLoginActivity.this,UsersRegisterActivity.class));
                UserLoginActivity.this.finish();
                //AnimationMethods.startToActivity(UserLoginActivity.this,UsersRegisterActivity.class);
                break;
            //忘记密码
            case R.id.forget_pwd:
                NormalMethods.exitAndSwicthActivity(UserLoginActivity.this,
                                                    FindPwdActivity.class,false);
                break;
//            case R.id.rl_topview:
//                shared = getSharedPreferences(Constants.SHAREDDATAS, MODE_PRIVATE);
//                editor = shared.edit();
//                editor.putInt(Constants.FIRST_OR_NOT, 0);
//                editor.apply();
//                break;
        }
    }
    /**
     *  内部类---登录所需的线程
     */
    class LoginThread extends Thread{

        private String account;
        private String pwd;
        private String requestUrl;

        private Handler mHandler;

        public LoginThread(String account,String pwd,String requestUrl){

            this.account = account;
            this.pwd = pwd;
            this.requestUrl = requestUrl;

//            dialog = new LoadingProgressDialog(UserLoginActivity.this,
//                                                R.style.dialog,R.anim.loadingprogressbar);
//            dialog.show();
        }
        @Override
        public void run(){

            if(NormalMethods.isNetWorkConnected(UserLoginActivity.this)){
                if(NormalMethods.isNotEmpty(account) && NormalMethods.isNotEmpty(pwd)){

                    httpUtilsHelper = HttpUtilsHelper.getInstance();
                    ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
                    map.put(MOBILE, account);
                    map.put(PASSWORD, MD5Util.getMD5(pwd));
                    httpUtilsHelper.post(requestUrl, map, new RequestCallBack<String>(){
                        @Override
                        public void onFailure(HttpException e, String s){

//                            dialog.dismiss();
                            NormalMethods.toastShowContent(UserLoginActivity.this,
                                                           "服务器连接失败,请稍后再试");
//                            Toast.makeText(UserLoginActivity.this,
//                                           "网络连接失败,请稍后再试",
//                                           Toast.LENGTH_SHORT).show();
                            Log.e("myerror", s);

                        }
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo){

                            Log.e("myerror", responseInfo.result);
                            JSONObject object = JSON.parseObject(responseInfo.result);
                            String info = object.getString(RETURN_CODE);
                            if(NormalMethods.isNotEmpty(info)
                                    && info.equals(UserOperationParams.BACK_RETURN_CODE)){

                                UserBean bean = UserBean.getInstance();
                                JSONObject item = object.getJSONObject(Constants.DATA);
                                bean.setCustomerId(item.getString("customerId"));
                                bean.setCasId(item.getString("casId"));
                                bean.setName(item.getString("name"));
                                bean.setMobile(item.getString("mobile"));

                                mHandler.sendEmptyMessage(UserOperationParams.LOGIN_SUCCESS);
                            }else{

                                mHandler.sendEmptyMessage(UserOperationParams.LOGIN_FAILED);
                            }
                        }
                    });
                }else{

                    NormalMethods.toastShowContent(UserLoginActivity.this,
                                                   "账号或密码不能为空");
//                    Toast.makeText(UserLoginActivity.this,
//                                   "账号或密码不能为空",
//                                   Toast.LENGTH_SHORT).show();
                    return;
                }
            }else{

                NormalMethods.toastShowContent(UserLoginActivity.this,
                        "您的网络好像有点问题~~");
                NormalMethods.exitAndSwicthActivity(UserLoginActivity.this);
//                Toast.makeText(UserLoginActivity.this,
//                               "您的网络好像有点问题~~",
//                               Toast.LENGTH_LONG).show();
                return;
            }
            Looper.prepare();
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg){

                    super.handleMessage(msg);
                    if(msg.what == UserOperationParams.LOGIN_SUCCESS){

//                        dialog.dismiss();
                        NormalMethods.exitAndSwicthActivity(UserLoginActivity.this,
                                                            MainActivity.class,true);
//                        Intent intent = new Intent();
//                        intent.putExtra("bundle",bundle);
//                        UserLoginActivity.this.startActivity(intent);
//                        UserLoginActivity.this.finish();
                    }else if(msg.what == UserOperationParams.LOGIN_FAILED){

//                        dialog.dismiss();
                        NormalMethods.toastShowContent(UserLoginActivity.this,
                                                       "登录失败,请稍后再试.");
//                        Toast.makeText(UserLoginActivity.this,
//                                       "登录失败,请稍后再试.",
//                                        Toast.LENGTH_SHORT).show();
                    }
                }
            };
            Looper.loop();
        }
    }
    /**
     *  内部类------获取项目数据
     */
//    class GetProjectDataThread extends Thread{
//        public GetProjectDataThread(String requestUrl){
//
//            this.requestUrl = requestUrl;
//        }
//        /**
//         * Handler对象
//         */
//        protected Handler handler;
//        /**
//         * 请求Url
//         */
//        protected String requestUrl;
//        @Override
//        public void run(){
//
//            super.run();
//            if(NormalMethods.isNetWorkConnected(UserLoginActivity.this)){
//
//                httpUtilsHelper = HttpUtilsHelper.getInstance();
//                httpUtilsHelper.post(requestUrl, new RequestCallBack<String>(){
//                    @Override
//                    public void onSuccess(ResponseInfo<String> responseInfo){
//
//                        JSONObject object = JSON.parseObject(responseInfo.result);
//                        if(object.get(RETURN_CODE).equals(UserOperationParams.BACK_RETURN_CODE)){
//
//                            JSONArray array = object.getJSONArray("recent_product");
//                            for(int i = 0; i < array.size(); i++){
//
//                                JSONObject item = array.getJSONObject(i);
//                                datas[i] = item.getString("expected_annual_rate") + "%";
//                            }
//                        }
//                    }
//                    @Override
//                    public void onFailure(HttpException e, String s){
//
//
//                    }
//                });
//            }else{
//
//                Toast.makeText(UserLoginActivity.this, "请检查您的网络.",
//                               Toast.LENGTH_SHORT).show();
//            }
//            Looper.prepare();
//            handler = new Handler(){
//                @Override
//                public void handleMessage(Message msg){
//
//                    super.handleMessage(msg);
//                    if(msg.what == UserOperationParams.PASSVALUE){
//
//                        handler.sendEmptyMessage(UserOperationParams.PASSVALUE);
//                        bundle.putStringArray("datas",datas);
//                        final String account = mLoginaccountInput.getText().toString().trim();
//                        final String pwd = mLoginpwdInput.getText().toString().trim();
//                        final String requestUrl = Constants.WEBURL + "app/login";
//                        new LoginThread(account,pwd,requestUrl).start();
//                    }
//                }
//            };
//            Looper.loop();
//        }
//    }
    /**
     * 初始化方法
     */
    private void assignViews(){
        /***
         * Button对象
         */
        mUserLogin = (Button) findViewById(R.id.user_login);
        mUserLogin.setOnClickListener(this);
        mUserRegister = (Button) findViewById(R.id.user_register);
        mUserRegister.setOnClickListener(this);
        /**
         * TextView对象
         */
        mForgetPwd = (TextView) findViewById(R.id.forget_pwd);
        mForgetPwd.setOnClickListener(this);
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("登录");
        /**
         * EditText对象
         */
        mLoginaccountInput = (EditText) findViewById(R.id.loginaccount_input);
        mLoginpwdInput = (EditText) findViewById(R.id.loginpwd_input);
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
//        mRlTopview.setOnClickListener(this);
        mRlTopview.setVisibility(View.GONE);
    }
    /**
     *  EditText对象
     */
    private EditText mLoginaccountInput;
    private EditText mLoginpwdInput;
    /**
     * Button对象
     */
    private Button mUserLogin;
    private Button mUserRegister;
    /**
     * TextView对象
     */
    private TextView mForgetPwd;
    private TextView mTopviewText;
    /**
     * ImageView对象
     */
    private RelativeLayout mRlTopview;
    /**
     * SharedPreferences、Editor对象
     */
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;
    /**
     * 常量
     */
    private  final String MOBILE = "mobile";
    private  final String PASSWORD = "password";
    private  final String RETURN_CODE = "return_code";
    /**
     * HttpUtilsHelper对象
     */
    private HttpUtilsHelper httpUtilsHelper;
    /**
     * LoadingProgressDialog对象
     */
    private LoadingProgressDialog dialog;
    /**
     * 存储String[]数组
     */
    private final String[] datas = new String[2];
    private final Bundle bundle = new Bundle();

}
