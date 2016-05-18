package com.weibank.com.weibankapp;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.constants.UserOperationParams;
import com.weibank.com.weibankapp.utils.HttpUtilsHelper;
import com.weibank.com.weibankapp.utils.MD5Util;
import com.weibank.com.weibankapp.utils.NormalMethods;
import com.weibank.com.weibankapp.views.SameTextWatcher;

import java.util.HashMap;
import java.util.Map;

public class UpdatePwdActivity extends UEBaseCompatActivity
                                implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        /**
         * 调用初始化对象方法
         */
        assignViews();
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            //返回到上一页面
            case R.id.rl_topview:

                UpdatePwdActivity.this.finish();
                break;
            case R.id.update_pwd:

                srcPwd = mOldPwdInput.getText().toString().trim();
                newPwd = mNewPwdInput.getText().toString().trim();
                ensPwd = mEnsureNewPwdInput.getText().toString().trim();

                if(NormalMethods.isNotEmpty(srcPwd)
                                 && NormalMethods.isNotEmpty(newPwd)
                                 && NormalMethods.isNotEmpty(ensPwd)
                                 && newPwd.equals(ensPwd)){

                    Map<String,String> params = new HashMap<>();
                    params.put(SRCPASSWORD, MD5Util.getMD5(srcPwd));
                    params.put(NEWPASSWORD,MD5Util.getMD5(newPwd));
                    new UpdatePwdThread(requestUrl,params).start();
                }else{

                    NormalMethods.toastShowContent(this, "密码不能为空或两次输入密码不一致.");
//                    Toast.makeText(this, "密码不能为空或两次输入密码不一致.",
//                                   Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    /**
     *  内部类------获取项目数据
     */
    class UpdatePwdThread extends Thread{
        /**
         *  构造器
         */
        public UpdatePwdThread(String requestUrl,Map<String,String> params){

            this.requestUrl = requestUrl;
            this.params = params;
        }
        /**
         * Handler对象
         */
//        protected Handler handler;
        /**
         * 请求Url
         */
        protected String requestUrl;
        /**
         * Map对象
         */
        protected Map<String,String> params;
        @Override
        public void run(){

            super.run();
            if(NormalMethods.isNetWorkConnected(UpdatePwdActivity.this)){

                httpUtilsHelper = HttpUtilsHelper.getInstance();
                httpUtilsHelper.post(requestUrl, params,new RequestCallBack<String>(){
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo){

                        JSONObject object = JSON.parseObject(responseInfo.result);
                        if(object.get(RETURN_CODE).equals(UserOperationParams.BACK_RETURN_CODE)){

                            handler.sendEmptyMessage(UserOperationParams.GETPROJECTVALUE);
                        }
                    }
                    @Override
                    public void onFailure(HttpException e, String s){

                        NormalMethods.toastShowContent(UpdatePwdActivity.this, "获取数据失败.");
//                        Toast.makeText(UpdatePwdActivity.this, "获取数据失败.",
//                                       Toast.LENGTH_SHORT).show();
                    }
                });
            }else{

                NormalMethods.toastShowContent(UpdatePwdActivity.this, "请检查您的网络.");
                NormalMethods.exitAndSwicthActivity(UpdatePwdActivity.this);
//                Toast.makeText(UpdatePwdActivity.this, "请检查您的网络.",
//                               Toast.LENGTH_SHORT).show();
            }
        }
    }
    final private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){

            super.handleMessage(msg);
            if(msg.what == UserOperationParams.GETPROJECTVALUE){

                  NormalMethods.toastShowContent(UpdatePwdActivity.this, "修改密码成功...");
//                Toast.makeText(UpdatePwdActivity.this, "修改密码成功...",
//                               Toast.LENGTH_SHORT).show();
            }
        }
    };
    /**
     * 初始化对象方法
     */
    private void assignViews(){
        /**
         * EditText对象
         */
        mOldPwdInput = (EditText) findViewById(R.id.old_pwd_input);
        mNewPwdInput = (EditText) findViewById(R.id.new_pwd_input);
        mEnsureNewPwdInput = (EditText) findViewById(R.id.ensure_new_pwd_input);
        /**
         * Button对象
         */
        mUpdatePwd = (Button) findViewById(R.id.update_pwd);
        mUpdatePwd.setOnClickListener(this);
        /**
         * TextView对象
         */
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("修改密码");
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
    }
    /**
     * EditText对象
     */
    private EditText mOldPwdInput;
    private EditText mNewPwdInput;
    private EditText mEnsureNewPwdInput;
    /**
     * Button对象
     */
    private Button mUpdatePwd;
    /**
     * TextView对象
     */
    private TextView mTopviewText;
    /**
     * RelativeLayout对象
     */
    private RelativeLayout mRlTopview;
    /**
     * HttpUtilsHelper对象
     */
    private HttpUtilsHelper httpUtilsHelper;
    /**
     * String对象
     */
    private String srcPwd = "";
    private String newPwd = "";
    private String ensPwd = "";
    /**
     *  常量
     */
    private  final String RETURN_CODE = "return_code";
    private  final String SRCPASSWORD = "srcPassword";
    private  final String NEWPASSWORD = "newPassword";
    final String requestUrl = Constants.WEBURL + "app/modifyPassword";
}
