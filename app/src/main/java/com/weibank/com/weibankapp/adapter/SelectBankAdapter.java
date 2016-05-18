package com.weibank.com.weibankapp.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weibank.com.weibankapp.R;

/**
 * Created by Administrator on 2016/1/19.
 */
public class SelectBankAdapter extends BaseAdapter{

    public SelectBankAdapter(Context context,String[] strings){

        this.context = context;
        this.strings = strings;
        inflater = (LayoutInflater)this.context
                                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount(){
        return strings.length;
    }
    @Override
    public Object getItem(int position){
        return strings[position];
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        BankViewHoler viewHoler;
        if(convertView == null){

            convertView = inflater.inflate(R.layout.bank_listview_item,null);
            viewHoler = new BankViewHoler();
            viewHoler.textView = (TextView)convertView.findViewById(R.id.listbankname);
            convertView.setTag(viewHoler);
        }else{

            viewHoler = (BankViewHoler)convertView.getTag();
        }
        viewHoler.textView.setText(strings[position]);
        return convertView;
    }
    /**
     * 内部类--ViewHolder对象
     */
    class BankViewHoler {

        protected TextView textView;
    }
    /**
     * Context对象
     */
    private Context context;
    /**
     * String对象数组
     */
    private String[] strings;
    /**
     * LayoutInflater对象
     */
    private LayoutInflater inflater;
}
