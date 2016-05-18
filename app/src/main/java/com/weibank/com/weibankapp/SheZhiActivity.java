package com.weibank.com.weibankapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.utils.AnimationMethods;
import com.weibank.com.weibankapp.utils.NormalMethods;

public class SheZhiActivity extends BaseActivity
                             implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);
        /**
         * 调用初始化方法
         */
        assignViews();
        //AnimationMethods.setupWindowSlide(this);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            //客户服务
            case R.id.shezhi_client_service:
                NormalMethods.exitAndSwicthActivity(SheZhiActivity.this,
                                                    ClientServiceActivity.class,false);
                //AnimationMethods.startToActivity(SheZhiActivity.this,ClientServiceActivity.class);
                break;
            //退出
            case R.id.shezhi_exit:

                NormalMethods.exitAndSwicthActivity(SheZhiActivity.this,
                                                    UserLoginActivity.class,true);
                break;
//            //跳转到支付页面
//            case R.id.shezhi_zifei:
//
//                NormalMethods.exitAndSwicthActivity(SheZhiActivity.this,
//                                                    ZhiFuActivity.class,false);
//
//                //NormalMethods.exitAndSwicthActivity(SheZhiActivity.this,
//                //                                    TestCodeActivity.class,false);
//                break;
//            case R.id.shezhi_saoyisao:
//                Intent intent = new Intent();
//                intent.setClass(SheZhiActivity.this, MipcaActivityCapture.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivityForResult(intent, Constants.SCANNIN_GREQUEST_CODE);
//                break;
            //修改密码
            case R.id.shezhi_update_pwd:

                NormalMethods.exitAndSwicthActivity(SheZhiActivity.this,
                                                    UpdatePwdActivity.class,false);
                break;
            //关于我们
            case R.id.shezhi_aboutus:

                break;
            //版本更新
            case R.id.shezhi_version_update:

                NormalMethods.exitAndSwicthActivity(this,VersionUpdateActivity.class,false);
                 break;
            //资费说明
            case R.id.shezhi_zhifei_shuoming:

                break;
        }
    }
    /**
     * 初始化对象方法
     */
//    private void assignViews(){
//        /**
//         * RelativeLayout对象
//         */
//        mRlClientservice = (RelativeLayout) findViewById(R.id.rl_clientservice);
//        mRlClientservice.setOnClickListener(this);
//
//        mRlExit = (RelativeLayout) findViewById(R.id.rl_exit);
//        mRlExit.setOnClickListener(this);
//
//        mShezhiZifei = (RelativeLayout) findViewById(R.id.shezhi_zifei);
//        mShezhiZifei.setOnClickListener(this);
//
//        mShezhiSaoyisao = (RelativeLayout) findViewById(R.id.shezhi_saoyisao);
//        mShezhiSaoyisao.setOnClickListener(this);
//
//        mUpdatePwd = (RelativeLayout) findViewById(R.id.update_pwd);
//        mUpdatePwd.setOnClickListener(this);
//        /**
//         * TextView对象
//         */
//        mTopviewText = (TextView) findViewById(R.id.topview_text);
//        mTopviewText.setText("设置");
//        /**
//         * RelativeLayout对象
//         */
//        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
//        mRlTopview.setVisibility(View.GONE);
//
//    }
    /**
     * RelativeLayout对象
     */
    private RelativeLayout mShezhiVersionUpdate;
    private RelativeLayout mShezhiUpdatePwd;
    private RelativeLayout mShezhiAboutus;
    private RelativeLayout mShezhiZhifeiShuoming;
    private RelativeLayout mShezhiClientService;
    private RelativeLayout mShezhiExit;
    private RelativeLayout mRlTopview;
    /**
     *  初始化对象方法
     */
    private void assignViews(){

        mShezhiVersionUpdate = (RelativeLayout) findViewById(R.id.shezhi_version_update);
        mShezhiVersionUpdate.setOnClickListener(this);

        mShezhiUpdatePwd = (RelativeLayout) findViewById(R.id.shezhi_update_pwd);
        mShezhiUpdatePwd.setOnClickListener(this);

        mShezhiAboutus = (RelativeLayout) findViewById(R.id.shezhi_aboutus);
        mShezhiAboutus.setOnClickListener(this);

        mShezhiZhifeiShuoming = (RelativeLayout) findViewById(R.id.shezhi_zhifei_shuoming);
        mShezhiZhifeiShuoming.setOnClickListener(this);

        mShezhiClientService = (RelativeLayout) findViewById(R.id.shezhi_client_service);
        mShezhiClientService.setOnClickListener(this);

        mShezhiExit = (RelativeLayout) findViewById(R.id.shezhi_exit);
        mShezhiExit.setOnClickListener(this);

        /**
         * TextView对象
         */
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("设置");
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setVisibility(View.GONE);
    }

    /**
     * TextView对象
     */
    private TextView mTopviewText;

}
