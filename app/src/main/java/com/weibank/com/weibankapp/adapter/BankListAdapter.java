package com.weibank.com.weibankapp.adapter;
import java.io.InputStream;
import java.util.List;
import com.weibank.com.weibankapp.R;
import com.weibank.com.weibankapp.beans.Bank;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class BankListAdapter extends BaseAdapter{
	
	LayoutInflater inflater;
	// 定义Context
	private Activity mActivity;
	private ViewHolder holder;
	private int clickTemp = -1;
	private List<Bank> list;
	private Bank mBank; 
	private AssetManager assets;
	BitmapFactory.Options options;
		
	public BankListAdapter(Activity activity, List<Bank> banklist) {
		mActivity = activity;
		inflater = activity.getLayoutInflater();
		list = banklist;
		assets = mActivity.getAssets();
		options = new BitmapFactory.Options();
//		mFinanceVolume = financeVolume;
//		loadingDialog = new NormalProgressDialog(mActivity);
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public void setSeclection(int position) {
		clickTemp = position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.fragment_finance_bank_list_item, null);
			holder.bank_icon = (ImageView) convertView
					.findViewById(R.id.bank_icon);
			holder.bank_name = (TextView) convertView
					.findViewById(R.id.bank_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (list.size() > 0) {
//			setImage(list.get(position).getId()+"");
			holder.bank_name.setText(list.get(position).getName()+"");
		}
		
//		convertView.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//			}
//		});
		return convertView;
	}
	
	
	public void setImage(String imgStr){
        InputStream is = null;
        try {
            is = assets.open("img/logo/" + imgStr + ".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
        holder.bank_icon.setImageBitmap(bitmap);
	}
	
	
	private class ViewHolder {
		ImageView bank_icon;
		TextView bank_name;
		
	}
}
