package com.weibank.com.weibankapp;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
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
import com.weibank.com.weibankapp.adapter.TradeRecordXRecylerViewAdapter;
import com.weibank.com.weibankapp.beans.TradeRecordBean;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.constants.UserOperationParams;
import com.weibank.com.weibankapp.listener.RecylerViewItemClickListener;
import com.weibank.com.weibankapp.utils.HttpUtilsHelper;
import com.weibank.com.weibankapp.utils.NormalMethods;
import com.weibank.com.weibankapp.utils.XRecylerViewHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;

public class TradeRecordActivity extends UEBaseCompatActivity
                                  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_record);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        /**
         * 调用初始化对象方法
         */
        assignViews();

        new GetTradeRecordThread(requestUrl,produceMaps(pagecode),
                                 isPullToRefresh,showLoadingDialog).start();

        xRecylerViewHelper.handleXRecylerViewEvent(this, mRecyclerview,
                                                     new XRecyclerView.LoadingListener(){
            @Override
            public void onRefresh(){
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        if(pagecode <= 20){

                            isPullToRefresh = true;
                            showLoadingDialog = false;
                            pagecode += 1;
                            new GetTradeRecordThread(requestUrl,produceMaps(pagecode),
                                                     isPullToRefresh,showLoadingDialog).start();
                            mRecyclerview.refreshComplete();
                        }else{

                            mRecyclerview.refreshComplete();
                        }
                    }
                },2000);
            }
            @Override
            public void onLoadMore(){
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        if(pagecode >= 1){

                            isPullToRefresh = false;
                            showLoadingDialog = false;
                            pagecode -= 1;
                            new GetTradeRecordThread(requestUrl,produceMaps(pagecode),
                                                     isPullToRefresh,showLoadingDialog).start();
                            mRecyclerview.loadMoreComplete();
                        }else{

                            mRecyclerview.loadMoreComplete();
                        }
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
//                new Handler().postDelayed(new Runnable(){
//                    @Override
//                    public void run(){
//                        if(pagecode <= 20){
//
//                            isPullToRefresh = true;
//                            pagecode += 1;
//                            new GetTradeRecordThread(requestUrl,produceMap(pagecode),isPullToRefresh).start();
//                            mRecyclerview.refreshComplete();
//                        }else{
//
//                            mRecyclerview.refreshComplete();
//                        }
//                    }
//                },2000);
//            }
//            @Override
//            public void onLoadMore(){
//                new Handler().postDelayed(new Runnable(){
//                    @Override
//                    public void run(){
//                        if(pagecode >= 1){
//
//                            isPullToRefresh = false;
//                            pagecode -= 1;
//                            new GetTradeRecordThread(requestUrl,produceMap(pagecode),isPullToRefresh).start();
//                            mRecyclerview.loadMoreComplete();
//                        }else{
//
//                            mRecyclerview.loadMoreComplete();
//                        }
//                    }
//                },2000);
//            }
//        });


//        if(flag && trBeans != null){
//
//            adapter = new TradeRecordXRecylerViewAdapter(trBeans);
//            mRecyclerview.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
//            adapter.onItemClickListener(new RecylerViewItemClickListener(){
//                @Override
//                public void onItemClick(View view, int position){
//
//
//                }
//            });
//        }
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            //返回上一页面
            case R.id.rl_topview:
                TradeRecordActivity.this.finish();
                break;
        }
    }
    @Override
    protected void onStop(){

        super.onStop();
        mCircle.progressiveStop();
    }
    @Override
    protected void onDestroy(){

        super.onDestroy();
//        trBeans.clear();
        mCircle.progressiveStop();
    }
    /**
     * 内部类---GetTradeRecordThread
     */
    class GetTradeRecordThread extends Thread{
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
        protected boolean showLoadingDialog;
        public GetTradeRecordThread(String requestUrl,ConcurrentHashMap<String,Integer> params,
                                    boolean flag,boolean showLoadingDialog){

            this.requestUrl = requestUrl;
            this.params = params;
            this.flag = flag;

            if(showLoadingDialog){

                mCircle.setIndeterminate(true);
                ((CircularProgressDrawable)mCircle.getIndeterminateDrawable()).start();
            }
        }
        @Override
        public void run(){
            super.run();
            if(NormalMethods.isNetWorkConnected(TradeRecordActivity.this)){

                httpUtilsHelper = HttpUtilsHelper.getInstance();
                httpUtilsHelper.post(requestUrl, params, new RequestCallBack<String>(){
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo){

                        JSONObject object = JSON.parseObject(responseInfo.result);
                        String result = object.getString(RETURN_CODE);
                        if(result.equals(UserOperationParams.BACK_RETURN_CODE)){

                            JSONArray arrays = object.getJSONArray(DATA);
                            for(int i = 0; i < arrays.size(); i++){

                                JSONObject item = arrays.getJSONObject(i);

                                TradeRecordBean bean = new TradeRecordBean();
                                bean.setTransAmount(item.getString("transAmount"));
                                bean.setTransDate(item.getString("transDate"));
                                bean.setTransStatusName(item.getString("transStatusName"));
                                bean.setTransTypeName(item.getString("transTypeName"));

                                trBeans.add(bean);
                            }
//                            sumBeans.add(trBeans);
                            if(isPullToRefresh){

                                handler.sendEmptyMessage(UserOperationParams.TRADERECORD_PULLTOREFRESH);
                            }else{

                                handler.sendEmptyMessage(UserOperationParams.TRADERECORD_UPTOLOADING);
                            }
                        }else{

                            NormalMethods.toastShowContent(TradeRecordActivity.this, "获取数据失败.");
//                            Toast.makeText(TradeRecordActivity.this, "获取数据失败.",
//                                           Toast.LENGTH_SHORT).show();
                            handler.sendEmptyMessage(UserOperationParams.TRADERECORDFAILED);
                        }
                    }
                    @Override
                    public void onFailure(HttpException e, String s){

                        mCircle.progressiveStop();
                        mRecyclerview.setVisibility(View.VISIBLE);

                        NormalMethods.toastShowContent(TradeRecordActivity.this, "获取数据失败.");
//                        Toast.makeText(TradeRecordActivity.this, "获取数据失败.",
//                                       Toast.LENGTH_SHORT).show();
                        Log.e("myerror",s);
                    }
                });
            }else{

                NormalMethods.toastShowContent(TradeRecordActivity.this, "请检查您的网络.");
                NormalMethods.exitAndSwicthActivity(TradeRecordActivity.this);
//                Toast.makeText(TradeRecordActivity.this, "请检查您的网络.",
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
            if(msg.what == UserOperationParams.TRADERECORD_PULLTOREFRESH){

                if(showLoadingDialog){

                    mCircle.progressiveStop();
                }else{

                    mCircle.setVisibility(View.GONE);
                }
                mRecyclerview.setVisibility(View.VISIBLE);

                pullToRefreshBeans.clear();

//                if(trBeans.size() > 25){
//
//                    for(int i = 0; i < 5; i++){
//
//                        TradeRecordBean bean = trBeans.get(i);
//                        upToLoadBeans.add(bean);
//                    }
//                }
                for(int i = trBeans.size() - 1; i >= 0 ; i--){

                    TradeRecordBean bean = trBeans.get(i);
                    pullToRefreshBeans.add(bean);
                }
//                Collections.reverse(pullToRefreshBeans);
                adapter = new TradeRecordXRecylerViewAdapter(pullToRefreshBeans);
                mRecyclerview.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                /**
                 *  下拉刷新获取焦点
                 */
                mRecyclerview.setFocusable(true);
                mRecyclerview.setFocusableInTouchMode(true);
                mRecyclerview.requestFocus();
                mRecyclerview.requestFocusFromTouch();
            }else if(msg.what == UserOperationParams.TRADERECORD_UPTOLOADING){

                if(showLoadingDialog){

                    mCircle.progressiveStop();
                }else{

                    mCircle.setVisibility(View.GONE);
                }
                mRecyclerview.setVisibility(View.VISIBLE);

                upToLoadingBeans.clear();
                for(int i = trBeans.size() - 1; i >= 0 ; i--){

                    TradeRecordBean bean = trBeans.get(i);
                    upToLoadingBeans.add(bean);
                }
                Collections.reverse(upToLoadingBeans);
                adapter = new TradeRecordXRecylerViewAdapter(upToLoadingBeans);
                mRecyclerview.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                /**
                 *  上拉加载获取焦点
                 */
                mRecyclerview.setFocusable(true);
                mRecyclerview.setFocusableInTouchMode(true);
                mRecyclerview.requestFocus();
                mRecyclerview.requestFocusFromTouch();
            }else if(msg.what == UserOperationParams.TRADERECORDFAILED){

                if(showLoadingDialog){

                    mCircle.progressiveStop();
                }else{

                    mCircle.setVisibility(View.GONE);
                }
                mRecyclerview.setVisibility(View.VISIBLE);
            }
        }
    };
    /**
     *  返回Map对象方法
     */
    private Map<String,Integer> produceMap(int i){

        Map<String,Integer> params = new HashMap<>();
        params.put(PAGE,i);
        params.put(PAGESIZE, PAGENUMBER);
        return params;
    }
    /**
     *  返回ConcurrentHashMap对象
     */
    private ConcurrentHashMap<String,Integer> produceMaps(int i){

        ConcurrentHashMap<String,Integer> params = new ConcurrentHashMap<>();
        params.put(PAGE,i);
        params.put(PAGESIZE, PAGENUMBER);
        return params;
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){
        /**
         * TextView对象
         */
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("交易记录");
//        mDownNomoredata = (TextView) findViewById(R.id.down_nomoredata);
//        mDownNomoredata.setVisibility(View.GONE);
        /**
         * ListView对象
         */
//        mGoumaiList = (ListView) findViewById(R.id.goumai_list);
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
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
    /**
     * XRecyclerView对象
     */
    private XRecyclerView mRecyclerview;
    /**
     * TradeRecordXRecylerViewAdapter对象
     */
    private TradeRecordXRecylerViewAdapter adapter;
    /**
     *  CircularProgressBar对象
     */
    private CircularProgressBar mCircle;
    /**
     * HttpUtilsHelper对象
     */
    private HttpUtilsHelper httpUtilsHelper;
    /**
     *
     */
    private XRecylerViewHelper xRecylerViewHelper;
    /**
     *  常量
     */
    private  final String RETURN_CODE = "return_code";
    private  final String DATA = "data";
    private  final String PAGE = "page";
    private  final String PAGESIZE = "pageSize";
    private  final int PAGENUMBER = 5;
    private final String requestUrl = Constants.WEBURL + "app/getTransList";
    /**
     * 页码
     */
    private static int pagecode = 5;
    /**
     * 存储TradeRecordBean对象
     */
//    private static Stack<Stack<TradeRecordBean>> sumBeans = new Stack<>();
    private static ArrayList<TradeRecordBean> trBeans = new ArrayList<>();
    private static ArrayList<TradeRecordBean> pullToRefreshBeans = new ArrayList<>();
    private static ArrayList<TradeRecordBean> upToLoadingBeans = new ArrayList<>();
//    private static Stack<TradeRecordBean> trBeans = new Stack<>();
//    private static Stack<TradeRecordBean> pullToRefreshBeans = new Stack<>();
//    private static Stack<TradeRecordBean> upToLoadBeans = new Stack<>();
    /**
     *  标志位----判断是下拉刷新还是上拉加载
     */
    private boolean isPullToRefresh = true;
    private boolean showLoadingDialog = true;
//    ArrayList<String> arrays = new ArrayList<>();
}
