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

public class GoumaiHistoryActivity extends UEBaseCompatActivity
                                    implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goumai_history);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        /**
         *  调用初始化对象方法
         */
        assignViews();
        Map<String,Integer> params = new HashMap<>();
        params.put(PAGE,1);
        params.put(PAGESIZE,5);
        params.put(TYPE, 1);

        ConcurrentHashMap<String,Integer> values = new ConcurrentHashMap<>();
        values.put(PAGE,1);
        values.put(PAGESIZE,5);
        values.put(TYPE, 1);

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
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){

                        mRecyclerview.loadMoreComplete();
                    }
                },2000);
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
        switch(v.getId()){
            case R.id.rl_topview:

//                Intent intent = new Intent(this,WodegoumaiActivity.class);
//                intent.putExtra("abcd",false);
//                startActivity(intent);
//                mCircle.progressiveStop();
                GoumaiHistoryActivity.this.finish();
                break;
        }
    }
    @Override
    protected void onStop(){

        super.onStop();
        mCircle.progressiveStop();
        mCircle.setVisibility(View.GONE);
    }
    @Override
    protected void onDestroy(){

        super.onDestroy();
        mCircle.progressiveStop();
        mCircle.setVisibility(View.GONE);
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
        public GetGouMainRecordThread(String requestUrl,ConcurrentHashMap<String,Integer> params){

            this.requestUrl = requestUrl;
            this.params = params;
            mCircle.setIndeterminate(true);
            ((CircularProgressDrawable)mCircle.getIndeterminateDrawable()).start();
        }
        @Override
        public void run(){
            super.run();
            if(NormalMethods.isNetWorkConnected(GoumaiHistoryActivity.this)){

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

                            NormalMethods.toastShowContent(GoumaiHistoryActivity.this,
                                                           "获取数据失败.");
//                            Toast.makeText(GoumaiHistoryActivity.this, "获取数据失败.",
//                                           Toast.LENGTH_SHORT).show();
                            handler.sendEmptyMessage(UserOperationParams.GETGOUMAIRECORD_FAILED);
                        }
                    }
                    @Override
                    public void onFailure(HttpException e, String s){

                        mCircle.progressiveStop();
                        mRecyclerview.setVisibility(View.VISIBLE);
                        NormalMethods.toastShowContent(GoumaiHistoryActivity.this,
                                                       "获取数据失败.");
//                        Toast.makeText(GoumaiHistoryActivity.this, "获取数据失败.",
//                                       Toast.LENGTH_SHORT).show();
                        Log.e("myerror", s);
                    }
                });
            }else{

                NormalMethods.exitAndSwicthActivity(GoumaiHistoryActivity.this);
                NormalMethods.toastShowContent(GoumaiHistoryActivity.this,
                                               "请检查您的网络.");
//                Toast.makeText(GoumaiHistoryActivity.this, "请检查您的网络.",
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
                mRecyclerview.setVisibility(View.VISIBLE);

                adapter = new GoumaiInfoXRecylerAdapter(infoBeans);
                adapter.notifyDataSetChanged();
                mRecyclerview.setAdapter(adapter);
            }else if(msg.what == UserOperationParams.GETGOUMAIRECORD_FAILED){

                mCircle.progressiveStop();
                mRecyclerview.setVisibility(View.VISIBLE);
            }
        }
    };
    /**
     * 初始化对象方法
     */
    private void assignViews(){
        /**
         * TextView对象
         */
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("购买历史");
//        mDownNomoredata = (TextView) findViewById(R.id.down_nomoredata);
//        mDownNomoredata.setVisibility(View.GONE);
        /**
         * ListView对象
         */
//        mGoumaiList = (ListView) findViewById(R.id.goumai_list);
        /**
         *  RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
//        mRltopNomoredata = (RelativeLayout) findViewById(R.id.rltop_nomoredata);
        /**
         *  XRecyclerView对象
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
    private TextView mDownNomoredata;
    private TextView mTopviewText;
    /**
     * ListView对象
     */
    private ListView mGoumaiList;
    /**
     * RelativeLayout对象
     */
    private RelativeLayout mRlTopview;
    private RelativeLayout mRltopNomoredata;
    /**
     *  XRecyclerView对象
     */
    private XRecyclerView mRecyclerview;
    /**
     *  CircularProgressBar对象
     */
    private CircularProgressBar mCircle;
    /**
     * HttpUtilsHelper对象
     */
    private HttpUtilsHelper httpUtilsHelper;
    /**
     * XRecylerViewHelper对象
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
