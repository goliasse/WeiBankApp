package com.weibank.com.weibankapp;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.weibank.com.weibankapp.adapter.JinJuBaoAdapter;
import com.weibank.com.weibankapp.adapter.YinJuBaoAdapter;
import com.weibank.com.weibankapp.adapter.YinJuBaoRecylerViewAdapter;
import com.weibank.com.weibankapp.utils.NormalMethods;
import com.weibank.com.weibankapp.views.LoadingProgressDialog;

import java.util.Arrays;
import java.util.LinkedList;

public class YinJuBaoActivity extends UEBaseCompatActivity
                               implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yin_ju_bao);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        /**
         * 调用初始化对象方法
         */
        assignViews();
//        YinJuBaoAdapter adapter = new YinJuBaoAdapter(
//                                             this,textone,
//                                             texttwo,textthree,
//                                             textfour, textfive,
//                                             textsix,imgages);


        textOnea = new LinkedList<>();
        textOnea.addAll(Arrays.asList(textone));

        textTwoa = new LinkedList<>();
        textTwoa.addAll(Arrays.asList(texttwo));

        textThreea = new LinkedList<>();
        textThreea.addAll(Arrays.asList(textthree));

        textFoura = new LinkedList<>();
        textFoura.addAll(Arrays.asList(textfour));

        textFivea = new LinkedList<>();
        textFivea.addAll(Arrays.asList(textfive));

        textSixa = new LinkedList<>();
        textSixa.addAll(Arrays.asList(textsix));

        imagesa = new LinkedList<>();
        imagesa.addAll(Arrays.asList(mimgages));

        //mYinjubaoListview.setAdapter(adapter);
        dialog = new LoadingProgressDialog(this,R.style.dialog,R.anim.loadingprogressbar);
        dialog.show();

//        adapter = new YinJuBaoAdapter(
//                this,textOnea,
//                textTwoa,textThreea,
//                textFoura, textFivea,
//                textSixa,imagesa);
//        final ListView listView = mPullToFreshListView.getRefreshableView();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                dialog.dismiss();
                /**
                 * 调用初始化RecylerView方法
                 */
                //initRecylerView();
//                listView.setAdapter(adapter);
            }
        }, 3000);
        /**
         * 调用PullToRefreshListView方法
         */
//        initPullToRefreshListView();
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent,
//                                    View view, int position, long id) {
//
//                TextView root = (TextView) view.findViewById(R.id.rate);
//                int index = (int) root.getTag();
//                Toast.makeText(YinJuBaoActivity.this, index + "---abc",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.rl_topview:
                YinJuBaoActivity.this.finish();
                break;
        }
    }
    /**
     *  初始化对象方法
     */
    private void assignViews(){
        /**
         * TextView对象
         */
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("银聚宝");
        mTvdownNomoredata = (TextView) findViewById(R.id.tvdown_nomoredata);
        /**
         * ListView对象
         */
        //mYinjubaoListview = (ListView) findViewById(R.id.yinjubao_listview);
        //mRecylerView = (RecyclerView)findViewById(R.id.recylerview);
//        mPullToFreshListView = (PullToRefreshListView)
//                                 findViewById(R.id.pulltorefreshview);
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
        /**
         * LinearLayout对象
         */
        mLltopNomoredata = (LinearLayout) findViewById(R.id.lltop_nomoredata);
        mLltopNomoredata.setVisibility(View.GONE);
    }
    /**
     * 初始化PullToRefreshListView方法
     */
    private void initPullToRefreshListView(){
//        mPullToFreshListView.setOnRefreshListener(
//                new PullToRefreshBase.OnRefreshListener2<ListView>(){
//                    @Override
//                    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView){
//                        String label = DateUtils.formatDateTime(getApplicationContext(),
//                                System.currentTimeMillis(),
//                                DateUtils.FORMAT_ABBREV_ALL
//                                        | DateUtils.FORMAT_SHOW_YEAR
//                                        | DateUtils.FORMAT_ABBREV_MONTH
//                                        | DateUtils.FORMAT_ABBREV_TIME);
//                        mPullToFreshListView.getLoadingLayoutProxy()
//                                              .setLastUpdatedLabel(NormalMethods.getStringDate());
//                        new DataAsyncTask().execute();
//                        isDown = true;
//                    }
//                    @Override
//                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView){
//                        String label = DateUtils.formatDateTime(getApplicationContext(),
//                                System.currentTimeMillis(),
//                                DateUtils.FORMAT_ABBREV_ALL
//                                        | DateUtils.FORMAT_SHOW_YEAR
//                                        | DateUtils.FORMAT_ABBREV_MONTH
//                                        | DateUtils.FORMAT_ABBREV_TIME);
//                        mPullToFreshListView.getLoadingLayoutProxy()
////                                              .setLastUpdatedLabel(NormalMethods.getStringDate());
//                        isDown = false;
//                        new DataAsyncTask().execute();
//                    }
//                });
    }
    /**
     * 初始化RecylerView方法
     */
