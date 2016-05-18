package com.baofoo.sdk.vip;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.baofoo.sdk.vip.util.SecurityUtil;

@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled", "NewApi" })
public class BaofooPayActivity extends Activity {

	/**
	 * true:测试<br/>
	 * false:正式
	 */
	public final static String PAY_BUSINESS = "PAY_BUSINESS";

	public final static String PAY_TOKEN = "PAY_TOKEN";
	public final static String PAY_RESULT = "PAY_RESULT";
	public final static String PAY_MESSAGE = "PAY_MESSAGE";
	public final static int PAY_RESULT_CODE = 199;
	public final static String PAY_SDK_STORE_V1 = "PAY_SDK_STORE_V1";
	public final static String PAY_SDK_STORE_KEY = "sdk@bfv1";
	public final static String PAY_SDK_STORE_CARD = "PAY_SDK_STORE_CARD";

	private final static String URL_BAOFOO_GATEWAY_TEST = "https://tgw.baofoo.com/apipay/orderRequest";
	private final static String URL_BAOFOO_GATEWAY_BUSINESS = "https://gw.baofoo.com/apipay/orderRequest";

	private final static String PIC_GUIDE = "launch_guide.png";
	private final static String PIC_NOTFOUND = "not_found.png";

	private SharedPreferences walletPreferences;
	private String paysdkurl;
	private String tradeNo;
	private boolean isBusiness;

	private FrameLayout mainLayout;
	private ProgressBar progressbar;
	private RelativeLayout progressView;
	private WebView webView;
	private ImageView guideView;//
	private ImageView notfoundView;

	Bitmap guide;
	Bitmap notfound;

	BitmapFactory.Options mBitmapOptions;
	public boolean mIsOnpause = true;

	public static final int MSG_VIEW_WEBVIEW = 15;
	public static final int MSG_VIEW_NOT_FOUND = 20;
	public static final int MSG_WAIT = 30;

	private boolean connected = false;
	private boolean firstshowdown = false;
	private boolean isnotfound = false;
	private Handler mhandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (mIsOnpause)
				return;
			if (msg.what == MSG_VIEW_WEBVIEW) {
				if (guideView != null)
					guideView.setVisibility(View.GONE);
				if (webView != null)
					webView.setVisibility(View.VISIBLE);
				if (notfoundView != null)
					notfoundView.setVisibility(View.GONE);
				if (progressView.isShown())
					progressView.setVisibility(View.GONE);
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
			} else if (msg.what == MSG_VIEW_NOT_FOUND) {
				isnotfound = true;
				if (firstshowdown) {
					// Log.d("hrl", "handleMessage 5");
					if (guideView != null)
						guideView.setVisibility(View.GONE);
					if (webView != null)
						webView.setVisibility(View.GONE);
					if (notfoundView != null)
						notfoundView.setVisibility(View.VISIBLE);
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				} else {
					// Log.d("hrl", "handleMessage 6");
					mhandler.sendEmptyMessageDelayed(MSG_VIEW_NOT_FOUND, 500);
				}
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Log.d("hrl", "onCreate");
		Log.i("zzx", "onCreate");
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//		if (getIntent() == null) {
//			Intent intent = new Intent();
//			intent.putExtra(PAY_RESULT, "fatal");
//			intent.putExtra(PAY_MESSAGE, "参数");
//			setResult(RESULT_CANCELED, intent);
//			finish();
//			return;
//		}
		Log.i("zzx", "setRequestedOrientation");
		// 验证参数
		Bundle params = getIntent().getExtras();
		if (params == null || params.isEmpty()
				|| !params.containsKey(PAY_TOKEN)
				|| params.getString(PAY_TOKEN).isEmpty()) {
			Intent intent = new Intent();
			intent.putExtra(PAY_RESULT, "fatal");
			intent.putExtra(PAY_MESSAGE, "交易单号不能为空");
			setResult(RESULT_CANCELED, intent);
			finish();
			return;
		}

		tradeNo = params.getString(PAY_TOKEN);
		
		Log.i("zzx", "tradeNo>>="+tradeNo);
		isBusiness = params.getBoolean(PAY_BUSINESS);
		if (isBusiness) {
			paysdkurl = URL_BAOFOO_GATEWAY_BUSINESS;
		} else {
			paysdkurl = URL_BAOFOO_GATEWAY_TEST;
		}

		// webview初始化
		initView();
		layout();
		// 接受参数，判断参数合法性

		// 加载页面
		String formHtml = getHtmlData();
		webView.removeJavascriptInterface("searchBoxJavaBridge_");//防止360
		webView.loadData(formHtml, "text/html", "utf-8");

		// 缓存设置
		walletPreferences = getApplicationContext().getSharedPreferences(
				PAY_SDK_STORE_V1, Context.MODE_PRIVATE);

		showGuide();
	}

