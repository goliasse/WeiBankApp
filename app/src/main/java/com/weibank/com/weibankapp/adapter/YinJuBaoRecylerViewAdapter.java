package com.weibank.com.weibankapp.adapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.weibank.com.weibankapp.R;
import com.weibank.com.weibankapp.beans.ProjectInfo;
import com.weibank.com.weibankapp.listener.RecylerViewItemClickListener;
import java.util.List;

/**
 * Created by Administrator on 2016/1/20.
 */
public class YinJuBaoRecylerViewAdapter
              extends RecyclerView.Adapter<YinJuBaoRecylerViewAdapter.YinJuBaoViewHolder>{

//    public YinJuBaoRecylerViewAdapter(String[] textOne,String[] textTwo,
//                                       String[] textThree,String[] textFour,
//                                       String[] textFive,String[] textSix,int[] images){
//
//        super();
//        this.textOne = textOne;
//        this.textTwo = textTwo;
//        this.textThree = textThree;
//        this.textFour = textFour;
//        this.textFive = textFive;
//        this.textSix = textSix;
//        this.images = images;
//    }
    public YinJuBaoRecylerViewAdapter(List<ProjectInfo> projectInfos){

        super();
        this.projectInfos = projectInfos;

    }
    @Override
    public int getItemCount(){

        return projectInfos.size();
    }
    @Override
    public void onBindViewHolder(YinJuBaoViewHolder holder, int position) {

//        holder.mRate.setText(textOne[position]);
//        holder.mYuqiRate.setText(textTwo[position]);
//        holder.mYjbTerm.setText(textThree[position]);
//        holder.mYjbSongqian.setText(textFour[position]);
//        holder.mRenchouzong.setText(textFive[position]);
//        holder.mHot.setText(textSix[position]);
//
//        holder.mYjbXsBg.setBackgroundResource(images[position]);

        ProjectInfo termInfo = projectInfos.get(position);

        holder.mRate.setText(termInfo.getmRate());
        //holder.mYuqiRate.setText(textTwo[position]);
        holder.mYjbTerm.setText(termInfo.getmProjectId());

        String mValue = termInfo.getmStartRateDate();
        if(mValue.length() > 5){

            mValue = "10.00+";
        }
        holder.mYjbSongqian.setText(mValue);

        holder.mRenchouzong.setText(termInfo.getmProjectDesc1());
//        holder.mHot.setText(termInfo.getmProjectDesc2());

//        holder.mYjbXsBg.setBackgroundResource(termInfo.getmBgImage());
    }
    @Override
    public YinJuBaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View mView = inflater.inflate(R.layout.yinjubao_listview_itema, parent, false);
        return new YinJuBaoViewHolder(mView,itemClickListener);
    }
    /**
     * adapter实现监听
     */
    public void onItemClickListener(RecylerViewItemClickListener itemClickListener){

        this.itemClickListener = itemClickListener;
    }
    /**
     * 内部类--ViewHolder类
     */
    class YinJuBaoViewHolder extends RecyclerView.ViewHolder
                             implements View.OnClickListener{

        public YinJuBaoViewHolder(View itemView,
                                  RecylerViewItemClickListener itemClickListener){

            super(itemView);

            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
            /**
             * TextView对象
             */
            mRate = (TextView) itemView.findViewById(R.id.rate);
            mYuqiRate = (TextView) itemView.findViewById(R.id.yuqi_rate);
            mYjbTerm = (TextView) itemView.findViewById(R.id.yjb_term);
            mYjbSongqian = (TextView) itemView.findViewById(R.id.yjb_songqian);
            mRenchouzong = (TextView) itemView.findViewById(R.id.renchouzong);
//            mHot = (TextView) itemView.findViewById(R.id.hot);
            /**
             * RelativeLayout对象
             */
//            mYjbXsBg = (RelativeLayout) itemView.findViewById(R.id.yjb_xs_bg);
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
        private TextView mRate;
        private TextView mYuqiRate;
        private TextView mYjbTerm;
        private TextView mYjbSongqian;
        private TextView mRenchouzong;
//        private TextView mHot;
        /**
         * RelativeLayout对象
         */
//        private RelativeLayout mYjbXsBg;
        /**
         * RecylerViewItemClickListener对象
         */
        private RecylerViewItemClickListener itemClickListener;
    }
    /**
     * String数组
     */
    private String[] textOne;
    private String[] textTwo;
    private String[] textThree;
    private String[] textFour;
    private String[] textFive;
    private String[] textSix;
    /**
     * int数组
     */
    private int[] images;


    /**
     * 存储ProjectInfo方法
     */
    private List<ProjectInfo> projectInfos;
    /**
     * RecylerViewItemClickListener对象
     */
    private RecylerViewItemClickListener itemClickListener;
}
