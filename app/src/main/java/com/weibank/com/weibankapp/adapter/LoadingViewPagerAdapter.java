package com.weibank.com.weibankapp.adapter;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.weibank.com.weibankapp.MainActivity;
import com.weibank.com.weibankapp.utils.NormalMethods;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/13.
 */
public class LoadingViewPagerAdapter extends PagerAdapter
                                      implements ViewPager.OnPageChangeListener{

    public LoadingViewPagerAdapter(Activity activity,ArrayList<View> lists){

        super();
        this.lists = lists;
        this.activity = activity;
    }
    @Override
    public int getCount() {

        return lists.size();
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){

        //super.destroyItem(container, position, object);
        container.removeView(lists.get(position));
    }
    @Override
    public boolean isViewFromObject(View view, Object object){

        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position){

        View view = lists.get(position);
        container.addView(view);
        return view;
    }
    @Override
    public void onPageSelected(int position){
        curIndex = position;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels){
    }
    @Override
    public void onPageScrollStateChanged(int state){}
    /**
     * 返回当前页面的索引值方法
     */
    public int currentPageIndex(){

        return curIndex;
    }
    /**
     * 重写onTouch方法
     */
    /**
     * 存储View对象列表
     */
    private ArrayList<View> lists;
    /**
     * 标志位---记录当前页面的索引值
     */
    private int curIndex = 0;
    /**
     * Context对象
     */
    private Activity activity;
}