	@Override
	protected void onResume() {
		// Log.d("hrl", "onResume");
		mIsOnpause = false;
		super.onResume();
	}

	@Override
	protected void onStart() {
		// Log.d("hrl", "onStart");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// Log.d("hrl", "onRestart");
		super.onRestart();
	}

	@Override
	protected void onPause() {
		// Log.i("hrl", "onPause");
		super.onPause();
		// this.finish();
	}

	@Override
	protected void onStop() {
		// Log.d("hrl", "onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// Log.d("hrl", "onDestroy");
		super.onDestroy();
		mIsOnpause = true;
		webView.setWebViewClient(null);
		webView.setWebChromeClient(null);

		// mhandler.removeMessages(MSG_VIEW_GUIDE);
		mhandler.removeMessages(MSG_VIEW_WEBVIEW);
		mhandler.removeMessages(MSG_VIEW_NOT_FOUND);
		mhandler.removeMessages(MSG_WAIT);
		guideView = null;
		notfoundView = null;
		webView = null;
		progressbar = null;
		progressView.removeAllViews();
		progressView = null;
		mainLayout.removeAllViews();
		mainLayout = null;

		if (guide != null) {
			guide.recycle();
		}
		guide = null;
		if (guide != null) {
			notfound.recycle();
		}
		notfound = null;
	}

	public void showLoading() {
		// Log.e("hrl", "showLoading");
		if (!progressView.isShown())
			progressView.setVisibility(View.VISIBLE);
	}

	public void hideLoading() {
		// Log.e("hrl", "hideLoading");
		if (progressView.isShown())
			progressView.setVisibility(View.GONE);
	}

	private void initView() {
		// Log.i("hrl", "initView");
		mainLayout = new FrameLayout(this);
		setContentView(mainLayout);

		mBitmapOptions = new BitmapFactory.Options();
		mBitmapOptions.inTempStorage = new byte[1024 * 1024];
		mBitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
		mBitmapOptions.inPurgeable = true;
		mBitmapOptions.inSampleSize = 2;
		mBitmapOptions.inInputShareable = true;

		// webview
		initWebView();
		// imageview
		initGuidePic();
		// 进度条
		progressbar = new ProgressBar(this);
		progressView = new RelativeLayout(this);
	}

	private void layout() {
		// Log.w("hrl", "layout " + guideView);
		RelativeLayout.LayoutParams progressLayoutParam = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		progressLayoutParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
		progressLayoutParam.addRule(RelativeLayout.CENTER_VERTICAL);
		new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		progressView.addView(progressbar, progressLayoutParam);

		FrameLayout.LayoutParams frameLayoutParam = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		mainLayout.addView(guideView, frameLayoutParam);
		mainLayout.addView(webView, frameLayoutParam);
		mainLayout.addView(notfoundView, frameLayoutParam);
		mainLayout.addView(progressView, frameLayoutParam);

		guideView.setVisibility(View.VISIBLE);
		webView.setVisibility(View.GONE);
		notfoundView.setVisibility(View.GONE);
		progressView.setVisibility(View.GONE);

	}

