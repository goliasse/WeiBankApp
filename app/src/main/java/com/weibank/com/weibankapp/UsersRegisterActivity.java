package com.weibank.com.weibankapp;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.weibank.com.weibankapp.beans.UserRegisterInfoBean;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.constants.UserOperationParams;
import com.weibank.com.weibankapp.utils.AnimationMethods;
import com.weibank.com.weibankapp.utils.HttpUtilsHelper;
import com.weibank.com.weibankapp.utils.MD5Util;
import com.weibank.com.weibankapp.utils.NormalMethods;
import com.weibank.com.weibankapp.views.LoadingProgressDialog;
import com.weibank.com.weibankapp.views.SameTextWatcher;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UsersRegisterActivity extends EBaseCompatActivity
                                    implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_register);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        /***
         * 调用初始化方法
         */
        assignViews();
        //AnimationMethods.setupWindowFade(this);
//        phone = mPhoneNumber.getText().toString().trim();
//        pwd = mPwd.getText().toString().trim();
//        epwd = mEnsurePwd.getText().toString().trim();
//        valicode = mValicode.getText().toString().trim();
//        recommender = mTuijianren.getText().toString().trim();
    }
    @Override
    public void onClick(View v){
       switch(v.getId()){
           //获取验证码
           case R.id.getvalicode:

               phone = mPhoneNumber.getText().toString().trim();
               count = 60;
               new UpdateButtonThread().start();

               Map<String,String> param = new HashMap<>();
               param.put(MOBILE, phone);

               ConcurrentHashMap<String,String> value = new ConcurrentHashMap<>();
               value.put(MOBILE, phone);

               httpUtilsHelper.post(REQUESTURL_GETVERCODE, value,
                                      new RequestCallBack<String>(){
                   @Override
                   public void onSuccess(ResponseInfo<String> responseInfo){

                       JSONObject json = JSONObject.parseObject(responseInfo.result);
                       String return_code = json.getString(RETURN_CODE);
                       if(return_code.equals(UserOperationParams.BACK_RETURN_CODE)){

                           NormalMethods.toastShowContent(UsersRegisterActivity.this,
                                                          "短信验证码发送成功,请注意查收.");
//                           Toast.makeText(UsersRegisterActivity.this,
//                                          "短信验证码发送成功,请注意查收.",
//                                          Toast.LENGTH_SHORT).show();
                       }else{

                           NormalMethods.toastShowContent(UsersRegisterActivity.this,
                                                          "短信验证码发送失败,请稍后再试.");
//                           Toast.makeText(UsersRegisterActivity.this,
//                                          "短信验证码发送失败,请稍后再试.",
//                                          Toast.LENGTH_SHORT).show();
                       }
                   }
                   @Override
                   public void onFailure(HttpException e, String s){

                       NormalMethods.toastShowContent(UsersRegisterActivity.this,
                                                      "短信验证码发送失败,请稍后再试.");
//                       Toast.makeText(UsersRegisterActivity.this,
//                                      "短信验证码发送失败,请稍后再试.",
//                                      Toast.LENGTH_SHORT).show();
                   }
               });
               break;
           //用户注册
           case R.id.ausers_register:

               phone = mPhoneNumber.getText().toString().trim();
               pwd = mPwd.getText().toString().trim();
               epwd = mEnsurePwd.getText().toString().trim();
               valicode = mValicode.getText().toString().trim();
               recommender = mTuijianren.getText().toString().trim();
               if(!NormalMethods.isNotEmpty(phone)){

                   NormalMethods.toastShowContent(this, "手机号不能为空");
//                   Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                   return;
               }
               if(!NormalMethods.isNotEmpty(pwd) || !NormalMethods.isNotEmpty(epwd)){

                   NormalMethods.toastShowContent(this, "输入密码不能为空");
//                   Toast.makeText(this, "输入密码不能为空", Toast.LENGTH_SHORT).show();
                   return;
               }
               if(!epwd.equals(pwd)){

                   NormalMethods.toastShowContent(this, "两次输入的密码不一致");
//                   Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
               }
               if(!NormalMethods.isNotEmpty(valicode)){

                   NormalMethods.toastShowContent(this, "验证码不能为空");
//                   Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                   return;
               }
               if(isSelected  == false){

                   NormalMethods.toastShowContent(this, "请浏览并同意微银行用户协议");
//                   Toast.makeText(this, "请浏览并同意微银行用户协议", Toast.LENGTH_SHORT).show();
                   return;
               }
               UserRegisterInfoBean bean = new UserRegisterInfoBean();
               bean.setPhone(phone);
               bean.setPassword(pwd);
               bean.setValicode(valicode);
               bean.setRecommender(recommender);
               new RegisterThread(bean).start();
//               Map<String,String> params = new HashMap<>();
//               params.put(MOBILE,phone);
//               params.put(PASSWORD, MD5Util.getMD5(pwd));
//               params.put(VERCODE,valicode);
//               params.put(RECOMMENDER,recommender);
//
//               httpUtilsHelper.post(REQUESTURL_REGIST, params, new RequestCallBack<String>(){
//                   @Override
//                   public void onSuccess(ResponseInfo<String> responseInfo){
//
//                       Log.e("myerror",responseInfo.result);
//                   }
//                   @Override
//                   public void onFailure(HttpException e, String s){
//
//                       Log.e("myerror",s);
//                       Log.e("myerror",e.getMessage());
//                   }
//               });
               break;
           //同意用户协议
           case R.id.agree_protocol:

               isSelected = true;
               break;
           //浏览协议内容
           case R.id.protocol_contents:


               break;
       }
    }
    /**
     * 内部类----用户注册线程
     */
    class RegisterThread extends Thread{

        private UserRegisterInfoBean bean;
        private Handler mHandler;

        public RegisterThread(UserRegisterInfoBean bean){

            this.bean = bean;
//            dialog = new LoadingProgressDialog(UsersRegisterActivity.this,
//                                                R.style.dialog,R.anim.loadingprogressbar);
            dialog.show();
        }
        @Override
        public void run(){

            super.run();
            Map<String,String> params = new HashMap<>();
            params.put(MOBILE,bean.getPhone());
            params.put(PASSWORD, MD5Util.getMD5(bean.getPassword()));
            params.put(VERCODE,bean.getValicode());
            params.put(RECOMMENDER, bean.getRecommender());

            httpUtilsHelper.post(REQUESTURL_REGIST, params, new RequestCallBack<String>(){
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo){

                    Log.e("myerror", responseInfo.result);
                    JSONObject object = JSON.parseObject(responseInfo.result);
                    String mValue = object.getString(RETURN_CODE);
                    if(NormalMethods.isNotEmpty(mValue)
                            && mValue.equals(UserOperationParams.BACK_RETURN_CODE)){

                        mHandler.sendEmptyMessage(UserOperationParams.LOGIN_SUCCESS);
                    }else{

                        mHandler.sendEmptyMessage(UserOperationParams.LOGIN_FAILED);
                    }
                }
                @Override
                public void onFailure(HttpException e, String s){

                    Log.e("myerror", s);
//                    dialog.dismiss();

                    NormalMethods.toastShowContent(UsersRegisterActivity.this,
                            "网络连接失败,请检查您的网络.");
//                    Toast.makeText(UsersRegisterActivity.this,
//                                   "网络连接失败,请检查您的网络.", Toast.LENGTH_SHORT).show();
                }
            });
            Looper.prepare();
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg){
                    super.handleMessage(msg);
                    if(msg.what == UserOperationParams.LOGIN_SUCCESS){

                        dialog.dismiss();
                        NormalMethods.exitAndSwicthActivity
                                      (UsersRegisterActivity.this, MainActivity.class, true);
                    }else{

//                        dialog.dismiss();
                         NormalMethods.toastShowContent(UsersRegisterActivity.this,
                                                        "登录失败,请稍后再试.");
//                        Toast.makeText(UsersRegisterActivity.this,
//                                       "登录失败,请稍后再试.", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            Looper.loop();
        }
    }
    /**
     *  更改button线程
     */
    class UpdateButtonThread extends Thread{
        @Override
        public void run(){
            super.run();
            /**
             * 同步处理 使线程安全
             */
            synchronized (this){

                while(count >= 0){
                    try{

                        Thread.sleep(1000);
                        count--;
                    }catch (Exception e){

                        e.printStackTrace();
                    }
                    if(count == 0){

                        handler.sendEmptyMessage(UserOperationParams.REGISTER_SUCCESS);
                    }else{

                        handler.sendEmptyMessage(UserOperationParams.REGISTER_FAILED);
                    }
                }
            }
        }
    }
    /**
     * Handler对象
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){

            super.handleMessage(msg);
            if(msg.what == UserOperationParams.REGISTER_SUCCESS){

                mGetvalicode.setEnabled(true);
                mGetvalicode.setText("获取验证码");
            }else if(msg.what == UserOperationParams.REGISTER_FAILED){

                mGetvalicode.setEnabled(false);
                mGetvalicode.setText("倒计时"+ NormalMethods.one2Two(count) +"s");
            }
        }
    };
    /**
     * 初始化对象方法
     */
    private void assignViews(){

        mPhoneNumber = (EditText) findViewById(R.id.phone_number);
        mPwd = (EditText) findViewById(R.id.pwd);
        mEnsurePwd = (EditText) findViewById(R.id.ensure_pwd);
        mValicode = (EditText) findViewById(R.id.valicode);
        mTuijianren = (EditText) findViewById(R.id.tuijianren);

        mGetvalicode = (Button) findViewById(R.id.getvalicode);
        mGetvalicode.setOnClickListener(this);
        mAusersRegister = (Button) findViewById(R.id.ausers_register);
        mAusersRegister.setOnClickListener(this);
        mAgreeProtocol = (CheckBox) findViewById(R.id.agree_protocol);
        mAgreeProtocol.setOnClickListener(this);
        mProtocolContents = (TextView) findViewById(R.id.protocol_contents);
        mProtocolContents.setOnClickListener(this);

        httpUtilsHelper = HttpUtilsHelper.getInstance();
    }
    /**
     * EditText对象
     */
    private EditText mPhoneNumber;
    private EditText mPwd;
    private EditText mEnsurePwd;
    private EditText mValicode;
    private EditText mTuijianren;
    /**
     * String对象
     */
    String phone ;
    String pwd ;
    String epwd ;
    String valicode;
    String recommender;
    /**
     * Button对象
     */
    private Button mGetvalicode;
    private Button mAusersRegister;
    private CheckBox mAgreeProtocol;
    /**
     * TextView对象
     */
    private TextView mProtocolContents;
    /**
     * boolean变量
     */
    private boolean isSelected = false;
    /**
     * 与服务器交互的url
     */
    private  final String REQUESTURL_GETVERCODE = Constants.WEBURL + "app/getVercode";
    private  final String REQUESTURL_REGIST = Constants.WEBURL + "app/regist";
    /**
     * 相关的常量
     */
    private final String MOBILE = "mobile";
    private final String PASSWORD = "password";
    private final String VERCODE = "vercode";
    private final String RECOMMENDER = "recommender";
    private final String RETURN_CODE = "return_code";
    /**
     * 计数器
     */
    private int count = 0;
    /**
     * HttpUtilsHelper对象
     */
    private HttpUtilsHelper httpUtilsHelper;
    /**
     * LoadingProgressDialog对象
     */
    private LoadingProgressDialog dialog;
}
