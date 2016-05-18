package com.weibank.com.weibankapp;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.utils.NormalMethods;

public class ChongZhiActivity extends UEBaseCompatActivity
                               implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chong_zhi);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        /**
         * 调用初始化对象方法
         */
        assignViews();
        intent = this.getIntent();
        String bank = intent.getStringExtra(Constants.BANKNAME);
        if(bank == null || bank.length() == 0){

            mSelectBank.setText("请选择银行");
        }else {

            mSelectBank.setText(bank);
        }
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.rl_topview:
                ChongZhiActivity.this.finish();
                break;
            //跳转到选择银行页面
            case R.id.select_bank:
            case R.id.input_chongzhi_num:
                NormalMethods.exitAndSwicthActivity(ChongZhiActivity.this,
                                                    SelectBankActivity.class,true);
                break;
        }
    }
    /**
     * 把EditText光标移动到前面
     */
    private void bringToFront(CharSequence charSequence){
        if(charSequence instanceof Spannable){

            Spannable text = (Spannable)charSequence;
            Selection.setSelection(text,0);
        }
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){
        /**
         * EditText对象
         */
        mInputChongzhijiner = (EditText) findViewById(R.id.input_chongzhijiner);
        mInputName = (EditText) findViewById(R.id.input_name);
        mInputIdCard = (EditText) findViewById(R.id.input_id_card);
        mInputChongzhiNum = (LinearLayout) findViewById(R.id.input_chongzhi_num);
        mInputChongzhiNum.setOnClickListener(this);
        mInputCardNumber = (EditText) findViewById(R.id.input_card_number);
        mInputPhoneNumber = (EditText) findViewById(R.id.input_phone_number);
        mInputOpenBank = (EditText) findViewById(R.id.input_open_bank);
        /**
         * TextView对象
         */
        mChongzhimianzhi = (TextView) findViewById(R.id.chongzhimianzhi);
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("充值");
        mSelectBank = (TextView)findViewById(R.id.select_bank);
        mSelectBank.setOnClickListener(this);
        /**
         * Button及其子对象
         */
        mChongzhi = (Button) findViewById(R.id.chongzhi);
        mChongzhiradiobtn = (CheckBox) findViewById(R.id.chongzhiradiobtn);
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
    }
    /**
     * EditText对象
     */
    private EditText mInputChongzhijiner;
    private EditText mInputName;
    private EditText mInputIdCard;
    private EditText mInputCardNumber;
    private EditText mInputPhoneNumber;
    private EditText mInputOpenBank;
    /**
     * TextView对象
     */
    private TextView mChongzhimianzhi;
    private TextView mTopviewText;
    private TextView mSelectBank;
    /**
     * LinearLayout对象
     */
    private LinearLayout mInputChongzhiNum;
    /**
     * ImageView对象
     */
    private RelativeLayout mRlTopview;
    /**
     * Button及其子对象
     */
    private Button mChongzhi;
    private CheckBox mChongzhiradiobtn;
    /**
     * Intent对象
     */
    private Intent intent;
}
