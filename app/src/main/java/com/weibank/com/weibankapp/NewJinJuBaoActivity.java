package com.weibank.com.weibankapp;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
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
import com.weibank.com.weibankapp.adapter.JinJuBaoRecylerViewAdapter;
import com.weibank.com.weibankapp.adapter.YinJuBaoRecylerViewAdapter;
import com.weibank.com.weibankapp.beans.ProjectInfo;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.constants.UserOperationParams;
import com.weibank.com.weibankapp.listener.RecylerViewItemClickListener;
import com.weibank.com.weibankapp.utils.HttpUtilsHelper;
import com.weibank.com.weibankapp.utils.NormalMethods;
import com.weibank.com.weibankapp.utils.XRecylerViewHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;

public class NewJinJuBaoActivity extends UEBaseCompatActivity
                                  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_jin_ju_bao);
        /**
         *  调用初始化对象方法
         */
        assignViews();

        Map<String,Integer> params = new HashMap<>();
        params.put(PAGE, 1);
        params.put(PAGESIZE, 5);
        params.put(REPAYTYPEID, 3);

        ConcurrentHashMap<String,Integer> values = new ConcurrentHashMap<>();
        values.put(PAGE,1);
        values.put(PAGESIZE, 5);
        values.put(REPAYTYPEID, 3);

        new JinJuBaoDataThread(requestUrl,values).start();

        xRecylerViewHelper.handleXRecylerViewEvent(this, mRecyclerview,
                                                     new XRecyclerView.LoadingListener(){
            @Override
            public void onRefresh(){
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){

                        mRecyclerview.refreshComplete();

                    }
                }, 1000);
            }
            @Override
            public void onLoadMore(){
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){

                        mRecyclerview.loadMoreComplete();
                    }
                }, 1000);
            }
        });
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerview.setLayoutManager(layoutManager);
//
//        mRecyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        mRecyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallGridPulse);
//        //mRecyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
//        mRecyclerview.setArrowImageView(R.mipmap.iconfont_downgrey);
//        mRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener(){
//            @Override
//            public void onRefresh(){
//                new Handler().postDelayed(new Runnable(){
//                    @Override
//                    public void run(){
//
//                        mRecyclerview.refreshComplete();
//
//                    }
//                }, 1000);
//            }
//            @Override
//            public void onLoadMore(){
//                new Handler().postDelayed(new Runnable(){
//                    @Override
//                    public void run(){
//
//                        mRecyclerview.loadMoreComplete();
//                    }
//                }, 1000);
//            }
//        });

