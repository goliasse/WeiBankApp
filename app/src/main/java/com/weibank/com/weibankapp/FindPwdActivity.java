package com.weibank.com.weibankapp;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FindPwdActivity extends UEBaseCompatActivity
                              implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pwd);
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
            //返回上一页面方法
            case R.id.rl_topview:
                FindPwdActivity.this.finish();
                break;
        }
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){
        /**
         * EditText对象
         */
        mFindpwdPhone = (EditText) findViewById(R.id.findpwd_phone);
        mFindpwdPwd = (EditText) findViewById(R.id.findpwd_pwd);
        mFindpwdPwda = (EditText) findViewById(R.id.findpwd_pwda);
        mFindpwdValicode = (EditText) findViewById(R.id.findpwd_valicode);
        /**
         * Button对象
         */
        mFindpwdGetvalicode = (Button) findViewById(R.id.findpwd_getvalicode);
        /**
         * TextView对象
         */
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("找回密码");
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout)findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
    }
    /**
     * EditText对象
     */
    private EditText mFindpwdPhone;
    private EditText mFindpwdPwd;
    private EditText mFindpwdPwda;
    private EditText mFindpwdValicode;
    /**
     * Button对象
     */
    private Button mFindpwdGetvalicode;
    /**
     * TextView对象
     */
    private TextView mTopviewText;
    /**
     * RelativeLayout对象
     */
    private RelativeLayout mRlTopview;
}
