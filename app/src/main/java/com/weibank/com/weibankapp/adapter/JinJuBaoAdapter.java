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
 * Created by Administrator on 2016/1/21.
 */
public class JinJuBaoAdapter extends BaseAdapter{

    public JinJuBaoAdapter(Context context, LinkedList<String> textOne,
                           LinkedList<String> textTwo, LinkedList<String> textThree,
                           LinkedList<String> textFour, LinkedList<String> textFive,
                           LinkedList<String> textSix, LinkedList<String> textSeven,
                           LinkedList<Integer> images){


        super();
        this.textOne = textOne;
        this.textTwo = textTwo;
        this.textThree = textThree;
        this.textFour = textFour;
        this.textFive = textFive;
        this.textSix = textSix;
        this.textSeven = textSeven;
        this.images = images;
        this.context = context;

        inflater = (LayoutInflater)
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount(){
        return images.size();
    }
    @Override
    public Object getItem(int position){
        return images.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        JinJuBaoViewHolder holder;
        if(convertView == null){

            holder = new JinJuBaoViewHolder();
            convertView = inflater.inflate(R.layout.jinjubao_listview_itema,null);
            /**
             * TextView对象
             */
            holder.mRate = (TextView) convertView.findViewById(R.id.rate);
            holder.mYuqiRate = (TextView) convertView.findViewById(R.id.yuqi_rate);
            holder.mYjbContent  = (TextView) convertView.findViewById(R.id.yjb_content);
            holder.mYjbTerm = (TextView) convertView.findViewById(R.id.yjb_term);
            holder.mYjbSongqian = (TextView) convertView.findViewById(R.id.yjb_songqian);
            holder.mRenchouzong = (TextView) convertView.findViewById(R.id.renchouzong);
            holder.mHot = (TextView) convertView.findViewById(R.id.hot);
            /**
             * RelativeLayout对象
             */
            holder.mYjbXsBg = (RelativeLayout) convertView.findViewById(R.id.yjb_xs_bg);
            //holder.mRlRoot  = (RelativeLayout) convertView.findViewById(R.id.rlroot);

            convertView.setTag(holder);
        }else{

            holder = (JinJuBaoViewHolder)convertView.getTag();
        }

        holder.mRate.setText(textOne.get(position));
        holder.mRate.setTag(position);
        holder.mYuqiRate.setText(textTwo.get(position));
        holder.mYjbContent.setText(textThree.get(position));
        holder.mYjbTerm.setText(textFour.get(position));
        holder.mYjbSongqian.setText(textFive.get(position));
        holder.mRenchouzong.setText(textSix.get(position));
        holder.mHot.setText(textSeven.get(position));
        holder.mYjbXsBg.setBackgroundResource(images.get(position));
        //holder.mRlRoot.setTag(position);

        return convertView;
    }
    /**
     * 内部类---ViewHolder对象
     */
    class JinJuBaoViewHolder{
        /**
         * TextView对象
         */
        protected TextView mRate;
        protected TextView mYuqiRate;
        protected TextView mYjbContent;
        protected TextView mYjbTerm;
        protected TextView mYjbSongqian;
        protected TextView mRenchouzong;
        protected TextView mHot;
        /**
         * RelativeLayout对象
         */
        protected RelativeLayout mYjbXsBg;
        //protected RelativeLayout mRlRoot;
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
//    private String[] textSeven;
    /**
     * int数组
     */
//    private int[] images;

    private LinkedList<String> textOne;
    private LinkedList<String> textTwo;
    private LinkedList<String> textThree;
    private LinkedList<String> textFour;
    private LinkedList<String> textFive;
    private LinkedList<String> textSix;
    private LinkedList<String> textSeven;
    private LinkedList<Integer> images;
    /**
     * LayoutInflater对象
     */
    private LayoutInflater inflater;
}