//        adapter = new JinJuBaoRecylerViewAdapter(projectInfos());
//        adapter.onItemClickListener(new RecylerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position){
//                switch(position){
//                    case 1:
//
//                        Toast.makeText(NewJinJuBaoActivity.this, "abc", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 2:
//
//                        Toast.makeText(NewJinJuBaoActivity.this, "def", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        });
//        mRecyclerview.setAdapter(adapter);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.rl_topview:

                NewJinJuBaoActivity.this.finish();
                break;
        }
    }
    @Override
    protected void onDestroy(){

        super.onDestroy();
        linkedLists.clear();
    }
    /**
     *  内部类 ---JinJuBaoDataThread
     */
    class JinJuBaoDataThread extends Thread{
        /**
         *  String对象
         */
        protected String requestUrl;
        /**
         *  Map对象
         */
        protected ConcurrentHashMap<String,Integer> params;
        public JinJuBaoDataThread(String requestUrl,ConcurrentHashMap<String,Integer> params){

            this.requestUrl = requestUrl;
            this.params = params;
            mCircle.setIndeterminate(true);
            ((CircularProgressDrawable)mCircle.getIndeterminateDrawable()).start();
        }
        @Override
        public void run(){
            super.run();
            if(NormalMethods.isNetWorkConnected(NewJinJuBaoActivity.this)){
                httpUtilsHelper = HttpUtilsHelper.getInstance();
                httpUtilsHelper.post(requestUrl, params, new RequestCallBack<String>(){
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo){
                        JSONObject object = JSON.parseObject(responseInfo.result);
                        if(object.getString(RETURN_CODE).equals
                                (UserOperationParams.BACK_RETURN_CODE)){

                            JSONArray array = object.getJSONArray(DATA);
                            for(int i = 0; i < array.size(); i++){

                                JSONObject item = array.getJSONObject(i);
                                ProjectInfo info = new ProjectInfo();
                                info.setmRate(item.getInteger("expectedAnnualRate") + "%");
                                info.setmProjectId(item.getString("productName"));
                                info.setmStartRateDate(item.getString("minAmount"));
                                info.setmProjectDesc1(item.getString("statusName"));
                                linkedLists.add(info);
                            }
                            handler.sendEmptyMessage(UserOperationParams.GETPROJECTINFO_SUCCESS);
                        }else{

                            mCircle.progressiveStop();
                            mMainLayout.setVisibility(View.VISIBLE);

                            NormalMethods.toastShowContent(NewJinJuBaoActivity.this,
                                                           "服务器出现异常.");
//                            Toast.makeText(NewJinJuBaoActivity.this, "服务器出现异常.",
//                                           Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(HttpException e, String s){

                        mCircle.progressiveStop();
                        mMainLayout.setVisibility(View.VISIBLE);
                        NormalMethods.toastShowContent(NewJinJuBaoActivity.this,
                                                       "服务器出现异常.");
//                        Toast.makeText(NewJinJuBaoActivity.this, "服务器出现异常.",
//                                       Toast.LENGTH_SHORT).show();
                    }
                });
            }else{

                NormalMethods.toastShowContent(NewJinJuBaoActivity.this,
                                              "请检查您的网络.");
                NormalMethods.exitAndSwicthActivity(NewJinJuBaoActivity.this);
//                Toast.makeText(NewJinJuBaoActivity.this, "请检查您的网络.",
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
            if(msg.what == UserOperationParams.GETPROJECTINFO_SUCCESS){

                mCircle.progressiveStop();
                mMainLayout.setVisibility(View.VISIBLE);

                adapter = new JinJuBaoRecylerViewAdapter(linkedLists);
                mRecyclerview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
    };
    /**
     * 返回ProjectInfo对象方法
     */
    private List<ProjectInfo> projectInfos(){

        List<ProjectInfo>  itemInfos = new ArrayList<>();
        ProjectInfo projectInfoa = new ProjectInfo();
        projectInfoa.setmRate("10%");
        projectInfoa.setmHeadProjectId("[10号]");
        projectInfoa.setmProjectId("孔雀养殖2期");
        projectInfoa.setmStartRateDate("计日起息 1.00元起送");
        projectInfoa.setmBgImage(R.mipmap.zhuangtai_bg);
        projectInfoa.setmProjectDesc1("认筹中");
        projectInfoa.setmProjectDesc2("热");

        itemInfos.add(projectInfoa);

        ProjectInfo projectInfob = new ProjectInfo();
        projectInfob.setmRate("9.8%");
        projectInfob.setmHeadProjectId("[8号]");
        projectInfob.setmProjectId("孔雀养殖11期");
        projectInfob.setmStartRateDate("计日起息 1.00元起送");
        projectInfob.setmBgImage(R.mipmap.zhuangtai_bg);
        projectInfob.setmProjectDesc1("认筹中");
        projectInfob.setmProjectDesc2("热");
        itemInfos.add(projectInfob);

        return itemInfos;
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){

        mTvupNomoredata = (TextView) findViewById(R.id.tvup_nomoredata);
        mTvdownNomoredata = (TextView) findViewById(R.id.tvdown_nomoredata);
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("金聚宝");
        mRecyclerview = (XRecyclerView) findViewById(R.id.recyclerview);
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
        /**
         *  LinearLayout对象
         */
        mMainLayout = (LinearLayout)findViewById(R.id.mainLayout);
        /**
         * CircularProgressBar对象
         */
        mCircle = (CircularProgressBar)findViewById(R.id.circle);
        xRecylerViewHelper = XRecylerViewHelper.getInstance();
    }
    /**
     * TextView对象
     */
    private TextView mTvupNomoredata;
    private TextView mTvdownNomoredata;
    private TextView mTopviewText;
    /***
     * XRecyclerView对象
     */
    private XRecyclerView mRecyclerview;
    /**
     * JinJuBaoRecylerViewAdapter对象
     */
    private JinJuBaoRecylerViewAdapter adapter;
    /**
     * RelativeLayout对象
     */
    private RelativeLayout mRlTopview;
    /**
     * LinearLayout对象
     */
    private LinearLayout mMainLayout;
    /**
     *  CircularProgressBar对象
     */
    private CircularProgressBar mCircle;
    /**
     * HttpUtilsHelper对象
     */
    private HttpUtilsHelper httpUtilsHelper;
    /**
     *  XRecylerViewHelper对象
     */
    private XRecylerViewHelper xRecylerViewHelper;
    /**
     *  常量
     */
    private  final String RETURN_CODE = "return_code";
    private  final String requestUrl = Constants.WEBURL + "app/queryProductList";
    private  final String PAGE = "page";
    private  final String PAGESIZE = "pageSize";
    private  final String REPAYTYPEID = "repayTypeId";
    private  final String DATA = "data";
    /**
     * 存储ProjectInfo列表
     */
    private LinkedList<ProjectInfo> linkedLists = new LinkedList<>();
}
