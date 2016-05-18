package com.weibank.com.weibankapp.service;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.baofoo.sdk.vip.BaofooPayActivity;
import com.weibank.com.weibankapp.beans.PayParams;
import com.weibank.com.weibankapp.utils.NormalMethods;

@SuppressLint("SimpleDateFormat")
public class OrderService extends AsyncTask<Integer, Void, Boolean>{

	public final static int REQUEST_CODE_BAOFOO_SDK = 100;
	// 附属界面
	private Activity mainActivity;
	// 全局对话框
	private AlertDialog dialog;
	// 保留请求序列号，此序列号与商户自身订单号无关，请注意
	private String orderNo = null;
	private String msg = "";
	private final static String URL_BAOFOO_GATEWAY_TEST =
			"http://tgw.baofoo.com/rsa/merchantPost.action";
	//"https://tgw.baofoo.com/apipay/sdk";
	private final static String URL_OK = "0000";
	/**
	 * 支付参数对象
	 */
	private PayParams payParams;
//	EditText ipt_money_ed;
//	EditText id_card_ed;
//	EditText name_ed;
//	EditText bank_card_ed;
//	EditText mobile_ed;

	public OrderService(Activity mainActivity,PayParams payParams){

		this.mainActivity = mainActivity;
		this.payParams = payParams;
	}
	@Override
	protected void onPreExecute(){
		Log.i("orderService", "onPreExecute");
		// 处理前先弹出一个处理等待对话框
		dialog = new AlertDialog(mainActivity) {};
		dialog.setMessage("正在创建宝付支付订单");
		dialog.setCancelable(false);
		dialog.show();
	}

