package com.weibank.com.weibankapp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.utils.HttpUtilsHelper;
import java.util.HashMap;
import java.util.Map;

public class TestCodeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_code);
        /**
         * 调用初始化对象方法
         */
        assignViews();
        String phone = mValicode.getText().toString().trim();
        Map<String,String> params = new HashMap<>();
        params.put(MOBILE,phone);
        httpUtilsHelper.post(REQUESTURL, params, new RequestCallBack<String>(){
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo){

                Log.e("myerror", responseInfo.result);
            }
            @Override
            public void onFailure(HttpException e, String s){

                Log.e("myerror",s);
                Log.e("myerror",e.getMessage());
            }
        });
    }
    /**
     *  初始化对象方法
     */
    private void assignViews(){

        mValicode = (EditText) findViewById(R.id.valicode);
        httpUtilsHelper = HttpUtilsHelper.getInstance();
    }
    /**
     * EditText对象
     */
    private EditText mValicode;
    /**
     * 与服务器交互的url
     */
    private final String REQUESTURL = Constants.WEBURL  + "app/getVercode";
    private final String MOBILE = "mobile";
    /**
     * HttpUtilsHelper对象
     */
    private HttpUtilsHelper httpUtilsHelper;
}
