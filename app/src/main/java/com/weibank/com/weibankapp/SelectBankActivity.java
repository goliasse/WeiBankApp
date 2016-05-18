package com.weibank.com.weibankapp;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.adapter.SelectBankAdapter;

public class SelectBankActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank);
        if(Build.VERSION.SDK_INT  >=  Build.VERSION_CODES.KITKAT){

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        /**
         * 调用初始化对象方法
         */
        assignViews();
        mBankslist.setAdapter(new SelectBankAdapter(this,bankLists));
        mBankslist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                     int position, long id){

                final TextView text = (TextView)
                                       view.findViewById(R.id.listbankname);
                String contents = text.getText().toString();
                Intent intent = new Intent(SelectBankActivity.this,ChongZhiActivity.class);
                intent.putExtra(Constants.BANKNAME,contents);
                startActivity(intent);
                SelectBankActivity.this.finish();
            }
        });
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){

        mBankslist = (ListView) findViewById(R.id.bankslist);
    }
    /**
     * ListView对象
     */
    private ListView mBankslist;
    /***
     * 存储银行数组
     */
    private String[] bankLists = {
            "中国工商银行",
            "中国农业银行",
            "中国建设银行",
            "中国银行",
            "中国交通银行",
            "中国兴业银行",
            "中信银行",
            "中国光大银行",
            "平安银行",
            "中国邮政储蓄银行",
           "上海银行",
           "浦东发展银行",};
}
