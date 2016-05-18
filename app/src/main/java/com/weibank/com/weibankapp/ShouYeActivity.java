package com.weibank.com.weibankapp;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.phone.bean.ADInfo;
import com.android.phone.lib.CycleViewPager;
import com.android.phone.utils.ViewFactory;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.weibank.com.weibankapp.adapter.JinJuBaoAdapter;
import com.weibank.com.weibankapp.adapter.ShouYeListViewAdapter;
import com.weibank.com.weibankapp.adapter.ShouYeXRecyleViewAdapter;
import com.weibank.com.weibankapp.beans.MainProjectBean;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.constants.UserOperationParams;
import com.weibank.com.weibankapp.listener.RecylerViewItemClickListener;
import com.weibank.com.weibankapp.utils.HttpUtilsHelper;
import com.weibank.com.weibankapp.utils.NormalMethods;
import com.weibank.com.weibankapp.utils.XRecylerViewHelper;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;

public class ShouYeActivity extends BaseActivity
                             implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_ye);
        /**
         * 调用初始化方法
         */
        assignViews();
        /**
         *  调用配置ImageLoader方法
         */
        configImageLoader();
        /**
         *  初始化轮播图方法
         */
        initialize();
//        mShouyeListview.setAdapter(new ShouYeListViewAdapter(this, imgbgs, texts));
//        mShouyeListview.setAdapter(new ShouYeListViewAdapter(this,projectBeans()));
//        mShouyeListview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                     int position, long id){
//                switch(position){
//                    case 0:
//                        NormalMethods.exitAndSwicthActivity(ShouYeActivity.this,
//                                                            NewYinJuBaoActivity.class, false);
//                        break;
//                    case 1:
//                        NormalMethods.exitAndSwicthActivity(ShouYeActivity.this,
//                                                            NewJinJuBaoActivity.class, false);
//                        break;
//                }
//            }
//        });

        xRecylerViewHelper.handleXRecylerViewEvent(this, mShouyeListview,
                                                     new XRecyclerView.LoadingListener(){
            @Override
            public void onRefresh(){
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){

                        mShouyeListview.refreshComplete();
                    }
                }, 2000);
            }
            @Override
            public void onLoadMore(){
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){

                        mShouyeListview.loadMoreComplete();
                    }
                }, 1000);
            }
        });
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        mShouyeListview.setLayoutManager(manager);
//
//        mShouyeListview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        mShouyeListview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
//        mShouyeListview.setArrowImageView(R.mipmap.iconfont_downgrey);
//        mShouyeListview.setLoadingListener(new XRecyclerView.LoadingListener() {
//            /**
//             * 下拉刷新
//             */
//            @Override
//            public void onRefresh(){
//                new Handler().postDelayed(new Runnable(){
//                    @Override
//                    public void run() {
//
////                        final String requestUrl = Constants.WEBURL + "app/getHomePageInfo";
////                        new GetProjectDataThread(requestUrl).start();
////                        mShouyeListview.refreshComplete();
//                    }
//                }, 2000);
////                final String requestUrl = Constants.WEBURL + "app/getHomePageInfo";
////                new GetProjectDataThread(requestUrl).start();
////                mShouyeListview.refreshComplete();
//            }
//
//            /**
//             *  上拉加载
//             */
//            @Override
//            public void onLoadMore(){
//                new Handler().postDelayed(new Runnable(){
//                    @Override
//                    public void run(){
//
//                        mShouyeListview.loadMoreComplete();
//                    }
//                }, 1000);
//            }
//        });

        //final String requestUrl = Constants.WEBURL + "app/getHomePageInfo";
        new GetProjectDataThread(Constants.GET_HOME_PAGE_INFO_URL).start();
        if(beans == null){

            beans = projectBeans();
        }
        adapter = new ShouYeXRecyleViewAdapter(beans);
        adapter.onItemClickListener(new RecylerViewItemClickListener(){
            @Override
            public void onItemClick(View view, int position){
                switch (position){
                    case 1:

                        NormalMethods.exitAndSwicthActivity(ShouYeActivity.this,
                                                            NewYinJuBaoActivity.class, false);
                        break;
                    case 2:

                        NormalMethods.exitAndSwicthActivity(ShouYeActivity.this,
                                                            NewJinJuBaoActivity.class, false);
                        break;
                }
            }
        });

        mShouyeListview.setAdapter(adapter);
    }
    @Override
    protected void onResume(){

        super.onResume();
    }
    @Override
    protected void onStop(){

        super.onStop();
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            //case R.id.yaoqinglan:
                //跳转到分享页面
                //NormalMethods.exitAndSwicthActivity(ShouYeActivity.this,
                                                   // ShareFriendActivity.class,false);
                //break;
        }
    }
    /**
     * 设置通知栏状态方法
     *
     */
    private void setTranslucentStatus(boolean on){

        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    /**
     * 产生MainProjectBean对象方法
     */
    private ArrayList<MainProjectBean> projectBeans(){

        for(int i = 0; i < mainImgs.length; i++){

            MainProjectBean bean = new MainProjectBean();
            bean.setMainImg(mainImgs[i]);
            bean.setViceImg1(viceImg1s[i]);
            bean.setViceImg2(viceImg2s[i]);
            bean.setViceImg3(viceImg3s[i]);
            bean.setmYearRate(texts[i]);

            beans.add(bean);
        }
        return beans;
    }
    /**
     *  内部类------获取项目数据
     */
    class GetProjectDataThread extends Thread{
        /**
         *  构造器
         */
        public GetProjectDataThread(String requestUrl){

            this.requestUrl = requestUrl;
            mCircle.setIndeterminate(true);
            ((CircularProgressDrawable)mCircle.getIndeterminateDrawable()).start();
        }
        /**
         * Handler对象
         */
//        protected Handler handler;
        /**
         * 请求Url
         */
        protected String requestUrl;
        @Override
        public void run(){

            super.run();
            if(NormalMethods.isNetWorkConnected(ShouYeActivity.this)){

                httpUtilsHelper = HttpUtilsHelper.getInstance();
                httpUtilsHelper.post(requestUrl, new RequestCallBack<String>(){
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo){

                        JSONObject object = JSON.parseObject(responseInfo.result);
                        if(object.get(RETURN_CODE).equals(UserOperationParams.BACK_RETURN_CODE)){

                            JSONArray array = object.getJSONArray("recent_product");
                            for(int i = 0; i < array.size(); i++){

                                JSONObject item = array.getJSONObject(i);
                                arrays.add(item.getString("expected_annual_rate") + "%");
//                                Log.e("myerror",arrays.get(i));
                            }
                            handler.sendEmptyMessage(UserOperationParams.GETPROJECTINFO_SUCCESS);
                        }
                    }
                    @Override
                    public void onFailure(HttpException e, String s){

                        NormalMethods.toastShowContent(ShouYeActivity.this, "获取数据失败.");
//                        Toast.makeText(ShouYeActivity.this, "获取数据失败.",
//                                       Toast.LENGTH_SHORT).show();
                        handler.sendEmptyMessage(UserOperationParams.GETPROJECTINFO_FAILED);
                    }
                });
            }else{

                  NormalMethods.toastShowContent(ShouYeActivity.this, "请检查您的网络.");
                  NormalMethods.exitAndSwicthActivity(ShouYeActivity.this);
//                Toast.makeText(ShouYeActivity.this, "请检查您的网络.",
//                               Toast.LENGTH_SHORT).show();
            }
//            Looper.prepare();
//            handler = new Handler(){
//                @Override
//                public void handleMessage(Message msg){
//
//                    super.handleMessage(msg);
//                    if(msg.what == UserOperationParams.GETPROJECTVALUE){
//
////                        adapter = new ShouYeXRecyleViewAdapter(projectBeans(0x102));
////                        adapter.notifyDataSetChanged();
//                        beans.clear();
//                        for(int i = 0; i < mainImgs.length; i++){
//
//                            MainProjectBean bean = new MainProjectBean();
//                            bean.setMainImg(mainImgs[i]);
//                            bean.setViceImg1(viceImg1s[i]);
//                            bean.setViceImg2(viceImg2s[i]);
//                            bean.setViceImg3(viceImg3s[i]);
//                            bean.setmYearRate(arrays.get(i));
//
//                            beans.add(bean);
//                        }
//                    }
//                }
//            };
//            Looper.loop();
        }
    }
    final private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){

//            super.handleMessage(msg);
            if(msg.what == UserOperationParams.GETPROJECTINFO_SUCCESS){

//                        adapter = new ShouYeXRecyleViewAdapter(projectBeans(0x102));
//                        adapter.notifyDataSetChanged();
                //beans.clear();
                mCircle.progressiveStop();
                mCircle.setVisibility(View.GONE);
                mMainLayout.setVisibility(View.VISIBLE);

                for(int i = 0; i < mainImgs.length; i++){

                    MainProjectBean bean = new MainProjectBean();
                    bean.setMainImg(mainImgs[i]);
                    bean.setViceImg1(viceImg1s[i]);
                    bean.setViceImg2(viceImg2s[i]);
                    bean.setViceImg3(viceImg3s[i]);
                    bean.setmYearRate(arrays.get(i));

                    beans.add(bean);
                }
                //adapter.notifyDataSetChanged();
            }else if(msg.what == UserOperationParams.GETPROJECTINFO_FAILED){

                mCircle.progressiveStop();
                mCircle.setVisibility(View.GONE);
                mMainLayout.setVisibility(View.VISIBLE);
            }
        }
    };
    /**
     *  初始化轮播图方法
     */
    private void initialize(){

        cycleViewPager = (CycleViewPager) getFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);
        for(int i = 0; i < imageUrls.length; i ++){

            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i);
            infos.add(info);
        }
        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(infos.size() - 1).getUrl()));
        for(int i = 0; i < infos.size(); i++){

            views.add(ViewFactory.getImageView(this, infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);

        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }
    /**
     *  ImageCycleViewListener对象
     */
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener =
                            new CycleViewPager.ImageCycleViewListener(){
        @Override
        public void onImageClick(ADInfo info, int position, View imageView){
            if(cycleViewPager.isCycle()){

                position = position - 1;
//                Toast.makeText(ShouYeActivity.this,
//                               "position-->" + info.getContent(), Toast.LENGTH_SHORT).show();
                return;
            }
        }
    };
    /**
     * 配置ImageLoder
     */
    private void configImageLoader(){
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                        // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                                                                      .defaultDisplayImageOptions(options)
                                                                      .threadPriority(Thread.NORM_PRIORITY - 2)
                                                                      .denyCacheImageMultipleSizesInMemory()
                                                                      .discCacheFileNameGenerator(new Md5FileNameGenerator())
                                                                      .tasksProcessingOrder(QueueProcessingType.LIFO)
                                                                      .build();
        ImageLoader.getInstance().init(config);
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){
        /**
         * TextView对象
         */
        mRegiterUsers = (TextView) findViewById(R.id.regiter_users);
        mSuccessInverst = (TextView) findViewById(R.id.success_inverst);
        mNamea = (TextView) findViewById(R.id.namea);
        mNameb = (TextView) findViewById(R.id.nameb);
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        /**
         * ImageView对象
         */
        //mYaoqinglan = (ImageView) findViewById(R.id.yaoqinglan);
        //mYaoqinglan.setOnClickListener(this);
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setVisibility(View.GONE);
        /**
         * LinearLayout对象
         */
        mRightText = (LinearLayout) findViewById(R.id.right_text);
        /**
         * ListView对象
         */
        mShouyeListview = (XRecyclerView) findViewById(R.id.shouye_listview);
        /**
         * SliderLayout对象
         */
        //mSlider = (SliderLayout) findViewById(R.id.slider);
        /**
         * LinearLayout對象
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
    private TextView mRegiterUsers;
    private TextView mSuccessInverst;
    private TextView mNamea;
    private TextView mTopviewText;
    private TextView mNameb;
    /**
     * ImageView对象
     */
    //private ImageView mYaoqinglan;
    private RelativeLayout mRlTopview;
    /**
     * ListView对象
     */
//    private ListView mShouyeListview;
    private XRecyclerView mShouyeListview;
    /**
     * CircularProgressBar对象
     */
    private CircularProgressBar mCircle;
    /**
     * LinearLayout对象
     */
    private LinearLayout mRightText;
    private LinearLayout mMainLayout;
    /**
     * 图片数组
     */
    private int[] imgbgs = {R.mipmap.new_jinjubao_bg,
                              R.mipmap.new_yinjubao_bg};
    /**
     * 文本数组
     */
    private String[] texts = {"35%","15%"};



    /////////////////////////////////////////////////////////////////////
    /**
     * ShouYeXRecyleViewAdapter对象
     */
    private ShouYeXRecyleViewAdapter adapter;
    /**
     * HttpUtilsHelper对象
     */
    private HttpUtilsHelper httpUtilsHelper;
    /**
     *  XRecylerViewHelper对象
     */
    private XRecylerViewHelper xRecylerViewHelper;
    /**
     * ImageView数组
     */
    private Integer[] mainImgs = {R.mipmap.yinjubao_bg,
                                    R.mipmap.jinjubao_bg};
    private Integer[] viceImg1s = {R.mipmap.yinjubao_bg_shuhui,
                                     R.mipmap.jinjubao_bg_touzhi};
    private Integer[] viceImg2s = {R.mipmap.yinjubao_bg_daozhang,
                                     R.mipmap.jinjubao_bg_jingxuan};
    private Integer[] viceImg3s = {R.drawable.transparent_image_bg,
                                     R.mipmap.jinjubao_bg_shouyi};
    /**
     * url数组
     */
    private String[] imageUrls = {
            "http://192.168.1.158:1234/weibank-portal/uploadimgsss/app/adtwo.png",
            "http://192.168.1.158:1234/weibank-portal/uploadimgsss/app/adone.png",
            "http://192.168.1.158:1234/weibank-portal/uploadimgsss/app/adthree.png",
            "http://192.168.1.158:1234/weibank-portal/uploadimgsss/app/adone.png",
            "http://192.168.1.158:1234/weibank-portal/uploadimgsss/app/adthree.png"
    };
    /**
     * 存储对象列表
     */
    private ArrayList<String> arrays = new ArrayList<>();
    private ArrayList<MainProjectBean> beans = new ArrayList<>();
    private List<ImageView> views = new ArrayList<>();
    private List<ADInfo> infos = new ArrayList<>();
    /**
     * CycleViewPager对象
     */
    private CycleViewPager cycleViewPager;
    /**
     *  常量
     */
    private  final String RETURN_CODE = "return_code";
}
