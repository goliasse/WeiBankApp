package com.weibank.com.weibankapp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.weibank.com.weibankapp.utils.EtActionModeCallback;

public class NewRegisterActivity extends EBaseCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams
                   .SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_new_registerx);
        /**
         *  调用初始化对象方法
         */
        assignViews();
        mNewPhoneEt.setCustomSelectionActionModeCallback(new EtActionModeCallback());
        mNewPhoneEt.setOnTouchListener(new EtTouchListener(mNewPhoneEt,PHONE));
//        mNewPhoneEt.setOnTouchListener(new View.OnTouchListener() {
//
//            int touch_flag = 0;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                touch_flag++;
//                if (touch_flag % 2 == 0) {
//
//                    InputMethodManager imm = (InputMethodManager)
//                            getSystemService(INPUT_METHOD_SERVICE);
//                    imm.showSoftInput(mNewPhoneEt, InputMethodManager.SHOW_FORCED);
//
//                    flag = PHONE;
//                    switchBg(flag);
//                    return true;
//                }
//                return false;
//            }
//        });

        mNewRegisterpwdEta.setCustomSelectionActionModeCallback(new EtActionModeCallback());
        mNewRegisterpwdEta.setOnTouchListener(new EtTouchListener(mNewRegisterpwdEta,PWD));
//        mNewRegisterpwdEta.setOnTouchListener(new View.OnTouchListener(){
//
//            int touch_flag = 0;
//            @Override
//            public boolean onTouch(View v, MotionEvent event){
//
//                touch_flag++;
//                if(touch_flag % 2 == 0){
//
//                    InputMethodManager imm = (InputMethodManager)
//                            getSystemService(INPUT_METHOD_SERVICE);
//                    imm.showSoftInput(mNewPhoneEt,InputMethodManager.SHOW_FORCED);
//
//                    flag = PWD;
//                    switchBg(flag);
//                    return true;
//                }
//                return false;
//            }
//        });
        mNewRegisterpwdEtb.setCustomSelectionActionModeCallback(new EtActionModeCallback());
        mNewRegisterpwdEtb.setOnTouchListener(new EtTouchListener(mNewRegisterpwdEtb,EPWD));
//        mNewRegisterpwdEtb.setOnTouchListener(new View.OnTouchListener(){
//
//            int touch_flag = 0;
//            @Override
//            public boolean onTouch(View v, MotionEvent event){
//
//                touch_flag++;
//                if(touch_flag % 2 == 0){
//
//                    InputMethodManager imm = (InputMethodManager)
//                            getSystemService(INPUT_METHOD_SERVICE);
//                    imm.showSoftInput(mNewPhoneEt,InputMethodManager.SHOW_FORCED);
//
//                    flag = EPWD;
//                    switchBg(flag);
//                    return true;
//                }
//                return false;
//            }
//        });

        mNewRegistersmsEt.setCustomSelectionActionModeCallback(new EtActionModeCallback());
        mNewRegistersmsEt.setOnTouchListener(new EtTouchListener(mNewRegistersmsEt,SMS));
//        mNewRegistersmsEt.setOnTouchListener(new View.OnTouchListener(){
//
//            int touch_flag = 0;
//            @Override
//            public boolean onTouch(View v, MotionEvent event){
//
//                touch_flag++;
//                if(touch_flag % 2 == 0){
//
//                    InputMethodManager imm = (InputMethodManager)
//                            getSystemService(INPUT_METHOD_SERVICE);
//                    imm.showSoftInput(mNewPhoneEt,InputMethodManager.SHOW_FORCED);
//
//                    flag = SMS;
//                    switchBg(flag);
//                    return true;
//                }
//                return false;
//            }
//        });

        mNewRegisterrcdEt.setCustomSelectionActionModeCallback(new EtActionModeCallback());
        mNewRegisterrcdEt.setOnTouchListener(new EtTouchListener(mNewRegisterrcdEt,RECOMMENDER));
//        mNewRegisterrcdEt.setOnTouchListener(new View.OnTouchListener() {
//
//            int touch_flag = 0;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                touch_flag++;
//                if (touch_flag % 2 == 0) {
//
//                    InputMethodManager imm = (InputMethodManager)
//                            getSystemService(INPUT_METHOD_SERVICE);
//                    imm.showSoftInput(mNewPhoneEt, InputMethodManager.SHOW_FORCED);
//
//                    flag = RECOMMENDER;
//                    switchBg(flag);
//                    return true;
//                }
//                return false;
//            }
//        });
        mNewRegisterBackRliv.setOnTouchListener(new ImageViewTouchListener());
