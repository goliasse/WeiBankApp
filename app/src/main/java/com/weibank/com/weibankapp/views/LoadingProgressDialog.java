package com.weibank.com.weibankapp.views;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.weibank.com.weibankapp.R;

/**
 * Created by Administrator on 2016/1/20.
 */
public class LoadingProgressDialog extends ProgressDialog{

    public LoadingProgressDialog(Context context,int theme,String hint,int resId){

        super(context,theme);
        this.hint = hint;
        this.resId = resId;
        flag = true;
        /**
         * 调用初始化对象方法
         */
        assignViews();
        /**
         * 调用初始化数据方法
         */
        initData(flag);
        setCanceledOnTouchOutside(false);
    }

    public LoadingProgressDialog(Context context,int theme,int resId){

        super(context,theme);
        this.resId = resId;
        flag = false;
        setCanceledOnTouchOutside(false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        /**
         * 调用初始化对象方法
         */
        assignViews();
        /**
         * 调用初始化数据方法
         */
        initData(flag);
    }
    /**
     * 初始化对象方法
     */
    private void assignViews(){

        setContentView(R.layout.loadingprogressbar);
        mImgloading = (ImageView) findViewById(R.id.imgloading);
        mTextloading = (TextView) findViewById(R.id.textloading);
    }
    /**
     * 初始化数据方法
     */
    private void initData(boolean flag){

        mImgloading.setBackgroundResource(resId);
        animationDrawable = (AnimationDrawable)mImgloading.getBackground();
        mImgloading.post(new Runnable() {
            @Override
            public void run() {
                animationDrawable.start();
            }
        });
        if(flag){

            mTextloading.setText(hint);
        }
    }
    /**
     * ImageView对象
     */
    private ImageView mImgloading;
    /**
     * TextView对象
     */
    private TextView mTextloading;
    /**
     * AnimationDrawable对象
     */
    private AnimationDrawable animationDrawable;
    /**
     * 提示文本
     */
    private String hint;
    /**
     * 资源id
     */
    private int resId;
    /**
     * 标志位----是否设置文本
     */
    private boolean flag = false;
}
