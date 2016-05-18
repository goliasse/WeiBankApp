package com.weibank.com.weibankapp;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baofoo.sdk.vip.BaofooPayActivity;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.beans.PayParams;
import com.weibank.com.weibankapp.service.OrderService;
import com.weibank.com.weibankapp.utils.NormalMethods;

public class ZhiFuActivity extends UEBaseCompatActivity
                            implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_fu);
        /**
         * 调用初始化对象方法
         */
        assignViews();
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            //选择支付银行
            case R.id.bank_name_layout:
                Intent intent = new Intent(getApplicationContext(),
                        BankListActivity.class);
                startActivityForResult(intent, Constants.REQUESTCODEBANK);
                break;
            //提交支付
            case R.id.btn_submit_test:
                payParams = new PayParams();
                payParams.setPayMoneyNum(Double.parseDouble(mIptMoney.getText().toString()));
                payParams.setPayIdCard(mIdCard.getText().toString());
                payParams.setPayPerson(mName.getText().toString());
                payParams.setPayBankCard(mBankCard.getText().toString());
                payParams.setPayPhoneNum(mMobileEd.getText().toString());
                OrderService orderService = new OrderService(ZhiFuActivity.this,payParams);
                orderService.execute();
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode,
                                      int resultCode, Intent data){
        if (requestCode == OrderService.REQUEST_CODE_BAOFOO_SDK){
            //String result = "";
            String msg = "";
            if(data == null || data.getExtras() == null){
                msg = "支付已被取消";
            }else{
                //result = data.getExtras().getString(
                //BaofooPayActivity.PAY_RESULT);// -1:失败 0:取消 1:成功 10:处理中
                msg = data.getExtras().getString(BaofooPayActivity.PAY_MESSAGE);
            }
            AlertDialog dialog = new AlertDialog(this) {};
            dialog.setMessage(msg);
            dialog.show();
        } else if (requestCode == Constants.REQUESTCODEBANK){
            if (data != null){

                mBankName.setText(data.getStringExtra("bank_name"));
                NormalMethods.onSaveCode(getApplicationContext(),
                                         data.getStringExtra("bank_id"));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){
        /**
         * EditText对象
         */
        mIptMoney = (EditText) findViewById(R.id.ipt_money);
        mName = (EditText) findViewById(R.id.name);
        mIdCard = (EditText) findViewById(R.id.id_card);
        mBankCard = (EditText) findViewById(R.id.bank_card);
        mMobileEd = (EditText) findViewById(R.id.mobile_ed);
        /**
         * RelativeLayout对象
         */
        mBankNameLayout = (RelativeLayout) findViewById(R.id.bank_name_layout);
        mBankNameLayout.setOnClickListener(this);
        /**
         * TextView对象
         */
        mBankName = (TextView) findViewById(R.id.bank_name);
        /**
         * Button对象
         */
        mBtnSubmitTest = (Button) findViewById(R.id.btn_submit_test);
        mBtnSubmitTest.setOnClickListener(this);
    }
    /**
     * EditText对象
     */
    private EditText mIptMoney;
    private EditText mName;
    private EditText mIdCard;
    private EditText mBankCard;
    private EditText mMobileEd;
    /**
     * TextView对象
     */
    private TextView mBankName;
    /**
     * RelativeLayout对象
     */
    private RelativeLayout mBankNameLayout;
    /**
     * Button对象
     */
    private Button mBtnSubmitTest;
    /**
     * PayParams对象
     */
    private PayParams payParams;
}