	@Override
	protected Boolean doInBackground(Integer... params){
		try {
			getOrderNo();
			if(orderNo == null || orderNo.equals("")){
				return false;
			}
		} catch (Exception e) {
			//System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	protected void onPostExecute(Boolean isOk) {
		//Log.i("orderService", "onPostExecute");
		// 处理结束后处理
		// 关闭全局对话框
		dialog.dismiss();
		// 判断处理是否出错
		if (isOk) {
			Intent payintent = new Intent(mainActivity, BaofooPayActivity.class);
			payintent.putExtra(BaofooPayActivity.PAY_TOKEN, orderNo);
			payintent.putExtra(BaofooPayActivity.PAY_BUSINESS, false);
			mainActivity.startActivityForResult(payintent, REQUEST_CODE_BAOFOO_SDK);
		} else {
			AlertDialog msgdialog = new AlertDialog(mainActivity) {};
			msgdialog.setMessage("创建订单失败 " + msg);
			msgdialog.show();
		}
	}

	// 请求商户服务，获取订单交易流水号,本方法写于商户的服务器端,请求宝付接口，获取交易流水号
	public String getOrderNo() throws Exception {
		//Log.i("orderService", "getOrderNo");

//		ipt_money_ed = (EditText) mainActivity.findViewById(R.id.ipt_money); // 输入金额
//
//		id_card_ed = (EditText) mainActivity.findViewById(R.id.id_card); // 身份证
//
//		name_ed = (EditText) mainActivity.findViewById(R.id.name); // 姓名
//
//		bank_card_ed = (EditText) mainActivity.findViewById(R.id.bank_card); // 卡号
//
//		mobile_ed = (EditText) mainActivity.findViewById(R.id.mobile_ed); // 电话


		BigDecimal orderMoney = new BigDecimal(payParams.getPayMoneyNum() + "");
		String txn_amt = String.valueOf(orderMoney.multiply
				                       (new BigDecimal("100")).intValue());

		//String pay_code = null;
		String pay_code = NormalMethods.onReadIsCode(mainActivity);
//		String acc_no = bank_card_ed.getText().toString();
//		String id_card = id_card_ed.getText().toString();
//		String id_holder = name_ed.getText().toString();
//		String mobile = mobile_ed.getText().toString();
		// if (txn_amt.length() == 0 && pay_code.length() == 0 &&
		// acc_no.length() == 0 && id_card.length() == 0) {
		// Toast.makeText(mainActivity, "不能为空", Toast.LENGTH_SHORT).show();
		// return;
		// }

		String params = getParams5(txn_amt,
				pay_code,
				payParams.getPayBankCard(),
				payParams.getPayIdCard(),
				payParams.getPayPerson(),
				payParams.getPayPhoneNum());
		URL myURL = new URL(URL_BAOFOO_GATEWAY_TEST);
		HttpURLConnection con = (HttpURLConnection) myURL.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		con.setUseCaches(false);
		con.setConnectTimeout(30000);
		con.connect();

		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(params);
		out.flush();
		out.close();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String s = reader.readLine();
		reader.close();
		con.disconnect();
		JSONObject ret = new JSONObject(s);
		String code = ret.getString("retCode");
		String retmsg = ret.getString("retMsg");

		if(URL_OK.equals(code)){

			orderNo = ret.getString("tradeNo");
			msg = "";
		}else{

			msg = retmsg;
		}
		// {"tradeNo":"201506040110000400478535","retMsg":"","retCode":"0000"}
		Log.i("happyinfos",ret.getString("tradeNo"));
		return ret.getString("tradeNo");
	}

	private String getParams5(String txn_amt, String pay_code, String acc_no,
							  String id_card, String id_holder, String mobile) throws Exception{
		// String returnUrl =
		// //"http://tgw.baofoo.com/testPage/server/4.0/ok/1";
		// returnUrl = URLEncoder.encode(returnUrl,"utf-8"); // 服务器通知地址 ,需URL编码
		StringBuilder s = new StringBuilder("");
		s.append("&txn_amt=").append(txn_amt);
		s.append("&pay_code=").append(pay_code);
		s.append("&acc_no=").append(acc_no);
		s.append("&id_card=").append(id_card);
		s.append("&id_holder=").append(URLEncoder.encode(id_holder, "utf-8"));
		s.append("&mobile=").append(mobile);
		return s.toString();
	}

	private String getParams4(String memberId, String TerminalID, String key,
							  BigDecimal orderMoney) throws Exception {
		String returnUrl = "http://tgw.baofoo.com/testPage/server/4.0/ok/1";
		String pageUrl = "";
		String payId = "";
		String tradeDate = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		String transId = String.valueOf(new Date().getTime());
		String noticeType = "0";
		String keyType = "1";
		String commodityAmount = "1";
		String interfaceVersion = "4.0";
		String money = String.valueOf(orderMoney
				.multiply(new BigDecimal("100")).intValue());
		String temp = memberId + '|' + payId + '|' + tradeDate + '|' + transId
				+ '|' + money + '|' + pageUrl + '|' + returnUrl + '|'
				+ noticeType + '|' + key;
		String signature = md5(temp, "utf-8");

		String commodityName = URLEncoder.encode("宝付测试商品", "utf-8"); // 需URL编码
		String userName = URLEncoder.encode("baofoo", "utf-8"); // 需URL编码
		String AdditionalInfo = URLEncoder.encode("helloworld", "utf-8"); // 需URL编码
		pageUrl = URLEncoder.encode(pageUrl, "utf-8"); // 页面通知地址 ,需URL编码
		returnUrl = URLEncoder.encode(returnUrl, "utf-8"); // 服务器通知地址 ,需URL编码

		StringBuilder s = new StringBuilder("");
		s.append("PayID=").append(payId);
		s.append("&MemberID=").append(memberId);
		s.append("&TerminalID=").append(TerminalID);
		s.append("&TradeDate=").append(tradeDate);
		s.append("&OrderMoney=").append(money);
		s.append("&TransId=").append(transId);
		s.append("&ReturnUrl=").append(returnUrl);
		s.append("&PageUrl=").append(pageUrl);
		s.append("&KeyType=").append(keyType);
		s.append("&Signature=").append(signature);
		s.append("&CommodityName=").append(commodityName);
		s.append("&CommodityAmount=").append(commodityAmount);
		s.append("&UserName=").append(userName);
		s.append("&AdditionalInfo=").append(AdditionalInfo);
		s.append("&InterfaceVersion=").append(interfaceVersion);
		s.append("&noticeType=").append(noticeType);

		return s.toString();
	}
	/**
	 * MD5加密函数
	 */
	public String md5(String str, String encode) throws Exception{
		if(str == null){
			return null;
		}
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(str.getBytes(encode));
		byte[] digest = md5.digest();

		StringBuffer hexString = new StringBuffer();
		String strTemp;
		for(int i = 0; i < digest.length; i++){

			strTemp = Integer.toHexString((digest[i] & 0x000000FF) | 0xFFFFFF00)
					.substring(6);
			hexString.append(strTemp);
		}
		return hexString.toString();
	}
}
