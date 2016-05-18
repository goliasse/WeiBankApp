package com.weibank.com.weibankapp.views;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import com.weibank.com.weibankapp.utils.NormalMethods;

/**
 * Created by Administrator on 2016/3/2.
 */
public class SameTextWatcher implements TextWatcher{

    public SameTextWatcher(Context context,String  mValue){

        super();
        mContext = context;
        this.mValue = mValue;
    }
//    public SameTextWatcher(Context context,String  mValue,String  nValue){
//
//        super();
//        mContext = context;
//        this.mValue = mValue;
//        this.nValue = nValue;
//    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after){}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count){}
    @Override
    public void afterTextChanged(Editable s){

        if(NormalMethods.isNotEmpty(mValue)){
            if(!mValue.equals(s.toString())){

                Toast.makeText(mContext, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                return;
            }
        }
//        if(NormalMethods.isNotEmpty(mValue) && NormalMethods.isNotEmpty(nValue)){
//
//            if(!mValue.equals(nValue)){
//
//                Toast.makeText(mContext, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }
    }
    /**
     * Context对象
     */
    private Context mContext;
    /**
     * String对象
     */
    private String mValue;
    private String nValue;
}
