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
 * Created by Administrator on 2016/1/21.
 */
public class JinJuBaoRecylerViewAdapter extends
                        RecyclerView.Adapter<JinJuBaoRecylerViewAdapter.JinJuBaoViewHolder>{

//    public JinJuBaoRecylerViewAdapter(String[] textOne,String[] textTwo,
//                                      String[] textThree,String[] textFour,
//                                      String[] textFive,String[] textSix,String[] textSeven,
//                                      int[] images){
//
//        super();
//        this.textOne = textOne;
//        this.textTwo = textTwo;
//        this.textThree = textThree;
//        this.textFour = textFour;
//        this.textFive = textFive;
//        this.textSix = textSix;
//        this.textSeven = textSeven;
//        this.images = images;
//    }
    public JinJuBaoRecylerViewAdapter(List<ProjectInfo> projectInfos){

        super();
        this.projectInfos = projectInfos;
    }
    @Override
    public int getItemCount(){

        return projectInfos.size();
    }
    @Override
    public void onBindViewHolder(JinJuBaoViewHolder holder, int position){

        /**
         * TextView对象
         */
//        holder.mRate.setText(textOne[position]);
//        //holder.mYuqiRate.setText(textTwo[position]);
//        holder.mYjbContent.setText(textThree[position]);
//        holder.mYjbTerm.setText(textFour[position]);
//        holder.mYjbSongqian.setText(textFive[position]);
//        holder.mRenchouzong.setText(textSix[position]);
//        holder.mHot.setText(textSeven[position]);
        /**
         * RelativeLayout对象
         */
//        holder.mYjbXsBg.setBackgroundResource(images[position]);

        ProjectInfo itemInfo = projectInfos.get(position);
        holder.mJjbRate.setText(itemInfo.getmRate());
        //holder.mYuqiRate.setText(textTwo[position]);
//        holder.mJjbContent.setText(itemInfo.getmHeadProjectId());
        holder.mJjbTerm.setText(itemInfo.getmProjectId());

        String mValue = itemInfo.getmStartRateDate();
//        if(mValue.length() > 4){
//
//            mValue = "100+";
//        }

        holder.mJjbSongqian.setText(mValue);

        holder.mJjbRenchouzong.setText(itemInfo.getmProjectDesc1());
//        holder.mJjbHot.setText(itemInfo.getmProjectDesc2());
        /**
         * RelativeLayout对象
         */
//        holder.mJjbXsBg.setBackgroundResource(itemInfo.getmBgImage());
    }
    @Override
    public JinJuBaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View mView = inflater.inflate(R.layout.jinjubao_listview_itema,parent,false);
        return new JinJuBaoViewHolder(mView,itemClickListener);
    }
    /**
     * item点击监听
     */
    public void onItemClickListener(RecylerViewItemClickListener itemClickListener){

        this.itemClickListener = itemClickListener;
    }
    /**
     * 内部类---ViewHodler对象
     */
    class JinJuBaoViewHolder extends RecyclerView.ViewHolder
                             implements View.OnClickListener{

        public JinJuBaoViewHolder(View itemView,
                                  RecylerViewItemClickListener itemClickListener){

            super(itemView);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);

            mJjbRate = (TextView) itemView.findViewById(R.id.rate);
            //mYuqiRate = (TextView) itemView.findViewById(R.id.yuqi_rate);
            mJjbContent = (TextView) itemView.findViewById(R.id.yjb_content);
            mJjbTerm = (TextView) itemView.findViewById(R.id.yjb_term);
            mJjbSongqian = (TextView) itemView.findViewById(R.id.yjb_songqian);
            mJjbRenchouzong = (TextView) itemView.findViewById(R.id.renchouzong);
            mJjbHot = (TextView) itemView.findViewById(R.id.hot);
            mJjbXsBg = (RelativeLayout) itemView.findViewById(R.id.yjb_xs_bg);
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
        private TextView mJjbRate;
        //private TextView mYuqiRate;
        private TextView mJjbContent;
        private TextView mJjbTerm;
        private TextView mJjbSongqian;
        private TextView mJjbRenchouzong;
        private TextView mJjbHot;
        /**
         * RelativeLayout对象
         */
        private RelativeLayout mJjbXsBg;
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
    private String[] textSeven;
    /**
     * int数组
     */
    private int[] images;


    /**
     * 存储ProjectInfo列表
     */
    private List<ProjectInfo> projectInfos;
    /**
     * RecylerViewItemClickListener对象
     */
    private RecylerViewItemClickListener itemClickListener;
}
