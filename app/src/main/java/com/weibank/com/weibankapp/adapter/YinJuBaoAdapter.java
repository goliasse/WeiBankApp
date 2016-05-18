package com.weibank.com.weibankapp.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weibank.com.weibankapp.R;

import java.util.LinkedList;

/**
 * Created by Administrator on 2016/1/19.
 */
public class YinJuBaoAdapter extends BaseAdapter{

    public YinJuBaoAdapter(Context context, LinkedList<String> textOne,
                           LinkedList<String> textTwo, LinkedList<String> textThree,
                           LinkedList<String> textFour, LinkedList<String> textFive,
                           LinkedList<String> textSix, LinkedList<Integer> images){

        super();
        this.textOne = textOne;
        this.textTwo = textTwo;
        this.textThree = textThree;
        this.textFour = textFour;
        this.textFive = textFive;
        this.textSix = textSix;
        this.images = images;
        this.context = context;

        inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public Object getItem(int position){
        return images.get(position);
    }
    @Override
    public int getCount(){
        return images.size();
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        YinJuBaoViewHolder holder;
        if(convertView == null){

            holder = new YinJuBaoViewHolder();
            convertView = inflater.inflate(R.layout.yinjubao_listview_item,null);
            /**
             * TextView对象
             */
            holder.mRate = (TextView) convertView.findViewById(R.id.rate);
            holder.mYuqiRate = (TextView) convertView.findViewById(R.id.yuqi_rate);
            holder.mYjbTerm = (TextView) convertView.findViewById(R.id.yjb_term);
            holder.mYjbSongqian = (TextView) convertView.findViewById(R.id.yjb_songqian);
            holder.mRenchouzong = (TextView) convertView.findViewById(R.id.renchouzong);
            holder.mHot = (TextView) convertView.findViewById(R.id.hot);
            /**
             * RelativeLayout对象
             */
            holder.mYjbXsBg = (RelativeLayout) convertView.findViewById(R.id.yjb_xs_bg);
            convertView.setTag(holder);
        }else{

            holder = (YinJuBaoViewHolder)convertView.getTag();
        }

//        holder.mRate.setText(textOne[position]);
//        holder.mYuqiRate.setText(textTwo[position]);
//        holder.mYjbTerm.setText(textThree[position]);
//        holder.mYjbSongqian.setText(textFour[position]);
//        holder.mRenchouzong.setText(textFive[position]);
//        holder.mHot.setText(textSix[position]);
//        holder.mYjbXsBg.setBackgroundResource(images[position]);

        holder.mRate.setText(textOne.get(position));
        holder.mRate.setTag(position);
        holder.mYuqiRate.setText(textTwo.get(position));
        holder.mYjbTerm.setText(textThree.get(position));
        holder.mYjbSongqian.setText(textFour.get(position));
        holder.mRenchouzong.setText(textFive.get(position));
        holder.mHot.setText(textSix.get(position));
        holder.mYjbXsBg.setBackgroundResource(images.get(position));

        return convertView;
    }
    /**
     * 内部类---ViewHolder对象
     */
    class YinJuBaoViewHolder{
        /**
         * TextView对象
         */
        protected TextView mRate;
        protected TextView mYuqiRate;
        protected TextView mYjbTerm;
        protected TextView mYjbSongqian;
        protected TextView mRenchouzong;
        protected TextView mHot;
        /**
         * RelativeLayout对象
         */
        protected RelativeLayout mYjbXsBg;
    }
    /**
     * Context对象
     */
    private Context context;
    /**
     * String数组
     */
//    private String[] textOne;
//    private String[] textTwo;
//    private String[] textThree;
//    private String[] textFour;
//    private String[] textFive;
//    private String[] textSix;
    /**
     * int数组
     */
//    private int[] images;
    /**
     * LayoutInflater对象
     */
    private LayoutInflater inflater;

    private LinkedList<String> textOne;
    private LinkedList<String> textTwo;
    private LinkedList<String> textThree;
    private LinkedList<String> textFour;
    private LinkedList<String> textFive;
    private LinkedList<String> textSix;
    private LinkedList<Integer> images;
}
