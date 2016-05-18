package com.weibank.com.weibankapp;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baofoo.sdk.vip.BaofooPayActivity;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.weibank.com.weibankapp.beans.ZhifuorderBean;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.constants.UserOperationParams;
import com.weibank.com.weibankapp.service.OrderService;
import com.weibank.com.weibankapp.utils.CodeTransfer;
import com.weibank.com.weibankapp.utils.HttpUtilsHelper;
import com.weibank.com.weibankapp.utils.MD5Util;
import com.weibank.com.weibankapp.utils.NormalMethods;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class TestInterfaceActivity extends AppCompatActivity
                                    implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_interface);
        /**
         * 调用初始化对象方法
         */
        assignViews();
//        mTest.setEnabled(false);
//        mTest.setText("abcdefg");

        String   mytext   = "";
        String   mytext2   =  "";
        try{

            mytext = java.net.URLEncoder.encode("中国",   "utf-8");
            mytext2 = java.net.URLDecoder.decode(mytext, "utf-8");

            Log.e("myerror", "mytext:"+mytext + ";"+"mytext:"+mytext2);
        }catch (Exception e){

        }

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.test:

                count = 60;
                new UpdateThread().start();
                //Toast.makeText(this, "abcde", Toast.LENGTH_SHORT).show();
                httpUtilsHelper = HttpUtilsHelper.getInstance();
                Map<String,String> params = new HashMap<>();
                final String requestUrl = Constants.WEBURL + "app/queryCustomerFinancialList";
//                final String requestUrl = Constants.WEBURLAB + "caifu-front/app/notify";
//                final String requestUrl = Constants.WEBURL + "app/getChargeNo";

//                params.put(PRODUCTID, "178927");
//                params.put(MOBILE, "13023063971");

//                  params.put(PAGE, 1);
//                  params.put(PAGESIZE, 5);
//                  params.put(TYPE, 0);

                ZhifuorderBean bean = new ZhifuorderBean();

                bean.setAmount("0.01");
                params.put("amount", "0.01");

                bean.setOrderId(orderId);
                params.put("orderId", orderId);

                bean.setCardNo("6227001633010677193");
                params.put("cardNo", "6227001633010677193");
                // 6227001633010677193
                // 6217001210052700489

                bean.setName("郑");
                String personA = "郑响响";
                String personB = "";
                try{

                    //personA = URLEncoder.encode("郑响响","UTF-8");
                    //personB = URLDecoder.decode(personA, "UTF-8");
                    //person = new String(person.getBytes("gbk"),"utf-8");
                    personB  =  URLEncoder.encode(URLEncoder.encode(personA, "UTF-8"),"UTF-8");
                }catch(Exception e){

                    e.printStackTrace();
                }

                params.put("name", personA);

                bean.setBankCode("CCB");
                params.put("bankCode", "CCB");

                bean.setIdentityNo("342222199301302812");
                params.put("identityNo", "342222199301302812");

                bean.setMac("");
                params.put("mac", "");

                bean.setMerchantId("809012");
                params.put("merchantId", "809012");

                bean.setMerchantUrl("https://gw.baofoo.com/apipay/sdk");
                params.put("merchantUrl", "https://gw.baofoo.com/apipay/sdk");

                bean.setOrderTime(NormalMethods.getStringDate());
                params.put("orderTime", NormalMethods.getStringDate());

                bean.setPhone("13023063971");
                params.put("phone", "13023063971");

                bean.setUserId("1075");
                params.put("userId","1075");
