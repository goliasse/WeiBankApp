package com.weibank.com.weibankapp.views;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.weibank.com.weibankapp.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/1/20.
 */
public class ImagePlayingRecyler extends FrameLayout{


    //轮播图图片数量
    public final int IMAGE_COUNT = 5;
    //自动轮播的时间间隔
    public final int TIME_INTERVAL = 5;
    //自动轮播启用开关
    private final static boolean isAutoPlay = true;
    //自定义轮播图的资源ID
    private int[] imagesResIds;
    //放轮播图片的ImageView 的list
    private  List<ImageView> imageViewsList;
    //放圆点的View的list
    private List<View> dotViewsList;
    private ViewPager viewPager;
    //当前轮播页
    private int currentItem = 0;
    //定时任务
    private ScheduledExecutorService scheduledExecutorService;
    //Handler
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){

            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem);
        }
    };

    public ImagePlayingRecyler(Context context){

        this(context, null);
        initData();
        initUI(context);
        if(isAutoPlay){

            startPlay();
        }
    }

    public ImagePlayingRecyler(Context context, AttributeSet attrs){

        this(context, attrs, 0);
        initData();
        initUI(context);
        if(isAutoPlay){

            startPlay();
        }
    }

    public ImagePlayingRecyler(Context context, AttributeSet attrs, int defStyle){

        super(context, attrs, defStyle);
        initData();
        initUI(context);
        if(isAutoPlay){

            startPlay();
        }
    }
    /**
     * 开始轮播图切换
     */
    private void startPlay() {

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
    }
    /**
     * 停止轮播图切换
     */
    private void stopPlay() {

        scheduledExecutorService.shutdown();
    }
    /**
     * 初始化相关Data
     */
    private void initData() {
        imagesResIds = new int[]{
                R.mipmap.adone,
                R.mipmap.adtwo,
                R.mipmap.adthree,
                R.mipmap.adone,
                R.mipmap.adthree,
        };
        imageViewsList = new ArrayList<>();
        dotViewsList = new ArrayList<>();
    }
    /**
     * 初始化Views等UI
     */
    private void initUI(Context context) {

        LayoutInflater.from(context).inflate(R.layout.image_playing_model, this, true);
        for (int imageID : imagesResIds) {

            ImageView view = new ImageView(context);
            view.setImageResource(imageID);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewsList.add(view);
        }

        dotViewsList.add(findViewById(R.id.v_dot1));
        dotViewsList.add(findViewById(R.id.v_dot2));
        dotViewsList.add(findViewById(R.id.v_dot3));
        dotViewsList.add(findViewById(R.id.v_dot4));
        dotViewsList.add(findViewById(R.id.v_dot5));

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setFocusable(true);
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }
    /**
     * 填充ViewPager的页面适配器
     */
    class MyPagerAdapter extends PagerAdapter {
        @Override
        public void destroyItem(View container, int position, Object object) {

            //((ViewPag.er)container).removeView((View)object);
            ((ViewPager) container).removeView(imageViewsList.get(position));
        }
        @Override
        public Object instantiateItem(View container, int position) {

            ((ViewPager) container).addView(imageViewsList.get(position));
            return imageViewsList.get(position);
        }
        @Override
        public int getCount() {

            return imageViewsList.size();
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }
    }
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        boolean isAutoPlay = false;
        @Override
        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case 1:// 手势滑动，空闲中

                    isAutoPlay = false;
                    break;
                case 2:// 界面切换中

                    isAutoPlay = true;
                    break;
                case 0:
                    // 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1
                            && !isAutoPlay) {

                        viewPager.setCurrentItem(0);
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                    }
                    break;
            }
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {}
        @Override
        public void onPageSelected(int pos) {

            currentItem = pos;
            for(int i=0;i < dotViewsList.size();i++){

                if(i == pos){

                    ((View)dotViewsList.get(pos)).setBackgroundResource(R.mipmap.page_indicator_focused);
                }else {

                    ((View)dotViewsList.get(i)).setBackgroundResource(R.mipmap.page_indicator_unfocused);
                }
            }
        }
    }

    private class SlideShowTask implements Runnable{
        @Override
        public void run() {

            synchronized (viewPager) {

                currentItem = (currentItem+1)%imageViewsList.size();

                handler.obtainMessage().sendToTarget();
            }
        }
    }

    private void destoryBitmaps(){
        for(int i = 0; i < IMAGE_COUNT; i++){

            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if(drawable != null){

                //解除drawable对view的引用
                drawable.setCallback(null);
            }
        }
    }
}
