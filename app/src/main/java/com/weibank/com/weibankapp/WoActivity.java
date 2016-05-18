package com.weibank.com.weibankapp;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.weibank.com.weibankapp.adapter.WoGridViewAdapter;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.constants.UserOperationParams;
import com.weibank.com.weibankapp.utils.AnimationMethods;
import com.weibank.com.weibankapp.utils.HttpUtilsHelper;
import com.weibank.com.weibankapp.utils.NormalMethods;
import com.weibank.com.weibankapp.views.DefineGridView;

import java.util.HashMap;
import java.util.Map;

public class WoActivity extends BaseActivity
                         implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo);
        /**
         * 调用初始化方法
         */
        assignViews();
        mWoGridview.setAdapter(new WoGridViewAdapter(this, images, texts,resource));
        mWoGridview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                     int position, long id){
                switch(position){
                    case 0:
                        NormalMethods.exitAndSwicthActivity(WoActivity.this,
                                ChongZhiActivity.class, false);
                        break;
                    case 1:
                        NormalMethods.exitAndSwicthActivity(WoActivity.this,
                                WithdrawMoneyActivity.class, false);
                        break;
                    case 2:
                        NormalMethods.exitAndSwicthActivity(WoActivity.this,
                                WodegoumaiActivity.class, false);
                        break;
                    case 3:
                        NormalMethods.exitAndSwicthActivity(WoActivity.this,
                                TradeRecordActivity.class, false);
                        break;
                }
            }
        });
        //AnimationMethods.setupWindowSlide(this);
        new GetShouYiDataThread(requestUrl).start();
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            //账户余额
            case R.id.ll_zhanghuyuer:
                NormalMethods.exitAndSwicthActivity(WoActivity.this,
                                                    AccountSYXQActivity.class,false);
//                AnimationMethods.startToActivity(WoActivity.this,AccountSYXQActivity.class);
                break;
            //累计收益
            case R.id.ll_leijishouyi:
                NormalMethods.exitAndSwicthActivity(WoActivity.this,
                                                    LeiJiShouYiActivity.class, false);
                break;
            //logo图像
            case R.id.wo_logo:


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
            if(NormalMethods.isNetWorkConnected(WoActivity.this)){

                httpUtilsHelper = HttpUtilsHelper.getInstance();
                httpUtilsHelper.post(requestUrl, params,new RequestCallBack<String>(){
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo){

                        JSONObject object = JSON.parseObject(responseInfo.result);
                        if(object.get(RETURN_CODE).equals(UserOperationParams.BACK_RETURN_CODE)){

                            balance = object.getString("data");
                            Log.e("myerror",balance);
                            handler.sendEmptyMessage(UserOperationParams.GETPROJECTVALUE);
                        }
                    }
                    @Override
                    public void onFailure(HttpException e, String s){

                        NormalMethods.toastShowContent(WoActivity.this, "获取数据失败.");
//                        Toast.makeText(WoActivity.this, "获取数据失败.",
//                                       Toast.LENGTH_SHORT).show();
                    }
                });
            }else{

                      NormalMethods.exitAndSwicthActivity(WoActivity.this);
                      NormalMethods.toastShowContent(WoActivity.this, "请检查您的网络.");
//                    Toast.makeText(WoActivity.this, "请检查您的网络.",
//                                   Toast.LENGTH_SHORT).show();
            }
        }
    }
    final private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){

            super.handleMessage(msg);
            if(msg.what == UserOperationParams.GETPROJECTVALUE){

                mZhuanhuYuer.setText(balance);
            }
        }
    };
    /**
     * 初始化方法
     */
    private void assignViews(){
        /**
         * TextView对象
         */
        mWoYuer = (TextView) findViewById(R.id.wo_yuer);
        mZhuanhuYuer = (TextView) findViewById(R.id.zhuanhu_yuer);
        mLeijiShouyi = (TextView) findViewById(R.id.leiji_shouyi);
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("我的账户");
        /**
         * LinearLayout对象
         */
        mLlZhanghuyuer = (LinearLayout) findViewById(R.id.ll_zhanghuyuer);
        mLlZhanghuyuer.setOnClickListener(this);
        mLlLeijishouyi = (LinearLayout) findViewById(R.id.ll_leijishouyi);
        mLlLeijishouyi.setOnClickListener(this);
        /**
         * ImageView对象
         */
        mWoLogo = (ImageView) findViewById(R.id.wo_logo);
        mWoLogo.setOnClickListener(this);
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setVisibility(View.GONE);
        /**
         * GridView对象
         */
        mWoGridview = (DefineGridView) findViewById(R.id.wo_gridview);
    }
    /**
     * TextView对象
     */
    private TextView mWoYuer;
    private TextView mZhuanhuYuer;
    private TextView mLeijiShouyi;
    private TextView mTopviewText;
    /**
     * LinearLayout对象
     */
    private LinearLayout mLlZhanghuyuer;
    private LinearLayout mLlLeijishouyi;
    /**
     * ImageView对象
     */
    private ImageView mWoLogo;
    private RelativeLayout mRlTopview;
    /**
     * GridView对象
     */
    //private GridView mWoGridview;
    private DefineGridView mWoGridview;
    /**
     * 图片数组
     */
    private int[] images = {
            R.mipmap.wo_chongzhi_icon,
            R.mipmap.wo_tixian_icon,
            R.mipmap.wo_goumai_icon,
            R.mipmap.wo_jiaoyi_icon,
    };
    /**
     * 文本数组
     */
    private String[] texts = {
            "充值","提现","我的购买","交易记录",
    };
    /**
     * HttpUtilsHelper对象
     */
    private HttpUtilsHelper httpUtilsHelper;
    /**
     *  常量
     */
    private  final String RETURN_CODE = "return_code";
    final String requestUrl = Constants.WEBURL + "app/getBalance";
    private String balance = "0.00";
    private int resource = R.layout.grid_item;
}
