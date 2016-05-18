package com.weibank.com.weibankapp.utils;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.weibank.com.weibankapp.R;

/**
 * Created by Administrator on 2016/4/14.
 */
public class XRecylerViewHelper{
    /**
     *  处理方法
     */
    public void handleXRecylerViewEvent(Context context,XRecyclerView xRecyclerView,
                                         final XRecyclerView.LoadingListener loadingListener){
        initXRecylerView(context, xRecyclerView, loadingListener);
    }
    /**
     *  初始化方法
     */
    private void  initXRecylerView(Context context,XRecyclerView xRecyclerView,
                                    final XRecyclerView.LoadingListener loadingListener){

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);

        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallGridPulse);
        //mRecyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);

        xRecyclerView.setLoadingListener(loadingListener);
    }
    private XRecylerViewHelper(){}
    /**
     *  单例模式
     */
    public static XRecylerViewHelper getInstance(){

        if(instance == null){
            synchronized(XRecylerViewHelper.class){
                if(instance == null){

                    instance = new XRecylerViewHelper();
                }
            }
        }
        return instance;
    }
    /**
     *  XRecylerViewHelper对象
     */
    private volatile static XRecylerViewHelper instance;
}
