package com.weibank.com.weibankapp;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.weibank.com.weibankapp.adapter.GoumaiInfoXRecylerAdapter;
import com.weibank.com.weibankapp.beans.GoumaiInfoBean;
import com.weibank.com.weibankapp.beans.TradeRecordBean;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.constants.UserOperationParams;
import com.weibank.com.weibankapp.utils.HttpUtilsHelper;
import com.weibank.com.weibankapp.utils.NormalMethods;
import com.weibank.com.weibankapp.utils.XRecylerViewHelper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;

public class WodegoumaiActivity extends UEBaseCompatActivity
                                 implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wodegoumai);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        /**
         *  调用初始化对象方法
         */
        assignViews();
//        intent = this.getIntent();
//        boolean flag = intent.getBooleanExtra("abcd",true);

        Map<String,Integer> params = new HashMap<>();
        params.put(PAGE,1);
        params.put(PAGESIZE,5);
        params.put(TYPE, 0);

        ConcurrentHashMap<String,Integer> values = new ConcurrentHashMap<>();
        values.put(PAGE,1);
        values.put(PAGESIZE,5);
        values.put(TYPE, 0);

        new GetGouMainRecordThread(Constants.TOUZHI_RECORD_URL,values).start();

        xRecylerViewHelper.handleXRecylerViewEvent(this, mRecyclerview,
                                                     new XRecyclerView.LoadingListener(){
            @Override
            public void onRefresh(){
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){

                        mRecyclerview.refreshComplete();
                    }
                },2000);
            }
            @Override
            public void onLoadMore(){

                mRecyclerview.loadMoreComplete();
            }
        });
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerview.setLayoutManager(manager);
//
//        mRecyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        mRecyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
//        mRecyclerview.setArrowImageView(R.mipmap.iconfont_downgrey);
//
//        mRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener(){
//            @Override
//            public void onRefresh(){
//
//
//            }
//            @Override
//            public void onLoadMore(){
//
//
//            }
//        });
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            //返回上一页面
            case R.id.rl_topview:
                WodegoumaiActivity.this.finish();
                break;
            //跳转到购买历史界面
            case R.id.goumai_text:
                NormalMethods.exitAndSwicthActivity(WodegoumaiActivity.this,
                                                    GoumaiHistoryActivity.class,false);
                break;
        }
    }
    @Override
    protected void onPause(){

        super.onPause();
        mCircle.progressiveStop();
    }
    @Override
    protected void onResume(){

        super.onResume();
        mCircle.progressiveStop();
    }
    @Override
    protected void onStop(){

        super.onStop();
        mCircle.progressiveStop();
    }
    /**
     * 内部类---GetGouMainRecordThread
     */
    class GetGouMainRecordThread extends Thread{
        /**
         * String对象
         */
        protected String requestUrl;
        /**
         * Map对象
         */
        protected ConcurrentHashMap<String,Integer> params;
        /**
         *  boolean值对象
         */
        protected boolean flag;
        public GetGouMainRecordThread(String requestUrl,ConcurrentHashMap<String,Integer> params){

            this.requestUrl = requestUrl;
            this.params = params;
            mCircle.setIndeterminate(true);
            ((CircularProgressDrawable)mCircle.getIndeterminateDrawable()).start();
            Log.e("myerror", "abc");
        }
        @Override
        public void run(){
            super.run();
            if(NormalMethods.isNetWorkConnected(WodegoumaiActivity.this)){

                httpUtilsHelper = HttpUtilsHelper.getInstance();
                httpUtilsHelper.post(requestUrl, params, new RequestCallBack<String>(){
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo){

                        JSONObject object = JSON.parseObject(responseInfo.result);
                        String result = object.getString(Constants.RETURN_CODE);
                        if(result.equals(UserOperationParams.BACK_RETURN_CODE)){

                           JSONArray arrays = object.getJSONArray(Constants.DATA);
                            for(int i = 0; i < arrays.size(); i++){

                                JSONObject item = arrays.getJSONObject(i);
                                GoumaiInfoBean itemBean = new GoumaiInfoBean();

                                String mRate = item.getString("rate");
                                if(mRate.contains(".")){

                                    String[] rates = mRate.split(".");
                                    itemBean.setmRate1(rates[0]);
                                    itemBean.setmRate2("."+rates[1] + "%");
                                }else{

                                    itemBean.setmRate1(mRate);
                                    itemBean.setmRate2(".00%");
                                }

                                itemBean.setmProject(item.getString("productName"));
                                itemBean.setmStartRateMoney(item.getString("minAmount"));
                                itemBean.setmMonths(item.getString("repayMonth"));
                                itemBean.setmAmount(item.getString("amount"));

                                infoBeans.add(itemBean);
                            }
                            handler.sendEmptyMessage(UserOperationParams.GETGOUMAIRECORD_SUCCESS);
                        }else{

                            NormalMethods.toastShowContent(WodegoumaiActivity.this, "获取数据失败.");
//                            Toast.makeText(WodegoumaiActivity.this, "获取数据失败.",
//                                           Toast.LENGTH_SHORT).show();
                            handler.sendEmptyMessage(UserOperationParams.GETGOUMAIRECORD_FAILED);
                        }
                    }
                    @Override
                    public void onFailure(HttpException e, String s){

                        mCircle.progressiveStop();
                        mRecyclerview.setVisibility(View.VISIBLE);

                        NormalMethods.toastShowContent(WodegoumaiActivity.this, "获取数据失败.");
//                        Toast.makeText(WodegoumaiActivity.this, "获取数据失败.",
//                                       Toast.LENGTH_SHORT).show();
                        Log.e("myerror", s);
                    }
                });
            }else{

                NormalMethods.exitAndSwicthActivity(WodegoumaiActivity.this);
                NormalMethods.toastShowContent(WodegoumaiActivity.this, "请检查您的网络.");
//                Toast.makeText(WodegoumaiActivity.this, "请检查您的网络.",
//                               Toast.LENGTH_SHORT).show();
            }
        }
    }
    /**
     *  Handler对象
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){

            super.handleMessage(msg);
            if(msg.what == UserOperationParams.GETGOUMAIRECORD_SUCCESS){

                mCircle.progressiveStop();
                mCircle.setVisibility(View.GONE);
                mRecyclerview.setVisibility(View.VISIBLE);

                adapter = new GoumaiInfoXRecylerAdapter(infoBeans);
                adapter.notifyDataSetChanged();
                mRecyclerview.setAdapter(adapter);
            }else if(msg.what == UserOperationParams.GETGOUMAIRECORD_FAILED){

                mCircle.progressiveStop();
                mCircle.setVisibility(View.GONE);
                mRecyclerview.setVisibility(View.VISIBLE);
            }
        }
    };
    /**
     *  初始化对象方法
     */
    private void assignViews(){
        /**
         * TextView对象
         */
        mTopviewText = (TextView) findViewById(R.id.topview_text);
//        mDownNomoredata = (TextView) findViewById(R.id.down_nomoredata);
//        mDownNomoredata.setVisibility(View.GONE);
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
        /**
         * ListView对象
         */
//        mGoumaiList = (ListView) findViewById(R.id.goumai_list);
        /**
         * LinearLayout对象
         */
        mGoumaiText = (LinearLayout) findViewById(R.id.goumai_text);
        mGoumaiText.setOnClickListener(this);
        /**
         * RelativeLayout对象
         */
//        mRlNomoredata = (RelativeLayout) findViewById(R.id.rl_nomoredata);
        /**
         * XRecyclerView对象
         */
        mRecyclerview = (XRecyclerView) findViewById(R.id.recyclerview);
        /**
         * CircularProgressBar对象
         */
        mCircle = (CircularProgressBar)findViewById(R.id.circle);
        xRecylerViewHelper = XRecylerViewHelper.getInstance();
    }
    /**
     * TextView对象
     */
    private TextView mTopviewText;
    private TextView mDownNomoredata;
    /**
     * RelativeLayout对象
     */
    private RelativeLayout mRlTopview;
    private RelativeLayout mRlNomoredata;
    /**
     *  Intent对象
     */
    private Intent intent;
    /**
     *  CircularProgressBar对象
     */
    private CircularProgressBar mCircle;
    /**
     * ListView对象
     */
    private ListView mGoumaiList;
    /**
     * LinearLayout对象
     */
    private LinearLayout mGoumaiText;
    /**
     * XRecyclerView对象
     */
    private XRecyclerView mRecyclerview;
    /**
     * HttpUtilsHelper对象
     */
    private HttpUtilsHelper httpUtilsHelper;
    /**
     *  XRecylerViewHelper对象
     */
    private XRecylerViewHelper xRecylerViewHelper;
    /**
     *  GoumaiInfoXRecylerAdapter对象
     */
    private GoumaiInfoXRecylerAdapter adapter;
    /**
     *  存储GoumaiInfoBean对象列表
     */
    private LinkedList<GoumaiInfoBean> infoBeans = new LinkedList<>();
    /**
     * 常量
     */
    private  final String PAGE = "page";
    private  final String PAGESIZE = "pageSize";
    private  final String TYPE = "type";
}