	private void initWebView() {
		// Log.e("hrl", "initWebView");
		webView = new WebView(getApplicationContext());
		webView.setId(MSG_VIEW_WEBVIEW);
		webView.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
			}

			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					final JsResult result) {
				// Log.d("BaofooPay-WebChromeClient", "onJsAlert");
				Dialog dialog = new AlertDialog.Builder(BaofooPayActivity.this)
						.setMessage(message).create();
				dialog.show();
				return super.onJsAlert(view, url, message, result);
			}

			@Override
			public boolean onJsConfirm(WebView view, String url,
					String message, final JsResult result) {
				// Log.d("BaofooPay-WebChromeClient", "onJsConfirm");
				AlertDialog.Builder builder = new AlertDialog.Builder(
						BaofooPayActivity.this);

				builder.setMessage(message);
				builder.setPositiveButton("是",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								result.confirm();
							}
						});
				builder.setNegativeButton("否",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								result.cancel();
							}
						});
				builder.setOnCancelListener(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						result.cancel();
					}
				});
				builder.setCancelable(true);
				builder.show();
				return true;
			}
		});
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// //Log.d("BaofooPay-WebViewClient", "onPageStarted:" + url);
				BaofooPayActivity.this.showLoading();
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// //Log.d("BaofooPay-WebViewClient", "onPageFinished:" + url);
				if (url != null && !url.startsWith("data")) {
					BaofooPayActivity.this.hideLoading();
					mhandler.sendEmptyMessage(MSG_VIEW_WEBVIEW);
					// view.loadUrl("javascript:window.baofoosdk.checkAccess(document.getElementsByTagName('h1')[0].innerHTML);");
				} else {
				}
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// Log.d("BaofooPay-WebViewClient", "onReceivedError:" +
				// errorCode);
				mhandler.sendEmptyMessage(MSG_VIEW_NOT_FOUND);
			}

			@Override
			public void onReceivedHttpAuthRequest(WebView view,
					HttpAuthHandler handler, String host, String realm) {
				super.onReceivedHttpAuthRequest(view, handler, host, realm);
			}

			@Override
			public void onReceivedSslError(final WebView view,
					SslErrorHandler handler, SslError error) {
				super.onReceivedSslError(view, handler, error);
				handler.proceed();
			}
		});
		webView.setInitialScale(100);
		webView.setNetworkAvailable(true);
		webView.setScrollContainer(true);
		webView.setVerticalScrollBarEnabled(true);
		webView.addJavascriptInterface(this, "baofoosdk");
		WebSettings settings = webView.getSettings();
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		settings.setJavaScriptEnabled(true);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setSaveFormData(false);
		settings.setDomStorageEnabled(true);
		settings.setAllowContentAccess(true);
		settings.setAllowFileAccess(true);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
	}

	private void initGuidePic() {
		// Log.i("hrl", "initGuidePic");
		try {
			guide = BitmapFactory.decodeStream(
					this.getAssets().open(PIC_GUIDE), null, mBitmapOptions);
			guideView = new ImageView(this);
			guideView.setImageBitmap(guide);
			guideView.setScaleType(ImageView.ScaleType.FIT_XY);

			notfound = BitmapFactory.decodeStream(
					this.getAssets().open(PIC_NOTFOUND), null, mBitmapOptions);
			notfoundView = new ImageView(this);
			notfoundView.setImageBitmap(notfound);
			notfoundView.setScaleType(ImageView.ScaleType.FIT_XY);

			notfoundView.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					String[] fatal = new String[2];
					fatal[0] = "-1";
					fatal[1] = "亲，网络连接有问题哦，请检查网路后再试";
					payResult(fatal);
					return true;
				}
			});
		} catch (IOException e) {

			// Log.i("hrl", "initGuidePic " + e.toString());
		}
	}

	private String getHtmlData() {
		// Log.i("hrl", "getHtmlData");
		StringBuilder formHtml = new StringBuilder();
		formHtml.append("<html><head><meta charset='utf-8'></head>");
		formHtml.append("<body onload='payform.submit()'><form name='payform' action='");
		formHtml.append(paysdkurl); // 提交地址
		formHtml.append("' method='post'>");
		// 参数列表
		formHtml.append("<input type='hidden' name='tradeNo' value='")
				.append(tradeNo).append("' />");
		formHtml.append("<input type='hidden' name='version' value='1.0.0' />");
		formHtml.append("</form></body>").append("").append("</html>");
		return formHtml.toString();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Log.i("hrl", "onKeyDown");
		if (keyCode == KeyEvent.KEYCODE_BACK && connected && !isnotfound) {
			webView.loadUrl("javascript:(typeof(goBack)=='function'?goBack():window.history.back());");
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@JavascriptInterface
	public void payResult(String[] result) {
		String payresultCode = "";
		String payresultMessage = "";
		if (result == null || result.length != 2) {
			payresultCode = "-1";
			payresultMessage = "处理失败";
		} else {
			payresultCode = result[0];
			payresultMessage = result[1];
		}

		Intent intent = new Intent();
		intent.putExtra(PAY_RESULT, payresultCode);
		intent.putExtra(PAY_MESSAGE, payresultMessage);
		// Log.d("BaofooPay-BaofooPayActivity", "返回Code:" + payresultCode
		// + ",返回消息:" + payresultMessage);
		setResult(PAY_RESULT_CODE, intent);
		finish();
	}

	@JavascriptInterface
	public void saveData(String jsonvalue) {
		SharedPreferences.Editor editor = walletPreferences.edit();
		editor.putString(PAY_SDK_STORE_CARD,
				SecurityUtil.desEncrypt(jsonvalue, PAY_SDK_STORE_KEY));
		editor.commit();
	}

	@JavascriptInterface
	public String readData() {
		connected = true;
		// mhandler.sendEmptyMessage(MSG_VIEW_WEBVIEW);

		String result = walletPreferences.getString(PAY_SDK_STORE_CARD, "");
		if (result != null && !"".equals(result)) {
			result = SecurityUtil.desDecrypt(result, PAY_SDK_STORE_KEY);
		}
		return result;
	}

	@JavascriptInterface
	public void alert(String[] args) {
		if (args != null || args.length == 1) {
			String message = args[0];
			AlertDialog builder = new AlertDialog.Builder(
					BaofooPayActivity.this).create();
			builder.setMessage(message);
			builder.show();
		}
	}

	@JavascriptInterface
	public void confirm(String[] args) {
		if (args != null || args.length == 3) {
			String message = args[0];
			final String doyes = args[1];
			final String dono = args[2];
			AlertDialog.Builder builder = new AlertDialog.Builder(
					BaofooPayActivity.this);

			builder.setMessage(message);
			builder.setPositiveButton("是",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							webView.loadUrl("javascript:" + doyes + "();");
						}
					});
			builder.setNegativeButton("否",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							webView.loadUrl("javascript:" + dono + "();");
						}
					});
			builder.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					webView.loadUrl("javascript:" + dono + "();");
				}
			});
			builder.setCancelable(true);
			// builder.setNegativeButton("否", null);
			builder.show();
		}
	}

	@JavascriptInterface
	public void checkAccess(String k1) {
		if (k1 != null && k1.contains("404")) {
			// mhandler.sendEmptyMessage(MSG_VIEW_NOT_FOUND);
		} else {
			// guideView.setVisibility(ImageView.GONE);
			BaofooPayActivity.this.hideLoading();
		}
	}

	private void showGuide() {
		if (guideView != null)
			guideView.setVisibility(View.VISIBLE);
		if (webView != null)
			webView.setVisibility(View.GONE);
		if (notfoundView != null)
			notfoundView.setVisibility(View.GONE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}
