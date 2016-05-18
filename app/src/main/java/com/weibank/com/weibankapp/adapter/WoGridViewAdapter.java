package com.weibank.com.weibankapp.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.weibank.com.weibankapp.R;

/**
 * Created by Administrator on 2016/1/13.
 */
public class WoGridViewAdapter extends BaseAdapter{

    public WoGridViewAdapter(Context context,int[] images,String[] texts){

        inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.images = images;
        this.texts = texts;
    }

    public WoGridViewAdapter(Context context,int[] images,String[] texts,int resource){

        inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.images = images;
        this.texts = texts;
        this.resource = resource;
    }
    @Override
    public int getCount(){

        return images.length;
    }
    @Override
    public Object getItem(int position){

        return images[position];
    }
    @Override
    public long getItemId(int position){

        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        WoGridViewHolder viewHolder;

        if(convertView == null){

            viewHolder = new WoGridViewHolder();
//            convertView = inflater.inflate(R.layout.wo_gridview_item,null);
//            viewHolder.mImageView = (ImageView)
//                    convertView.findViewById(R.id.gridviewitem_icon);
//            viewHolder.mTextView = (TextView)
//                    convertView.findViewById(R.id.gridviewitem_text);
//            convertView = inflater.inflate(R.layout.grid_item,null);
            convertView = inflater.inflate(resource,null);
            viewHolder.mImageView = (ImageView)
                       convertView.findViewById(R.id.iv_item);
            viewHolder.mTextView = (TextView)
                       convertView.findViewById(R.id.tv_item);
            convertView.setTag(viewHolder);
        }else {

            viewHolder = (WoGridViewHolder)convertView.getTag();
        }
        viewHolder.mImageView.setImageResource(images[position]);
        viewHolder.mTextView.setText(texts[position]);

        return convertView;
    }
    /**
     * 内部类--ViewHolder对象
     */
    class WoGridViewHolder{

        protected ImageView mImageView;
        protected TextView mTextView;
    }
    /**
     * 图片数组
     */
    private int[] images;
    /**
     * 文本数组
     */
    private String[] texts;
    /**
     *  resource
     */
    private int resource;
    /**
     * LayoutInflater对象
     */
    private LayoutInflater inflater;
}
