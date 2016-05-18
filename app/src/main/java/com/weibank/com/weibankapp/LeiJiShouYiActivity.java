package com.weibank.com.weibankapp;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LeiJiShouYiActivity extends UEBaseCompatActivity
                                  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lei_ji_shou_yi);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        /**
         * 调用初始化对象方法
         */
        assignViews();
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            //返回到上一页面
            case R.id.rl_topview:
                LeiJiShouYiActivity.this.finish();
                break;
        }
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
        /**
         * TextView对象
         */
        mHehuorenTv = (TextView) findViewById(R.id.hehuoren_tv);
        mListviewLeijishouyi = (ListView) findViewById(R.id.listview_leijishouyi);
    }
    /**
     * ImageView对象
     */
    private RelativeLayout mRlTopview;
    /**
     * TextView对象
     */
    private TextView mHehuorenTv;
    /**
     * ListView对象
     */
    private ListView mListviewLeijishouyi;
}
