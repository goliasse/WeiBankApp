package com.weibank.com.weibankapp;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.weibank.com.weibankapp.beans.MainProjectBean;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.constants.UserOperationParams;
import com.weibank.com.weibankapp.utils.AnimationMethods;
import com.weibank.com.weibankapp.utils.HttpUtilsHelper;
import com.weibank.com.weibankapp.utils.NormalMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountSYXQActivity extends UEBaseCompatActivity
                                  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_syxq);

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
        new GetShouYiDataThread(requestUrl).start();
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            //返回上一页面
            case R.id.rl_topview:

                AccountSYXQActivity.this.finish();
                break;
        }
    }
    /**
     *  内部类------获取项目数据
     */
    class GetShouYiDataThread extends Thread{
        /**
         *  构造器
         */
        public GetShouYiDataThread(String requestUrl){

            this.requestUrl = requestUrl;
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
        private Map<String,String> params = new HashMap<>();
        @Override
        public void run(){

            super.run();
            if(NormalMethods.isNetWorkConnected(AccountSYXQActivity.this)){

                httpUtilsHelper = HttpUtilsHelper.getInstance();
                httpUtilsHelper.post(requestUrl, params,new RequestCallBack<String>(){
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo){

                        JSONObject object = JSON.parseObject(responseInfo.result);
                        if(object.get(RETURN_CODE).equals(UserOperationParams.BACK_RETURN_CODE)){

                            arrayLists.add(object.getString("total_income"));
                            arrayLists.add(object.getString("invest_income"));
                            arrayLists.add(object.getString("bonus_income"));
                            arrayLists.add(object.getString( "partner_income"));
                            handler.sendEmptyMessage(UserOperationParams.GETPROJECTVALUE);
                        }
                    }
                    @Override
                    public void onFailure(HttpException e, String s){

                        NormalMethods.toastShowContent(AccountSYXQActivity.this,"获取数据失败.");
//                        Toast.makeText(AccountSYXQActivity.this, "获取数据失败.",
//                                       Toast.LENGTH_SHORT).show();
                    }
                });
            }else{

                NormalMethods.exitAndSwicthActivity(AccountSYXQActivity.this);
                NormalMethods.toastShowContent(AccountSYXQActivity.this, "请检查您的网络.");
//                Toast.makeText(AccountSYXQActivity.this, "请检查您的网络.",
//                               Toast.LENGTH_SHORT).show();
            }
        }
    }
    final private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){

            super.handleMessage(msg);
            if(msg.what == UserOperationParams.GETPROJECTVALUE){

                mTotalShouyi.setText(arrayLists.get(0));
                mTouzhishouyi.setText(arrayLists.get(1));
                mJiangliTouzhishouyi.setText(arrayLists.get(2));
                mHehuorenTouzhishouyi.setText(arrayLists.get(3));
            }
        }
    };
    /**
     * 初始化对象方法
     */
    private void assignViews(){
        /**
         * ImageView对象
         */
        mAccountIcon = (ImageView) findViewById(R.id.account_icon);
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
        /**
         * TextView对象
         */
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("账户收益详情");
        mAccountName = (TextView) findViewById(R.id.account_name);
        mAccountPhone = (TextView) findViewById(R.id.account_phone);
        mTotalShouyi = (TextView) findViewById(R.id.total_shouyi);
        mTouzhishouyi = (TextView) findViewById(R.id.touzhishouyi);
        mJiangliTouzhishouyi = (TextView) findViewById(R.id.jiangli_touzhishouyi);
        mHehuorenTouzhishouyi = (TextView) findViewById(R.id.hehuoren_touzhishouyi);
    }
    /**
     * TextView对象
     */
    private TextView mAccountName;
    private TextView mAccountPhone;
    private TextView mTotalShouyi;
    private TextView mTouzhishouyi;
    private TextView mJiangliTouzhishouyi;
    private TextView mHehuorenTouzhishouyi;
    private TextView mTopviewText;
    /**
     * ImageView对象
     */
    private ImageView mAccountIcon;
    private RelativeLayout mRlTopview;
    /**
     * HttpUtilsHelper对象
     */
    private HttpUtilsHelper httpUtilsHelper;
    /**
     *  常量
     */
    private  final String RETURN_CODE = "return_code";
    final String requestUrl = Constants.WEBURL + "app/getCustomerIncome";
    /**
     * 存储String对象列表
     */
    private ArrayList<String> arrayLists = new ArrayList<>();
}
