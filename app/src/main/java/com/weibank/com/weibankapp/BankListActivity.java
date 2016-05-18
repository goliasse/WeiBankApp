package com.weibank.com.weibankapp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import com.weibank.com.weibankapp.adapter.BankListAdapter;
import com.weibank.com.weibankapp.beans.Bank;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class BankListActivity extends Activity 
                              implements OnItemClickListener{

	
	
	List<Bank> mBanklist = new ArrayList<>();
	
	ListView my_bank_list;
	
	BankListAdapter mbanklistAdapter;
	
	private Handler mhander = new Handler(){
		public void handleMessage(android.os.Message msg){
			if (msg.what == 1) {
				try {
					sendBanklist();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_finance_bank_list);
		
		my_bank_list = (ListView) this.findViewById(R.id.my_bank_list);
		
		my_bank_list.setOnItemClickListener(this);
		
		mBanklist.add(new Bank("ICBC", "中国工商银行"));
		mBanklist.add(new Bank("ABC", "中国农业银行"));
		mBanklist.add(new Bank("CCB", "中国建设银行"));
		mBanklist.add(new Bank("BOC", "中国银行"));
		mBanklist.add(new Bank("BCOM", "中国交通银行"));
		mBanklist.add(new Bank("CIB", "兴业银行"));
		mBanklist.add(new Bank("CITIC", "中信银行"));
		mBanklist.add(new Bank("CEB", "中国光大银行"));
		mBanklist.add(new Bank("PAB", "平安银行"));
		mBanklist.add(new Bank("PSBC", "中国邮政储蓄银行"));
		mBanklist.add(new Bank("SHB", "上海银行"));
		mBanklist.add(new Bank("SPDB", "浦东发展银行"));

		mbanklistAdapter = new BankListAdapter(this, mBanklist);
		my_bank_list.setAdapter(mbanklistAdapter);
		mhander.sendEmptyMessage(1);
	
	}
	private final static String URL_BAOFOO_GATEWAY_TEST = "https://tgw.baofoo.com/apipay/sdk";
	private String orderNo = null; // 保留请求序列号，此序列号与商户自身订单号无关，请注意
	public void sendBanklist() throws Exception{
			URL myURL = new URL(URL_BAOFOO_GATEWAY_TEST);
			HttpURLConnection con = (HttpURLConnection)myURL.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type",
	                "application/x-www-form-urlencoded");
			con.setUseCaches(false);
			con.setConnectTimeout(30000);
			con.connect();
//			DataOutputStream out = new DataOutputStream(con.getOutputStream());
//			out.writeBytes(params); 
//			out.flush();
//			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
		                con.getInputStream()));
			
			String s = reader.readLine();
			reader.close();
			con.disconnect();
			Log.i("BaofooPayDemo", s);
			JSONObject ret = new JSONObject(s);
			String code = ret.getString("retCode");
			String retmsg = ret.getString("retMsg");
			orderNo = ret.getString("tradeNo");
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		if (mBanklist.size() > 0) {
			Bank 	bank = (Bank) my_bank_list.getItemAtPosition(position);
			//回填方法1
			Intent intent = new Intent();
			intent.putExtra("bank_name", bank.getName());
			intent.putExtra("bank_id", bank.getId());
			setResult(0, intent);
			BankListActivity.this.finish();
		}
	}
}




//try {
//JSONObject ret = new JSONObject(responseInfo.result);
//JSONObject obj = ret.getJSONObject("obj");
//JSONObject totalobj = obj.getJSONObject("userFinance");
//double total_current = totalobj.getDouble("total_current");
//double total_earnings = totalobj.getDouble("total_earnings");
//JSONArray orderBuyDtos = obj.getJSONArray("orderBuyDtos");
//for (int i = 0; i < orderBuyDtos.length(); i++) {
//	JSONObject innerObject = orderBuyDtos.getJSONObject(i);
//	int product_id = innerObject.getInt("product_id");
//	String endTime = innerObject.getString("endTime");
//	int buy_amount = innerObject.getInt("buy_amount");
//	String product_name = innerObject.getString("product_name");
//	double exp_expire_amount = innerObject.getDouble("exp_expire_amount");
//	int state = innerObject.getInt("state");
//	myFinance.add(new MyFinance(product_id, endTime, buy_amount,
//			product_name, exp_expire_amount, state, total_current, total_earnings));
//}
//} catch (Exception ee) {
//
//}

//private void sendBanklist() {
//// TODO Auto-generated method stub
//String url = HtttpUtill.SHOW_BANK_LIST_URL;
//
//// 生成提交参数
//HashMap<String, String> params = new HashMap<String, String>();
////params.put("cardNumber", bank_number);
//HtttpUtill.SendMsg(params, doResponse, url);
//}
//
//private AbstractCallback<ResultDto> doResponse = new AbstractCallback<ResultDto>(getActivity()) {
//@Override
//public void execute(ResultDto result) {
//	JSONObject ret = (JSONObject) result.getObj();
//	if (result.getCode() == 1) {
//		try {
//			JSONArray obj = ret.getJSONArray("obj");
//			for (int i = 0; i < obj.length(); i++) {
//				JSONObject innerObject = obj.getJSONObject(i);
//				int bankId = (Integer) (!innerObject.isNull("bankId") ? innerObject
//						.getInt("bankId") : "0");
//				String bankName = !innerObject.isNull("bankName") ? innerObject
//						.getString("bankName") : "";
//				mBanklist.add(new BankBean(bankId, bankName));
//			}
//			if (mBanklist.size()>0) {
//				mbanklistAdapter = new BankListAdapter(mActivity, mBanklist);
//				my_bank_list.setAdapter(mbanklistAdapter);
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	} else {
//	}
//
//};
//};
