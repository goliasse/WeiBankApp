package com.weibank.com.weibankapp.adapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.weibank.com.weibankapp.R;
import com.weibank.com.weibankapp.beans.TradeRecordBean;
import com.weibank.com.weibankapp.listener.RecylerViewItemClickListener;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Administrator on 2016/3/31.
 */
public class TradeRecordXRecylerViewAdapter extends
              RecyclerView.Adapter<TradeRecordXRecylerViewAdapter.TradeRecordViewHolder>{

    public TradeRecordXRecylerViewAdapter(ArrayList<TradeRecordBean> tradeBeans){

        super();
        this.tradeBeans = tradeBeans;
    }
    @Override
    public int getItemCount(){

        return tradeBeans.size();
//        return tradeBeans.get(0).size();
    }
    @Override
    public void onBindViewHolder(TradeRecordViewHolder holder, int position){

//        for(int i = 0; i < tradeBeans.size(); i++){
//
//            TradeRecordBean tradeRecordBean = tradeBeans.get(i).get(position);
//            holder.mTransAmount.setText(tradeRecordBean.getTransAmount());
//            holder.mTransDate.setText(tradeRecordBean.getTransDate());
//            holder.mTransStatusName.setText(tradeRecordBean.getTransStatusName());
//            holder.mTransTypeName.setText(tradeRecordBean.getTransTypeName());
//        }

         TradeRecordBean tradeRecordBean = tradeBeans.get(position);
         holder.mTransAmount.setText(tradeRecordBean.getTransAmount());
         holder.mTransDate.setText(tradeRecordBean.getTransDate());
         holder.mTransStatusName.setText(tradeRecordBean.getTransStatusName());
         holder.mTransTypeName.setText(tradeRecordBean.getTransTypeName());
    }
    @Override
    public TradeRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View mView = inflater.inflate(R.layout.traderecord_xrecylerview_item,parent,false);
        return new TradeRecordViewHolder(mView,itemClickListener);
    }
    /**
     * adapter实现监听
     */
    public void onItemClickListener(RecylerViewItemClickListener itemClickListener){

        this.itemClickListener = itemClickListener;
    }
    /**
     * 内部类 TradeRecordViewHolder
     */
    class TradeRecordViewHolder extends RecyclerView.ViewHolder
                                implements View.OnClickListener{

        public TradeRecordViewHolder(View itemView,
                                     RecylerViewItemClickListener itemClickListener){

            super(itemView);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);

            mTransAmount = (TextView)itemView.findViewById(R.id.transAmount);
            mTransDate = (TextView)itemView.findViewById(R.id.transDate);
            mTransStatusName = (TextView)itemView.findViewById(R.id.transStatusName);
            mTransTypeName = (TextView)itemView.findViewById(R.id.transTypeName);
        }
        @Override
        public void onClick(View v){
            if(itemClickListener != null){

                itemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
        /**
         * TextView对象
         */
        protected TextView mTransAmount;
        protected TextView mTransDate;
        protected TextView mTransStatusName;
        protected TextView mTransTypeName;
        /**
         * RecylerViewItemClickListener对象
         */
        protected RecylerViewItemClickListener itemClickListener;
    }
    /**
     * RecylerViewItemClickListener对象
     */
    private RecylerViewItemClickListener itemClickListener;
    /**
     * 存储TradeRecordBean对象
     */
//    private Stack<TradeRecordBean> tradeBeans;
    private ArrayList<TradeRecordBean> tradeBeans;
//    private Stack<Stack<TradeRecordBean>> tradeBeans;
}