//        mNewRegisterBackRliv.setOnTouchListener(new View.OnTouchListener(){
//            @Override
//            public boolean onTouch(View v, MotionEvent event){
//
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//
//                    mBackImageView.setImageResource(R.mipmap.abc_back_unselected);
//                    return true;
//                }else if(event.getAction() == MotionEvent.ACTION_UP){
//
//                    mBackImageView.setImageResource(R.mipmap.abc_back_selected);
//                    return true;
//                }
//                return false;
//            }
//        });
    }
    /**
     *  改变颜色背景方法
     */
    private void switchBg(int number){
        switch(number){
            case PHONE:

                mPhoneRb.setChecked(true);
                mPhoneView.setBackgroundColor(getResources().getColor(R.color.pgreen));

                mPwdRb.setChecked(false);
                mPwdView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mEpwdRb.setChecked(false);
                mEpwdView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mSmsRb.setChecked(false);
                mSmsView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mRecommenderRb.setChecked(false);
                mRecommenderView.setBackgroundColor(getResources().getColor(R.color.pgray));
                break;
            case PWD:

                mPhoneRb.setChecked(false);
                mPhoneView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mPwdRb.setChecked(true);
                mPwdView.setBackgroundColor(getResources().getColor(R.color.pgreen));

                mEpwdRb.setChecked(false);
                mEpwdView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mSmsRb.setChecked(false);
                mSmsView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mRecommenderRb.setChecked(false);
                mRecommenderView.setBackgroundColor(getResources().getColor(R.color.pgray));
                break;
            case EPWD:

                mPhoneRb.setChecked(false);
                mPhoneView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mPwdRb.setChecked(false);
                mPwdView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mEpwdRb.setChecked(true);
                mEpwdView.setBackgroundColor(getResources().getColor(R.color.pgreen));

                mSmsRb.setChecked(false);
                mSmsView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mRecommenderRb.setChecked(false);
                mRecommenderView.setBackgroundColor(getResources().getColor(R.color.pgray));
                break;
            case SMS:

                mPhoneRb.setChecked(false);
                mPhoneView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mPwdRb.setChecked(false);
                mPwdView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mEpwdRb.setChecked(false);
                mEpwdView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mSmsRb.setChecked(true);
                mSmsView.setBackgroundColor(getResources().getColor(R.color.pgreen));

                mRecommenderRb.setChecked(false);
                mRecommenderView.setBackgroundColor(getResources().getColor(R.color.pgray));
                break;
            case RECOMMENDER:

                mPhoneRb.setChecked(false);
                mPhoneView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mPwdRb.setChecked(false);
                mPwdView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mEpwdRb.setChecked(false);
                mEpwdView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mSmsRb.setChecked(false);
                mSmsView.setBackgroundColor(getResources().getColor(R.color.pgray));

                mRecommenderRb.setChecked(true);
                mRecommenderView.setBackgroundColor(getResources().getColor(R.color.pgreen));
                break;
        }
    }
    /**
     *  初始化对象方法
     */
    private void assignViews(){
        /**
         *  LinearLayout对象
         */
        mSubLlayout = (LinearLayout) findViewById(R.id.subLlayout);
        mMainLlayout = (LinearLayout) findViewById(R.id.mainLlayout);
        /**
         *  RelativeLayout对象
         */
        mNewRegisterBackRliv = (RelativeLayout) findViewById(R.id.new_register_back_rliv);
        mNewLoginBackRltv = (RelativeLayout) findViewById(R.id.new_login_back_rltv);
        /**
         *  RadioButton对象
         */
        mPhoneRb = (RadioButton) findViewById(R.id.phone_rb);
        mPwdRb = (RadioButton) findViewById(R.id.pwd_rb);
        mEpwdRb = (RadioButton) findViewById(R.id.epwd_rb);
        mSmsRb = (RadioButton) findViewById(R.id.sms_rb);
        mRecommenderRb = (RadioButton) findViewById(R.id.recommender_rb);
        /**
         *  EditText对象
         */
        mNewPhoneEt = (EditText) findViewById(R.id.new_phone_et);
        mNewRegisterpwdEta = (EditText) findViewById(R.id.new_registerpwd_eta);
        mNewRegisterpwdEtb = (EditText) findViewById(R.id.new_registerpwd_etb);
        mNewRegistersmsEt = (EditText) findViewById(R.id.new_registersms_et);
        mNewRegisterrcdEt = (EditText) findViewById(R.id.new_registerrcd_et);
        /**
         *  View对象
         */
        mPhoneView = findViewById(R.id.phone_view);
        mPwdView = findViewById(R.id.pwd_view);
        mEpwdView = findViewById(R.id.epwd_view);
        mSmsView = findViewById(R.id.sms_view);
        mRecommenderView = findViewById(R.id.recommender_view);
        /**
         *  Button对象
         */
        mNewRegisterGetvalicode = (Button) findViewById(R.id.new_register_getvalicode);
        mNewRegisterBtn = (Button) findViewById(R.id.new_register_btn);
        /**
         *  CheckBox对象
         */
        mNewAgreeProtocol = (CheckBox) findViewById(R.id.new_agree_protocol);
        /**
         *  TextView对象
         */
        mNewProtocolContent = (TextView) findViewById(R.id.new_protocol_content);
        /**
         *  ImageView对象
         */
        mBackImageView = (ImageView)findViewById(R.id.back_imageview);
    }
    /**
     *  内部类 --EtTouchListener
     */
    protected class EtTouchListener implements View.OnTouchListener{

        private EditText editText;
        int singal;
        int flag_count = 0;
        public EtTouchListener(EditText editText,int singal){

            this.editText = editText;
            this.singal = singal;
        }
        @Override
        public boolean onTouch(View v, MotionEvent event){

            flag_count++;
            if(flag_count % 2 == 0){

                flag = this.singal;
                switchBg(flag);

                this.editText.setFocusable(true);
                this.editText.setFocusableInTouchMode(true);
                this.editText.requestFocus();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(INPUT_METHOD_SERVICE);
                imm.showSoftInput(this.editText,InputMethodManager.SHOW_FORCED);
                return true;
            }
            return false;
        }
    }
    /**
     *  内部类 ImageViewTouchListener
     */
    protected class ImageViewTouchListener implements View.OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event){

            if(event.getAction() == MotionEvent.ACTION_DOWN){

                //mBackRbLogin.setBackgroundResource(R.drawable.abc_back_unselected);
                //mBackRbLogin.setButtonDrawable(R.drawable.abc_back_unselected);
                mBackImageView.setImageResource(R.drawable.abc_back_unselected);
                return true;
            }else if(event.getAction() == MotionEvent.ACTION_UP){

                //mBackRbLogin.setBackgroundResource(R.drawable.abc_back_selected);
                //mBackRbLogin.setButtonDrawable(R.drawable.abc_back_selected);
                mBackImageView.setImageResource(R.drawable.abc_back_selected);
                return true;
            }
            return false;
        }
    }
    /**
     *  LinearLayout对象
     */
    private LinearLayout mSubLlayout;
    private LinearLayout mMainLlayout;
    /**
     *  RelativeLayout对象
     */
    private RelativeLayout mNewRegisterBackRliv;
    private RelativeLayout mNewLoginBackRltv;
    /**
     *  RadioButton对象
     */
    private RadioButton mPhoneRb;
    private RadioButton mPwdRb;
    private RadioButton mEpwdRb;
    private RadioButton mSmsRb;
    private RadioButton mRecommenderRb;
    /**
     *  EditText对象
     */
    private EditText mNewPhoneEt;
    private EditText mNewRegisterpwdEta;
    private EditText mNewRegisterpwdEtb;
    private EditText mNewRegistersmsEt;
    private EditText mNewRegisterrcdEt;
    /**
     *  View对象
     */
    private View mPhoneView;
    private View mPwdView;
    private View mEpwdView;
    private View mSmsView;
    private View mRecommenderView;
    /**
     *  Button对象
     */
    private Button mNewRegisterGetvalicode;
    private Button mNewRegisterBtn;
    /**
     *  CheckBox对象
     */
    private CheckBox mNewAgreeProtocol;
    /**
     *  TextView对象
     */
    private TextView mNewProtocolContent;
    /**
     *  ImageView
     */
    private ImageView mBackImageView;
    /**
     *  常量
     */
    private final int PHONE = 0x1010;
    private final int PWD = 0x1020;
    private final int EPWD = 0x1030;
    private final int SMS = 0x1040;
    private final int RECOMMENDER = 0x1050;
    /**
     *  标志位
     */
    private int flag = PHONE;
}
