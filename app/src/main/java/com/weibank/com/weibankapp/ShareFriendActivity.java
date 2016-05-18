package com.weibank.com.weibankapp;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weibank.com.weibankapp.utils.NormalMethods;
import com.weibank.com.weibankapp.utils.QRCodeUtil;

public class ShareFriendActivity extends UEBaseCompatActivity
                                  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_friend);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        /**
         * 调用初始化对象方法
         */
        assignViews();
        /**
         * 调用生成二维码方法
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.shezhi_phone);
        Bitmap erWeiMa = QRCodeUtil.createImage(CONTENT, 180, 180, bitmap);
        if(erWeiMa != null){

            mErweima.setImageBitmap(erWeiMa);
        }
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            //返回到上一界面
            case R.id.rl_topview:
                ShareFriendActivity.this.finish();
                break;
        }
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){
        /**
         * ImageView对象
         */
        mErweima = (ImageView) findViewById(R.id.erweima);
        mUserloginIconone = (ImageView) findViewById(R.id.userlogin_iconone);
        mUserloginIcontwo = (ImageView) findViewById(R.id.userlogin_icontwo);
        /**
         * RelativeLayout对象
         */
        mRlTopview = (RelativeLayout) findViewById(R.id.rl_topview);
        mRlTopview.setOnClickListener(this);
        /**
         * TextView对象
         */
        mTopviewText = (TextView) findViewById(R.id.topview_text);
        mTopviewText.setText("分享好友");
    }
    /**
     * ImageView对象
     */
    private ImageView mErweima;
    private ImageView mUserloginIconone;
    private ImageView mUserloginIcontwo;
    /**
     * RelativeLayout对象
     */
    private RelativeLayout mRlTopview;
    /**
     * TextView对象
     */
    private TextView mTopviewText;
    /**
     * 二维码内容
     */
    private final String CONTENT = "http://www.weibank.cn/toLogin";
}
