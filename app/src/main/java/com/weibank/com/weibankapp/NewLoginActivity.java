package com.weibank.com.weibankapp;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.weibank.com.weibankapp.utils.EtActionModeCallback;

public class NewLoginActivity extends EBaseCompatActivity
                               implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams
                   .SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_new_loginx);
        /**
         * 调用初始化对象方法
         */
        assignViews();

//        mNewPhoneEt.setCustomSelectionActionModeCallback(new ActionMode.Callback(){
//            @Override
//            public boolean onPrepareActionMode(ActionMode mode, Menu menu){
//                return false;
//            }
//            @Override
//            public void onDestroyActionMode(ActionMode mode){}
//            @Override
//            public boolean onCreateActionMode(ActionMode mode, Menu menu){
//                return false;
//            }
//            @Override
//            public boolean onActionItemClicked(ActionMode mode, MenuItem item){
//                return false;
//            }
//        });
        mNewPhoneEt.setCustomSelectionActionModeCallback(new EtActionModeCallback());
        mNewPhoneEt.setOnTouchListener(new EtTouchListener(mNewPhoneEt,UP));
//        mNewPhoneEt.setOnTouchListener(new View.OnTouchListener(){
//
//            int flag_count = 0;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event){
//
//                flag_count++;
//                if(flag_count % 2 == 0){
//
//                    flag = UP;
//                    switchBg(flag);
//
//                    mNewPhoneEt.setFocusable(true);
//                    mNewPhoneEt.setFocusableInTouchMode(true);
//                    mNewPhoneEt.requestFocus();
//
//                    InputMethodManager imm = (InputMethodManager)
//                                              getSystemService(INPUT_METHOD_SERVICE);
//                    imm.showSoftInput(mNewPhoneEt, InputMethodManager.SHOW_FORCED);
//                    return true;
//                }
//                return false;
//            }
//        });

//        mNewPwdEt.setCustomSelectionActionModeCallback(new ActionMode.Callback(){
//            @Override
//            public boolean onPrepareActionMode(ActionMode mode, Menu menu){
//                return false;
//            }
//            @Override
//            public void onDestroyActionMode(ActionMode mode){}
//            @Override
//            public boolean onCreateActionMode(ActionMode mode, Menu menu){
//                return false;
//            }
//            @Override
//            public boolean onActionItemClicked(ActionMode mode, MenuItem item){
//                return false;
//            }
//        });

        mNewPwdEt.setCustomSelectionActionModeCallback(new EtActionModeCallback());
        mNewPwdEt.setOnTouchListener(new EtTouchListener(mNewPwdEt,DOWN));
//        mNewPwdEt.setOnTouchListener(new View.OnTouchListener(){
//
//            int flag_count = 0;
//            @Override
//            public boolean onTouch(View v, MotionEvent event){
//
//                flag_count++;
//                if(flag_count % 2 == 0){
//
//                    flag = DOWN;
//                    switchBg(flag);
//
//                    mNewPwdEt.setFocusable(true);
//                    mNewPwdEt.setFocusableInTouchMode(true);
//                    mNewPwdEt.requestFocus();
//
//                    InputMethodManager imm = (InputMethodManager)
//                                              getSystemService(INPUT_METHOD_SERVICE);
//                    imm.showSoftInput(mNewPhoneEt,InputMethodManager.SHOW_FORCED);
//                    return true;
//                }
//                return false;
//            }
//        });
        mNewLoginBackRliv.setOnTouchListener(new RadioButtonTouchListener());
//        mNewLoginBackRliv.setOnTouchListener(new View.OnTouchListener(){
//            @Override
//            public boolean onTouch(View v, MotionEvent event){
//
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//
//                    mBackRbLogin.setBackgroundResource(R.drawable.abc_back_unselected);
//                    //mBackRbLogin.setButtonDrawable(R.drawable.abc_back_unselected);
//                    return true;
//                }else if(event.getAction() == MotionEvent.ACTION_UP){
//
//                    mBackRbLogin.setBackgroundResource(R.drawable.abc_back_selected);
//                    //mBackRbLogin.setButtonDrawable(R.drawable.abc_back_selected);
//                    return true;
//                }
//                return false;
//            }
//        });
    }
    @Override
    public void onClick(View v){


    }
    /**
     *  改变颜色方法
     */
    private void switchBg(int number){
        switch(number){
            case UP:

                mRbone.setChecked(true);
                mView1.setBackgroundColor(getResources().getColor(R.color.pgreen));

                mRbtwo.setChecked(false);
                mView2.setBackgroundColor(getResources().getColor(R.color.pgray));
                break;
            case DOWN:

                mRbtwo.setChecked(true);
                mView2.setBackgroundColor(getResources().getColor(R.color.pgreen));

                mRbone.setChecked(false);
                mView1.setBackgroundColor(getResources().getColor(R.color.pgray));
                break;
        }
    }
    /**
     *  初始化对象方法
     */
    private void assignViews(){
        /**
         *  RelativeLayout对象
         */
        mChildLayout = (RelativeLayout) findViewById(R.id.childLayout);
        mNewLoginBackRliv = (RelativeLayout) findViewById(R.id.new_login_back_rliv);
        mNewLoginRegisterRltv = (RelativeLayout) findViewById(R.id.new_login_register_rltv);
        /**
         *  LinearLayout对象
         */
        mParentLayout = (LinearLayout) findViewById(R.id.parentLayout);
        /**
         *  EditText对象
         */
        mNewPhoneEt = (EditText) findViewById(R.id.new_phone_et);
        mNewPwdEt = (EditText) findViewById(R.id.new_pwd_et);
        /**
         *  View对象
         */
        mView1 = (RadioButton)findViewById(R.id.view1);
        mView2 = (RadioButton)findViewById(R.id.view2);
        /**
         *  RadioButton对象
         */
        mRbone = (RadioButton)findViewById(R.id.rbone);
        mRbtwo = (RadioButton)findViewById(R.id.rbtwo);
        //mBackRbLogin = (RadioButton)findViewById(R.id.back_rb_login);
        /**
         *  Button对象
         */
        mNewLoginBtn = (Button) findViewById(R.id.new_login_btn);
        /**
         *  ImageView对象
         */
        mBackImageView = (ImageView) findViewById(R.id.back_imageview);
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
     *  内部类 RadioButtonTouchListener
     */
    protected class RadioButtonTouchListener implements View.OnTouchListener{
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
     *  常量
     */
    private final int  UP = 0x1010;
    private final int DOWN = 0x1020;
    /**
     *  标志位
     */
    private int flag = UP;
    /**
     *  RelativeLayout对象
     */
    private RelativeLayout mChildLayout;
    private RelativeLayout mNewLoginBackRliv;
    private RelativeLayout mNewLoginRegisterRltv;
    /**
     *  LinearLayout对象
     */
    private LinearLayout mParentLayout;
    /**
     *  EditText对象
     */
    private EditText mNewPhoneEt;
    private EditText mNewPwdEt;
    /**
     *  RadioButton对象
     */
    private RadioButton mRbone;
    private RadioButton mRbtwo;
    private RadioButton mBackRbLogin;
    /**
     *  View对象
     */
    private RadioButton mView1;
    private RadioButton mView2;
    /**
     *  Button对象
     */
    private Button mNewLoginBtn;
    /**
     *  ImageView对象
     */
    private ImageView mBackImageView;
}
