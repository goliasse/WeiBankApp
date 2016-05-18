package com.weibank.com.weibankapp.adapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.weibank.com.weibankapp.R;
import com.weibank.com.weibankapp.beans.MainProjectBean;
import com.weibank.com.weibankapp.listener.RecylerViewItemClickListener;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/30.
 */
public class ShouYeXRecyleViewAdapter extends
              RecyclerView.Adapter<ShouYeXRecyleViewAdapter.ShouYeViewHolder>{

    public ShouYeXRecyleViewAdapter(ArrayList<MainProjectBean> beans){

        super();
        this.beans = beans;
    }
    @Override
    public void onBindViewHolder(ShouYeViewHolder viewHolder, int position){

        MainProjectBean bean = beans.get(position);
        viewHolder.mainImg.setImageResource(bean.getMainImg());
        viewHolder.viceImg1.setImageResource(bean.getViceImg1());
        viewHolder.viceImg2.setImageResource(bean.getViceImg2());
        viewHolder.viceImg3.setImageResource(bean.getViceImg3());
        viewHolder.yearRate.setText(bean.getmYearRate());
    }
    @Override
    public ShouYeViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View mView = inflater.inflate(R.layout.pro_listview_item,parent,false);
        return new ShouYeViewHolder(mView,itemClickListener);
    }
    @Override
    public int getItemCount(){

        return beans.size();
    }
    /**
     * adapter实现监听
     */
    public void onItemClickListener(RecylerViewItemClickListener itemClickListener){

        this.itemClickListener = itemClickListener;
    }
    /**
     * 内部类---ViewHolder对象
     */
    class ShouYeViewHolder extends RecyclerView.ViewHolder
                            implements View.OnClickListener{

        public ShouYeViewHolder(View itemView,RecylerViewItemClickListener itemClickListener){

            super(itemView);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
            mainImg = (ImageView) itemView.findViewById(R.id.iva);
            viceImg1 = (ImageView) itemView.findViewById(R.id.ivb);
            viceImg2 = (ImageView) itemView.findViewById(R.id.ivc);
            viceImg3 = (ImageView) itemView.findViewById(R.id.ivd);
            yearRate = (TextView) itemView.findViewById(R.id.tva);
        }
        @Override
        public void onClick(View v){
            if(itemClickListener != null){

                itemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
        /**
         * ImageView对象
         */
        protected ImageView mainImg;
        protected ImageView viceImg1;
        protected ImageView viceImg2;
        protected ImageView viceImg3;
        /**
         *  TextView对象
         */
        protected TextView yearRate;
        /**
         * RecylerViewItemClickListener对象
         */
        private RecylerViewItemClickListener itemClickListener;
    }
    /**
     * RecylerViewItemClickListener对象
     */
    private RecylerViewItemClickListener itemClickListener;
    /**
     * 存储MainProjectBean对象列表
     */
    private ArrayList<MainProjectBean> beans;

}
