package com.weibank.com.weibankapp;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.viewpagerindicator.CirclePageIndicator;
import com.weibank.com.weibankapp.adapter.LoadingViewPagerAdapter;
import com.weibank.com.weibankapp.utils.NormalMethods;

import java.util.ArrayList;

public class LoadingActivity extends EBaseCompatActivity
                              implements GestureDetector.OnGestureListener{
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        /**
         * 调用初始化方法
         */
        assignViews();
        detector = new GestureDetector(this);
        viewPagerAdapter = new LoadingViewPagerAdapter(this,getViews());
        mViewpager.setAdapter(viewPagerAdapter);
        mIndicator.setViewPager(mViewpager);

        mEnter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                startActivity(new Intent(LoadingActivity.this,MainActivity.class));
                LoadingActivity.this.finish();
            }
        });
    }
    /**
     * 重写onKeyDown方法
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        //防止用户退出
        if(keyCode == KeyEvent.KEYCODE_BACK){

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 重写onTouchEvent方法
     */
    @Override
    public boolean onTouchEvent(MotionEvent event){
        return detector.onTouchEvent(event);
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2,
                            float velocityX, float velocityY){
        if(e2.getX() - e1.getX() >= 25
                && viewPagerAdapter.currentPageIndex() == lists.size() - 1){

            NormalMethods.exitAndSwicthActivity(this,UserLoginActivity.class,true);
            return true;
        }
        return false;
    }
    @Override
    public boolean onDown(MotionEvent e){
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e){}
    @Override
    public boolean onSingleTapUp(MotionEvent e){
        return false;
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                             float distanceX, float distanceY){
        return false;
    }
    @Override
    public void onLongPress(MotionEvent e){}
    /**
     * 初始化方法
     */
    private void assignViews(){

        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
    }
    /**
     * 获取存储View对象列表
     */
    private ArrayList<View> getViews(){

        lists = new ArrayList<>();
        lists.add(inflater.inflate(R.layout.loadingone,null));
        lists.add(inflater.inflate(R.layout.loadingtwo,null));
        lists.add(inflater.inflate(R.layout.loadingthree,null));

        View mView = inflater.inflate(R.layout.loadingfour, null);
        lists.add(mView);

        mEnter = (Button) mView.findViewById(R.id.enter);
        return lists;
    }
    /**
     * ViewPager对象
     */
    private ViewPager mViewpager;
    /**
     * CirclePageIndicator对象
     */
    private CirclePageIndicator mIndicator;
    /**
     * LayoutInflater对象
     */
    private LayoutInflater inflater;
    /**
     * View对象
     */
    private View view;
    /**
     * Button对象
     */
    private Button mEnter;
    /**
     * 存储View对象列表
     */
    private ArrayList<View> lists;
    /**
     * 记录x轴位置的变量
     */
    private int lastX = 0;
    /**
     * LoadingViewPagerAdapter对象
     */
    private LoadingViewPagerAdapter viewPagerAdapter;
    /**
     * GestureDetector对象
     */
    private GestureDetector detector;
}
