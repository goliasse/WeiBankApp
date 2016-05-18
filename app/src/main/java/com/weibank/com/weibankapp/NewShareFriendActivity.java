package com.weibank.com.weibankapp;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.weibank.com.weibankapp.adapter.WoGridViewAdapter;
import com.weibank.com.weibankapp.utils.QRCodeUtil;
import com.weibank.com.weibankapp.views.DefineGridView;

public class NewShareFriendActivity extends UEBaseCompatActivity
                                     implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_share_friend);
        /**
         * 调用初始化对象方法
         */
        assignViews();
        mErweimaIv.setImageBitmap(QRCodeUtil.createImageNoLogo(CONTENT, 245, 245));
        Animation scale = AnimationUtils.loadAnimation(this,R.anim.erweima_image_scale);
        mErweimaIv.startAnimation(scale);

        mGridView.setAdapter(new WoGridViewAdapter(this,
                images,
                getResources().getStringArray(R.array.texts_sharefriends),
                resourceId));
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.topLayout:

                NewShareFriendActivity.this.finish();
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
        mTopLayout = (RelativeLayout) findViewById(R.id.topLayout);
        mTopLayout.setOnClickListener(this);
        /**
         *  ImageView对象
         */
        mErweimaIv = (ImageView) findViewById(R.id.erweima_iv);
        /**
         *  GridView对象
         */
        mGridView = (DefineGridView) findViewById(R.id.gridView);
    }

    /**
     * RelativeLayout对象
     */
    private RelativeLayout mTopLayout;
    /**
     * ImageView对象
     */
    private ImageView mErweimaIv;
    /**
     * GridView对象
     */
    private DefineGridView mGridView;

    /////////////////////////////////////////////////////////////////////////////////
    /**
     *  图片数组
     */
    private int[] images = {
      R.mipmap.new_weibo_sharefriends,
      R.mipmap.new_qq_sharefriends,
      R.mipmap.new_weixin_sharefriends,
      R.mipmap.new_weixinpyq_sharefriends,
      R.mipmap.new_qqzone_sharefriends,
      R.mipmap.new_sms_sharefriends,
    };
    /**
     *  String数组
     */
    private String[] texts = {
       "新浪微博",
       "腾讯QQ",
       "QQ空间",
       "微信好友",
       "朋友圈",
       "短信",
    };
    /**
     *  资源id
     */
    private int resourceId = R.layout.sharefriend_gridview_item;
    /**
     * 二维码内容
     */
    private final String CONTENT = "http://www.weibank.cn/toLogin";
}
