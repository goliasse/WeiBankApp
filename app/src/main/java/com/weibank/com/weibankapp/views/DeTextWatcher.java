package com.weibank.com.weibankapp.views;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.weibank.com.weibankapp.R;
import com.weibank.com.weibankapp.constants.Constants;
import com.weibank.com.weibankapp.utils.NormalMethods;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Administrator on 2016/1/25.
 */
public class DeTextWatcher implements TextWatcher
                                       //,View.OnKeyListener
                                       {

    public DeTextWatcher(Context context,EditText editText,
                          TextView textView){

        this.editText = editText;
        this.textView = textView;
        this.context = context;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start,
                                   int count, int after){}
    @Override
    public void onTextChanged(CharSequence s, int start,
                               int before, int count){

//        String content = this.editText.getText().toString();
//        /**
//         * 获取小数点后面的位数
//         */
//        if(NormalMethods.isNotEmpty(content)){
//
//            double dou = Double.parseDouble(content);
//            if(dou == 0 || dou < 2){
//
//                this.editText.setText("2.00");
//                Toast.makeText(this.context, "贰元起取现", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if(content.contains(".")){
//
//                String str = content.substring(content.indexOf(".") + 1, content.length());
//                if(str.length() > 2){
//
//                    Toast.makeText(this.context, "小数点后面只能保留两位",
//                                   Toast.LENGTH_SHORT).show();
//                    String zen = content.substring(0,content.indexOf(".") + 3);
//                    this.editText.setText(zen);
//                    return;
//                }else{
//
//                    this.textView.setText(NormalMethods.toChineseCurrency
//                                          (Double.parseDouble(content)));
//                }
//            }else{
//
//                content += ".00";
//                this.editText.setText(content);
//                this.textView.setText(NormalMethods.toChineseCurrency
//                        (Double.parseDouble(content)));
//            }
//        }else{
//            this.textView.setText("");
//        }
    }
    @Override
    public void afterTextChanged(Editable s){

        if (!s.toString().equals(currentAmount)){

            this.editText.removeTextChangedListener(this);
            String replaceable = String.format("[%s, \\s.]",
                    NumberFormat.getCurrencyInstance
                            (Locale.CHINA).getCurrency()
                            .getSymbol(Locale.CHINA));
            String cleanString = s.toString().replaceAll(replaceable, "");
            if(cleanString.equals("")
                    || new BigDecimal(cleanString).toString().equals("0")){

                this.editText.setText(null);
                this.textView.setText(null);
            }else{

                double parsed = Double.parseDouble(cleanString);
                String formatted = NumberFormat.getCurrencyInstance
                                   (Locale.CHINA).format((parsed / 100));
                currentAmount = formatted;
                String rmbValue = formatted.trim().replace("￥","");
                this.textView.setText(NormalMethods.toChineseCurrency
                        (Double.parseDouble(rmbValue)));
                this.editText.setText(formatted);
                this.editText.setSelection(formatted.length());
            }
            this.editText.addTextChangedListener(this);
        }
    }
//    @Override
//    public boolean onKey(View v, int keyCode, KeyEvent event){
//        if(keyCode == KeyEvent.KEYCODE_DEL){
//            String cui = this.editText.getText().toString();
//            if(cui.length() > 0){
//
//                this.editText.setText(cui.substring(0,cui.length()-1));
//            }
//            return true;
//        }
//        return false;
//    }
    /**
     * EditText对象
     */
    private EditText editText;
    /**
     * TextView对象
     */
    private TextView textView;
    /**
     * String对象
     */
    private String  currentAmount = "";
    /**
     * Context对象
     */
    private Context context;
}
