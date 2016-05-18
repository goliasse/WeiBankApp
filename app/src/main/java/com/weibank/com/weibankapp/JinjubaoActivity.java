package com.weibank.com.weibankapp;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.weibank.com.weibankapp.constants.Actitvities;
import com.weibank.com.weibankapp.adapter.JinJuBaoAdapter;
import com.weibank.com.weibankapp.adapter.JinJuBaoRecylerViewAdapter;
import com.weibank.com.weibankapp.utils.NormalMethods;
import com.weibank.com.weibankapp.views.LoadingProgressDialog;
import java.util.Arrays;
import java.util.LinkedList;

public class JinjubaoActivity extends UEBaseCompatActivity
                               implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jinjubao);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        /**
         * 调用初始化对象方法
         */
        assignViews();
        dialog = new LoadingProgressDialog(this,R.style.dialog,R.anim.loadingprogressbar);
        dialog.show();

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

        textSevena = new LinkedList<>();
        textSevena.addAll(Arrays.asList(textseven));

        imagesa = new LinkedList<>();
        imagesa.addAll(Arrays.asList(imgages));

//        adapter = new JinJuBaoAdapter(
//                                       this,textOnea,
//                                       textTwoa,textThreea,
//                                       textFoura, textFivea,
//                                       textSixa,textSevena,imagesa);

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
         * 初始化PullToRefreshListView方法
         */
//        initPullToRefreshListView();
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent,
//                                    View view, int position, long id){
//
//                TextView root = (TextView)view.findViewById(R.id.rate);
//                int index = (int)root.getTag();
//                startActivity(new Intent(JinjubaoActivity.this, Actitvities.activies[index]));
//            }
//        });
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            //返回上一页面
            case R.id.rl_topview:
                JinjubaoActivity.this.finish();
                break;
        }
    }
    /**
     * 初始化PullToRefreshListView方法
     */
//    private void initPullToRefreshListView(){
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
//                                              .setLastUpdatedLabel(NormalMethods.getStringDate());
//                        isDown = false;
//                        new DataAsyncTask().execute();
//                    }
//                });
//    }
    /**
     * 初始化RecylerView方法
     */
//    private void initRecylerView(){
//
//        int mOritation = LinearLayoutManager.VERTICAL;
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(mOritation);
//        mRecyclerView.setLayoutManager(manager);
//        mAdapter = new JinJuBaoRecylerViewAdapter(textone, texttwo, textthree,
//                                                   textfour, textfive, textsix,
//                                                   textseven,mimgages);
//        mRecyclerView.setAdapter(mAdapter);
//    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){
        /**
         * TextView对象
         */
        mJinjubaoHint = (TextView) findViewById(R.id.tvdown_nomoredata);
        mTvUpNoMoreData = (TextView) findViewById(R.id.tvup_nomoredata);
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("金聚宝");
        /**
         * ListView对象
         */
        //mListView = findViewById(R.id.jinjubao_listview);
        //mRecyclerView = (RecyclerView)findViewById(R.id.jinjubao_recyclerview);
//        mPullToFreshListView = (PullToRefreshListView)
//                                  findViewById(R.id.pulltorefreshview);
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
    }
    /**
     * 内部类-----异步获取数据
     */
    class DataAsyncTask extends AsyncTask<Void,Void,String[]>{
        @Override
        protected String[] doInBackground(Void...params){
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

                textThreea.addFirst("[10号]");

                textFoura.addFirst("孔雀养殖2期");

                textFivea.addFirst("计日起息 1.00元起送");

                textSixa.addFirst("认筹中");

                textSevena.addFirst("热");

                imagesa.addFirst(R.mipmap.zhuangtai_bg);
            }else{

                textOnea.addLast("20.1%");

                textTwoa.addLast("预期年收益率");

                textThreea.addLast("[20号]");

                textFoura.addLast("孔雀养殖20期");

                textFivea.addLast("计日起息 1.00元起送");

                textSixa.addLast("认筹中");

                textSevena.addLast("热");

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
    private TextView mJinjubaoHint;
    private TextView mTvUpNoMoreData;
    private TextView mTopviewText;
    /**
     * ListView对象
     */
//    private ListView mListView;
//    private RecyclerView mRecyclerView;
//    private PullToRefreshListView mPullToFreshListView;
    private JinJuBaoRecylerViewAdapter mAdapter;
    private JinJuBaoAdapter adapter;
    /**
     * RelativeLayout对象
     */
    private RelativeLayout mRlTopview;
    /**
     * LoadingProgressDialog对象
     */
    private LoadingProgressDialog dialog;
    /**
     * 存储数据数组
     */
    private String[] textone = {
            "5.5%","8.6%","10.5%",
            "3.5%","11.6%","15.5%",
    };
    private String[] texttwo = {
            "预期年收益率", "预期年收益率", "预期年收益率",
            "预期年收益率", "预期年收益率", "预期年收益率",
    };
    private String[] textthree = {
            "[1号]", "[2号]", "[5号]",
            "[8号]", "[10号]", "[5号]",};
    private String[] textfour = {
            "孔雀养殖2期", "孔雀养殖1期", "孔雀养殖4期",
            "圣草线金莲5期", "圣草线金莲6期", "圣草线金莲7期",};
    private String[] textfive = {
            "计日起息 1.00元起送", "计日起息 1.00元起送",
            "计日起息 1.00元起送", "计日起息 1.00元起送",
            "计日起息 1.00元起送", "计日起息 1.00元起送",};
    private String[] textsix = {
            "认筹中", "认筹中", "认筹中",
            "认筹中", "认筹中", "认筹中",};
    private String[] textseven = {
            "热", "热", "热", "热", "热", "热",};
    private Integer[] imgages = {
            R.mipmap.zhuangtai_bg,
            R.mipmap.zhuangtai_bg,
            R.mipmap.zhuangtai_bg,
            R.mipmap.zhuangtai_bg,
            R.mipmap.zhuangtai_bg,
            R.mipmap.zhuangtai_bg,
    };
    private int[] mimgages = {
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
    private LinkedList<String> textSevena;
    private LinkedList<Integer> imagesa;

    private boolean isDown = true;
}
