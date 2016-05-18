package com.weibank.com.weibankapp;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.weibank.com.weibankapp.utils.NormalMethods;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class WithdrawMoneyActivity extends UEBaseCompatActivity
                                    implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_money);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        /**
         * 调用初始化对象方法
         */
        assignViews();
//        mTruewithdrawlNum.addTextChangedListener(
//                            new DeTextWatcher(this,mTruewithdrawlNum,mTruemoneycap));


        mTruewithdrawlNum.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){}
            @Override
            public void afterTextChanged(Editable s){
                if (!s.toString().equals(currentAmount)){

                    mTruewithdrawlNum.removeTextChangedListener(this);
                    String replaceable = String.format("[%s, \\s.]",
                            NumberFormat.getCurrencyInstance
                                    (Locale.CHINA).getCurrency()
                                    .getSymbol(Locale.CHINA));
                    String cleanString = s.toString().replaceAll(replaceable, "");
                    if(cleanString.equals("")
                            || new BigDecimal(cleanString).toString().equals("0")){

                        mTruewithdrawlNum.setText(null);
                        mTruemoneycap.setText(null);
                    }else{

                        double parsed = Double.parseDouble(cleanString);
                        String formatted = NumberFormat.getCurrencyInstance
                                          (Locale.CHINA).format((parsed / 100));
                        currentAmount = formatted;
                        mTruewithdrawlNum.setText(formatted);

                        String rmbValue = formatted.trim().replace("￥","");
                        mTruemoneycap.setText(NormalMethods.toChineseCurrency
                                              (Double.parseDouble((parsed / 100) + "")));
//                        Toast.makeText(WithdrawMoneyActivity.this, rmbValue,
//                                       Toast.LENGTH_SHORT).show();
//                        Log.e("abcd",(parsed / 100) + "");
                        mTruewithdrawlNum.setSelection(formatted.length());
                    }
                    mTruewithdrawlNum.addTextChangedListener(this);
                }
            }
        });

//        NormalMethods.setPricePoint(mTruewithdrawlNum,mTruemoneycap);

//        mTruewithdrawlNum.addTextChangedListener(new TextWatcher(){
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before,int count){
//                if(s.toString().contains(".")){
//                    if(s.length() - 1 - s.toString().indexOf(".") > 2){
//
//                        //if(s.length() - 1 - s.toString().indexOf(".") > 2){
//                        String mValue = s.toString().substring(0, s.toString().indexOf(".") + 3);
//
////                        String mValue = s.toString();
////                        s = s.toString().subSequence(0,
////                                s.toString().indexOf(".") + 3);
//                        mTruewithdrawlNum.setText(mValue);
//                        mTruewithdrawlNum.setSelection(s.length());
//                        mTruemoneycap.setText(NormalMethods.toChineseCurrency(Double.parseDouble(mValue)));
//                    }
//                }
//                if(s.toString().trim().substring(0).equals(".")){
//
//                    String mValue = "0" + s.toString();
//                    //s = "0" + s;
//                    mTruewithdrawlNum.setText(mValue);
//                    mTruewithdrawlNum.setSelection(2);
//                    mTruemoneycap.setText(NormalMethods.toChineseCurrency(Double.parseDouble(mValue)));
//                }
//                if(s.toString().startsWith("0")
//                        && s.toString().trim().length() > 1){
//                    if(!s.toString().substring(1, 2).equals(".")){
//
//                        String mValue = s.toString().substring(0, 1);
//                        //editText.setText(s.subSequence(0, 1));
//                        mTruewithdrawlNum.setText(mValue);
//                        mTruewithdrawlNum.setSelection(1);
//                        mTruemoneycap.setText(NormalMethods.toChineseCurrency(Double.parseDouble(mValue)));
//                        return;
//                    }
//                }
//                if(!NormalMethods.isNotEmpty(s.toString())){
//
//                    mTruemoneycap.setText(null);
//                }
////                textView.addTextChangedListener(this);
//            }
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,int after){}
//            @Override
//            public void afterTextChanged(Editable s){}
//        });
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rl_topview:

                WithdrawMoneyActivity.this.finish();
                break;
        }
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){
        /**
         * TextView对象
         */
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("提现");
        mTruename = (TextView) findViewById(R.id.truename);
        mTruebankname = (TextView) findViewById(R.id.truebankname);
        mTruecardnumber = (TextView) findViewById(R.id.truecardnumber);
        mTruecanWithdrawlNum = (TextView) findViewById(R.id.truecan_withdrawl_num);
        mTruemoneycap = (TextView) findViewById(R.id.truemoneycap);
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
        /**
         * EditText对象
         */
        mTruewithdrawlNum = (EditText) findViewById(R.id.truewithdrawl_num);
        /**
         * Button对象
         */
        mEnsureWithdrawl = (Button) findViewById(R.id.ensure_withdrawl);
    }
    /**
     * TextView对象
     */
    private TextView mTopviewText;
    private TextView mTruename;
    private TextView mTruebankname;
    private TextView mTruecardnumber;
    private TextView mTruecanWithdrawlNum;
    private TextView mTruemoneycap;
    /**
     * EditText对象
     */
    private EditText mTruewithdrawlNum;
    /**
     * ImageView对象
     */
    private RelativeLayout mRlTopview;
    /**
     * Button对象
     */
    private Button mEnsureWithdrawl;
    /**
     * String对象
     */
    private String currentAmount = "";
}