//                params.put(REPAYTYPEID,3);
//                params.put(BANKCODE,mBankcode.getText().toString());
//                params.put(BANKMOBILE,mBankphone.getText().toString());
//                params.put(BANKLOCATION, mBanklocation.getText().toString());

                httpUtilsHelper.post(Constants.ZHIFU_GET_CHARGE_NO_URL,params,
                                       new RequestCallBack<String>(){
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo){

                        Log.e("myerror", responseInfo.result);
                        JSONObject object = JSON.parseObject(responseInfo.result);
                        String result_code = object.getString(RESULTCODE);
                        if(NormalMethods.isNotEmpty(result_code)
                           && result_code.equals(UserOperationParams.BACK_RETURN_CODE)){

//                            NormalMethods.exitAndSwicthActivity(TestInterfaceActivity.this,
//                                                                ZhiFuActivity.class,true);

                            Intent payintent = new Intent(TestInterfaceActivity.this,
                                                           BaofooPayActivity.class);
                            String tradeNo = object.getString("chargeNo");
                            payintent.putExtra(BaofooPayActivity.PAY_TOKEN, tradeNo);
                            payintent.putExtra(BaofooPayActivity. PAY_BUSINESS,true);
                            TestInterfaceActivity.this.startActivityForResult
                                    (payintent, OrderService.REQUEST_CODE_BAOFOO_SDK);
                        }
                    }
                    @Override
                    public void onFailure(HttpException e, String s){

                        Log.e("myerror", s);
                    }
                });
                break;
            case R.id.orderId:

                httpUtilsHelper = HttpUtilsHelper.getInstance();
                httpUtilsHelper.post(Constants.GET_ORDERID_URL,new RequestCallBack<String>(){
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo){

                        JSONObject object = JSON.parseObject(responseInfo.result);
                        Log.e("myerror", responseInfo.result);
                        orderId = object.getString("orderId");
                    }

                    @Override
                    public void onFailure(HttpException e, String s){

                        Log.e("myerror", s);
                    }
                });
                break;
            case R.id.msao:

                NormalMethods.exitAndSwicthActivity(this,PreHandleCodeActivity.class,false);
                break;
        }
    }
    /**
     *  更改button线程
     */
    class UpdateThread extends Thread{
        @Override
        public void run(){
            super.run();
            while(count > 0){
                try{

                    Thread.sleep(1000);
                    count--;
                }catch (Exception e){

                    e.printStackTrace();
                }
                if(count == 0){

                    handler.sendEmptyMessage(UserOperationParams.REGISTER_SUCCESS);
                }else{

                    handler.sendEmptyMessage(UserOperationParams.REGISTER_FAILED);
                }
            }
        }
    }
    /**
     * Handler对象
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){

            super.handleMessage(msg);
            if(msg.what == UserOperationParams.REGISTER_SUCCESS){

                mTest.setEnabled(true);
                mTest.setText("测试Interface");
                mTest.invalidate();
            }else if(msg.what == UserOperationParams.REGISTER_FAILED){

                mTest.setEnabled(false);
                mTest.setText("剩余" + count +"s");
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == OrderService.REQUEST_CODE_BAOFOO_SDK){
            String result = "", msg = "";
            if(data == null || data.getExtras() == null){

                msg = "支付已被取消";
            }else{
                //result返回值判断 -1:失败  0:取消  1:成功  10:处理中
                result = data.getExtras().getString(BaofooPayActivity.PAY_RESULT);
                msg = data.getExtras().getString(BaofooPayActivity.PAY_MESSAGE);
            }
        }
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){

        mTest = (Button) findViewById(R.id.test);
        mTest.setOnClickListener(this);

        mOrderId = (Button) findViewById(R.id.orderId);
        mOrderId.setOnClickListener(this);

        mMsao = (Button)findViewById(R.id.msao);
        mMsao.setOnClickListener(this);

        mPhone = (EditText) findViewById(R.id.phone);
        mCard = (EditText) findViewById(R.id.card);

        mBank = (EditText) findViewById(R.id.bank);
        mBankcode = (EditText) findViewById(R.id.bankcode);
        mBankphone = (EditText) findViewById(R.id.bankphone);
        mBanklocation = (EditText) findViewById(R.id.banklocation);
    }


    /**
     * Button对象
     */
    private Button mTest;
    private Button mOrderId;
    private Button mMsao;
    /**
     * EditText对象
     */
    private EditText mPhone;
    private EditText mCard;
    private EditText mBank;
    private EditText mBankcode;
    private EditText mBankphone;
    private EditText mBanklocation;
    /**
     * 常量
     */
    private  final String PAGE = "page";
    private  final String PAGESIZE = "pageSize";
    private  final String REPAYTYPEID = "repayTypeId";
    private  final String TYPE = "type";
    private  final String POSITIONID = "positionId";
    private  final String PRODUCTID = "productId";
    private  final String POSITIONBALANCE = "positionBalance";

    private  final String MOBILE = "mobile";
    private  final String BANKCARD = "bankCard";
    private  final String BANKNAME = "bankName";
    private  final String BANKCODE = "bankCode";
    private  final String BANKMOBILE = "bankMobile";
    private  final String BANKLOCATION = "bankLocation";


    private  final String SRCPASSWORD = "srcPassword";
    private  final String NEWPASSWORD = "newPassword";

    private  final String AMOUNT = "amount";
    private  final String PHONE = "phone";
    private  final String CARDNO = "cardNo";
    private  final String RESULTCODE = "resultCode";
    private int count = 0;

    /**
     * HttpUtilsHelper对象
     */
    private HttpUtilsHelper httpUtilsHelper;

    private String orderId = "";
}
