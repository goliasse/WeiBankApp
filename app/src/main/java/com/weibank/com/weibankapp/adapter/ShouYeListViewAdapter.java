package com.weibank.com.weibankapp.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.weibank.com.weibankapp.R;
import com.weibank.com.weibankapp.beans.MainProjectBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/13.
 */
public class ShouYeListViewAdapter extends BaseAdapter{

    public ShouYeListViewAdapter(Context context,int[] imgbgs,String[] texts){

        super();
        inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imgbgs = imgbgs;
        this.texts = texts;
    }
    public ShouYeListViewAdapter(Context context,ArrayList<MainProjectBean> beans){

        super();
        inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.beans = beans;

    }
    @Override
    public int getCount(){
        //return imgbgs.length;
        return beans.size();
    }
    @Override
    public Object getItem(int position){

//        return imgbgs[position];
        return beans.get(position);
    }
    @Override
    public long getItemId(int position){

        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ShouYeListViewHolder viewHolder;
        if(convertView == null){

            viewHolder = new ShouYeListViewHolder();
//            convertView = inflater.inflate(R.layout.shouye_listview_item,null);
//            viewHolder.mRelativeLayout = (RelativeLayout)
//                       convertView.findViewById(R.id.shouye_listview_bg);
//            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.touzhi_shouyi);

            convertView = inflater.inflate(R.layout.pro_listview_item,null);
            viewHolder.mainImg = (ImageView) convertView.findViewById(R.id.iva);
            viewHolder.viceImg1 = (ImageView) convertView.findViewById(R.id.ivb);
            viewHolder.viceImg2 = (ImageView) convertView.findViewById(R.id.ivc);
            viewHolder.viceImg3 = (ImageView) convertView.findViewById(R.id.ivd);
            viewHolder.yearRate = (TextView) convertView.findViewById(R.id.tva);
        }else{

            viewHolder = (ShouYeListViewHolder)convertView.getTag();
        }

//        viewHolder.mRelativeLayout.setBackgroundResource(imgbgs[position]);
//        viewHolder.mTextView.setText(texts[position]);
        MainProjectBean bean = beans.get(position);
        viewHolder.mainImg.setImageResource(bean.getMainImg());
        viewHolder.viceImg1.setImageResource(bean.getViceImg1());
        viewHolder.viceImg2.setImageResource(bean.getViceImg2());
        viewHolder.viceImg3.setImageResource(bean.getViceImg3());
        viewHolder.yearRate.setText(bean.getmYearRate());
        return convertView;
    }
    /**
     * 内部类---ViewHolder
     */
    class ShouYeListViewHolder{

        protected RelativeLayout mRelativeLayout;
        protected TextView mTextView;

        protected ImageView mainImg;
        protected ImageView viceImg1;
        protected ImageView viceImg2;
        protected ImageView viceImg3;
        protected TextView yearRate;
    }
    /**
     * 图片数组
     */
    private int[] imgbgs ;
    /**
     * 文本数组
     */
    private String[] texts;
    /**
     * LayoutInflater对象
     */
    private LayoutInflater inflater;

    /**
     * 存储MainProjectBean对象列表
     */
    private ArrayList<MainProjectBean> beans;
}