//    private void initRecylerView(){
//
//        int mOritation = LinearLayoutManager.VERTICAL;
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(mOritation);
//        mRecylerView.setLayoutManager(manager);
//        mAdapter = new YinJuBaoRecylerViewAdapter(textone, texttwo, textthree,
//                                                   textfour, textfive, textsix,imgages);
//        mRecylerView.setAdapter(mAdapter);
//    }

    /**
     * 内部类-----异步获取数据
     */
    class DataAsyncTask extends AsyncTask<Void,Void,String[]> {
        @Override
        protected String[] doInBackground(Void... params){
            try{

                Thread.sleep(3000);

            }catch (Exception e){

                e.printStackTrace();
            }
            return textone;
        }
        @Override
        protected void onPostExecute(String[] strings){
            if(isDown){

                textOnea.addFirst("10.1%");

                textTwoa.addFirst("预期年收益率");

                textThreea.addFirst("银聚宝-第十期");

                textFoura.addFirst("即日起息 1.00元起送");

                textFivea.addFirst("认筹中");

                textSixa.addFirst("热");

                imagesa.addFirst(R.mipmap.zhuangtai_bg);
            }else{

                textOnea.addLast("20.1%");

                textTwoa.addLast("预期年收益率");

                textThreea.addLast("银聚宝-第十期");

                textFoura.addLast("即日起息 1.00元起送");

                textFivea.addLast("认筹中");

                textSixa.addLast("热");

                imagesa.addLast(R.mipmap.zhuangtai_bg);
            }
//            adapter.notifyDataSetChanged();
//            mPullToFreshListView.onRefreshComplete();
            super.onPostExecute(strings);
        }
    }
    /**
     * TextView对象
     */
    private TextView mTopviewText;
    private TextView mTvdownNomoredata;
    /**
     * ListView对象
     */
//    private ListView mYinjubaoListview;
//    private RecyclerView mRecylerView;
//    private PullToRefreshListView mPullToFreshListView;
    private YinJuBaoAdapter adapter;
    private YinJuBaoRecylerViewAdapter mAdapter;
    /**
     * RelativeLayout对象
     */
    private RelativeLayout mRlTopview;
    /**
     * LinearLayout对象
     */
    private LinearLayout mLltopNomoredata;
    /**
     * LoadingProgressDialog对话框
     */
    private LoadingProgressDialog dialog;
    /**
     * 存储数据数组
     */
    private String[] textone = {
         "8.5%","7.6%","10.5%",
         "6.5%","9.6%","12.5%"
    };

    private String[] texttwo = {
        "预期年收益率", "预期年收益率", "预期年收益率",
        "预期年收益率", "预期年收益率", "预期年收益率",
    };
    private String[] textthree = {
          "银聚宝-第一期",
          "银聚宝-第二期",
          "银聚宝-第三期",
          "银聚宝-第四期",
          "银聚宝-第五期",
          "银聚宝-第六期",};
    private String[] textfour = {
          "即日起息 1.00元起送", "即日起息 1.00元起送",
          "即日起息 1.00元起送", "即日起息 1.00元起送",
          "即日起息 1.00元起送", "即日起息 1.00元起送",};
    private String[] textfive = {
          "认筹中", "认筹中", "认筹中",
          "认筹中", "认筹中", "认筹中",};
    private String[] textsix = {
          "热", "热", "热", "热", "热", "热",};
    private int[] imgages = {
        R.mipmap.zhuangtai_bg,
        R.mipmap.zhuangtai_bg,
        R.mipmap.zhuangtai_bg,
        R.mipmap.zhuangtai_bg,
        R.mipmap.zhuangtai_bg,
        R.mipmap.zhuangtai_bg,
    };
    private Integer[] mimgages = {
            R.mipmap.zhuangtai_bg,
            R.mipmap.zhuangtai_bg,
            R.mipmap.zhuangtai_bg,
            R.mipmap.zhuangtai_bg,
            R.mipmap.zhuangtai_bg,
            R.mipmap.zhuangtai_bg,
    };

    private LinkedList<String> textOnea;
    private LinkedList<String> textTwoa;
    private LinkedList<String> textThreea;
    private LinkedList<String> textFoura;
    private LinkedList<String> textFivea;
    private LinkedList<String> textSixa;
    private LinkedList<Integer> imagesa;

    private boolean isDown = true;
}
