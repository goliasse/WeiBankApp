package com.weibank.com.weibankapp;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.weibank.com.weibankapp.utils.NormalMethods;

public class HeHuoRenActivity extends BaseActivity
                               implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_he_huo_ren);
        /**
         * 调用初始化方法
         */
        assignViews();
    }
    @Override
    public void onClick(View v){
       switch(v.getId()){
           //跳转到邀请好友界面
           case R.id.hehuorenyaoqinglan:
               NormalMethods.exitAndSwicthActivity(HeHuoRenActivity.this,
                                                   NewShareFriendActivity.class,false);
                break;
       }
    }
    /**
     * 初始化方法
     */
    private void assignViews(){
        /**
         * TextView对象
         */
        mHehuorenTv = (TextView) findViewById(R.id.hehuoren_tv);
        mTodayTicheng = (TextView) findViewById(R.id.today_ticheng);
        mHehuoren2ndgrade = (TextView) findViewById(R.id.hehuoren_2ndgrade);
        mHehuoren3rdgrade = (TextView) findViewById(R.id.hehuoren_3rdgrade);
        mTuijianHehuoren = (TextView) findViewById(R.id.tuijian_hehuoren);
        /**
         * ImageView对象
         */
        mHehuorenyaoqinglan = (ImageView) findViewById(R.id.hehuorenyaoqinglan);
        mHehuorenyaoqinglan.setOnClickListener(this);
        /**
         * ListView对象
         */
        mHehuorenListview = (ListView) findViewById(R.id.hehuoren_listview);
    }
    /**
     * TextView对象
     */
    private TextView mHehuorenTv;
    private TextView mTodayTicheng;
    private TextView mHehuoren2ndgrade;
    private TextView mHehuoren3rdgrade;
    private TextView mTuijianHehuoren;
    /**
     * ImageView对象
     */
    private ImageView mHehuorenyaoqinglan;
    /**
     * ListView对象
     */
    private ListView mHehuorenListview;
}
