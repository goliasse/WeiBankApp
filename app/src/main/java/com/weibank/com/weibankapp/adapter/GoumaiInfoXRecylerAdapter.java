package com.weibank.com.weibankapp.adapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.weibank.com.weibankapp.R;
import com.weibank.com.weibankapp.beans.GoumaiInfoBean;
import com.weibank.com.weibankapp.listener.RecylerViewItemClickListener;

import java.util.LinkedList;

/**
 *  Created by Administrator on 2016/4/6.
 */
public class GoumaiInfoXRecylerAdapter extends
              RecyclerView.Adapter<GoumaiInfoXRecylerAdapter.GoumaiInfoViewHolder>{

    public GoumaiInfoXRecylerAdapter(LinkedList<GoumaiInfoBean> goumaiInfoBeans){

        this.goumaiInfoBeans = goumaiInfoBeans;
    }
    @Override
    public int getItemCount(){

        return goumaiInfoBeans.size();
    }
    @Override
    public void onBindViewHolder(GoumaiInfoViewHolder holder, int position){

        GoumaiInfoBean infoBean = goumaiInfoBeans.get(position);
        holder.mRate1.setText(infoBean.getmRate1());
        holder.mRate2.setText(infoBean.getmRate2());
        String mProject = infoBean.getmProject();
        if(mProject.length() > 10){

            mProject = mProject.substring(0,9) + "...";
        }
        holder.mGoumaiProject.setText(mProject);

        String startRateMoney = infoBean.getmStartRateMoney();
        if(startRateMoney.length() > 4){

            startRateMoney = "1.00+";
        }
        holder.mInterestAmount.setText(startRateMoney);

        holder.mMonths.setText(infoBean.getmMonths());
        holder.mAmount.setText(infoBean.getmAmount());
    }
    @Override
    public GoumaiInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View mView = inflater.inflate(R.layout.goumai_history_item,parent,false);
        return new GoumaiInfoViewHolder(mView,itemClickListener);
    }
    public void setItemClickListener(RecylerViewItemClickListener itemClickListener){

        this.itemClickListener = itemClickListener;
    }
    /**
     *  内部类---GoumaiInfoViewHolder
     */
    protected class GoumaiInfoViewHolder extends RecyclerView.ViewHolder
                                          implements View.OnClickListener{

        public GoumaiInfoViewHolder(View itemView,
                                    RecylerViewItemClickListener itemClickListener){

            super(itemView);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);

            mRate1 = (TextView)  itemView.findViewById(R.id.rate1);
            mRate2 = (TextView) itemView.findViewById(R.id.rate2);
            mGoumaiProject = (TextView) itemView.findViewById(R.id.goumai_project);
            mInterestAmount = (TextView) itemView.findViewById(R.id.interest_amount);
            mMonths = (TextView) itemView.findViewById(R.id.months);
            mAmount = (TextView) itemView.findViewById(R.id.amount);
        }
        @Override
        public void onClick(View v){
            if(v != null){

                itemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
        /**
         *  TextView对象
         */
        protected TextView mRate1;
        protected TextView mRate2;
        protected TextView mGoumaiProject;
        protected TextView mInterestAmount;
        protected TextView mMonths;
        protected TextView mAmount;
        /**
         *  RecylerViewItemClickListener对象
         */
        protected RecylerViewItemClickListener itemClickListener;
    }
    /**
     *  RecylerViewItemClickListener对象
     */
    protected RecylerViewItemClickListener itemClickListener;
    /**
     *  存储GoumaiInfoBean对象列表
     */
    private LinkedList<GoumaiInfoBean> goumaiInfoBeans;
}

//    private void assignViews() {
//        mGoumaiProject = (TextView) findViewById(R.id.goumai_project);
//        mInterestAmount = (TextView) findViewById(R.id.interest_amount);
//        mRate1 = (TextView) findViewById(R.id.rate1);
//        mGoumaiRateText = (TextView) findViewById(R.id.goumai_rate_text);
//        mRate2 = (TextView) findViewById(R.id.rate2);
//        mZhuangtaibg = (RelativeLayout) findViewById(R.id.zhuangtaibg);
//        mMonths = (TextView) findViewById(R.id.months);
//        mTouzhi = (LinearLayout) findViewById(R.id.touzhi);
//    }

