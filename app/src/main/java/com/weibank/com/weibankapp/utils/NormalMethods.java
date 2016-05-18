package com.weibank.com.weibankapp.utils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.SuperToast;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/1/18.
 */
public class NormalMethods{

    /**
     *  退出当前activity,跳转到另一activity
     */
    public static void exitAndSwicthActivity(Activity mActivity,
                                               Class<?> mClass,boolean mIsFinish){
        Intent intent = new Intent();
        intent.setClass(mActivity,mClass);
        mActivity.startActivity(intent);
        if(mIsFinish){

            mActivity.finish();
        }
    }
    public static void exitAndSwicthActivity(Activity mActivity,
                                               String stra,String strb){
        Intent intent = new Intent();
        intent.setClassName(stra, strb);
        mActivity.startActivity(intent);
    }
    public static void exitAndSwicthActivity(Activity mActivity){

        String stra = "com.android.settings";
        String strb = "com.android.settings.Settings";
        Intent intent = new Intent();
        intent.setClassName(stra, strb);
        mActivity.startActivity(intent);
    }
    /**
     * 人民币转成大写
     */
    public static String hangeToBig(double value){
        // 段内位置表示
        char[] hunit = { '拾', '佰', '仟' };
        // 段名表示
        char[] vunit = { '万', '亿' };
        // 数字表示
        char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
        // 转化成整形
        long midVal = (long) (value * 100);
        // 转化成字符串
        String valStr = String.valueOf(midVal);
        // 取整数部分
        String head = valStr.substring(0, valStr.length() - 2);
        //取小数部分
        String rail = valStr.substring(valStr.length() - 2);
        // 整数部分转化的结果
        String prefix = "";
        // 小数部分转化的结果
        String suffix = "";
        // 处理小数点后面的数
        if(rail.equals("00")){
            // 如果小数部分为0
            suffix = "整";
        }else{

            suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
        }

        // 处理小数点前面的数
        char[] chDig = head.toCharArray(); //把整数部分转化成字符数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeroSerNum = 0; // 连续出现0的次数
        for (int i = 0; i < chDig.length; i++){ // 循环处理每个数字
            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int vidx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0')
            { // 如果当前字符是0
                zeroSerNum++; // 连续0次数递增
                if (zero == '0')
                { // 标志
                    zero = digit[0];
                }
                else if (idx == 0 && vidx > 0 && zeroSerNum < 4)
                {
                    prefix += vunit[vidx - 1];
                    zero = '0';
                }
                continue;
            }
            zeroSerNum = 0; // 连续0次数清零
            if (zero != '0')
            { // 如果标志不为0,则加上,例如万,亿什么的
                prefix += zero;
                zero = '0';
            }
            prefix += digit[chDig[i] - '0']; // 转化该数字表示
            if (idx > 0)
                prefix += hunit[idx - 1];
            if (idx == 0 && vidx > 0)
            {
                prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
            }
        }
        if (prefix.length() > 0)
        prefix += '圆'; // 如果整数部分存在,则有圆的字样
        return prefix + suffix; // 返回正确表示
    }
    /**
     * 人民币大小写转换
     */
    public static String toChineseCurrency(Number o){

        String s = new DecimalFormat("#.00").format(o);
        System.out.println(s);
        s = s.replaceAll("\\.", "");
        char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
        String unit = "仟佰拾兆仟佰拾亿仟佰拾万仟佰拾元角分";
        int l = unit.length();
        StringBuffer sb = new StringBuffer(unit);
        for(int i = s.length() - 1; i >= 0; i--){

            sb = sb.insert(l - s.length() + i, digit[(s.charAt(i) - 0x30)]);
        }
        s = sb.substring(l - s.length(), l + s.length());
        s = s.replaceAll("零[拾佰仟]", "零").replaceAll("零{2,}", "零")
                                             .replaceAll("零([兆万元])", "$1")
                                             .replaceAll("零[角分]", "");
        if(s.endsWith("角")){

            s += "零分";
        }
        if(!s.contains("角") && !s.contains("分") && s.contains("元")){

            s += "整";
        }
        if(s.contains("分") && !s.contains("整") && !s.contains("角")){

            s = s.replace("元", "元零");
        }
        return s;
    }
    /**
     * 判断文本是否为空
     */
    public static boolean isNotEmpty(String str){

        return str != null && str.length() != 0;
    }
    /**
     * 存储状态码
     */
    public static void onSaveCode(Context context, String flag){

        SharedPreferences shares = context.getSharedPreferences("bank_id", 0);
        SharedPreferences.Editor edtor = shares.edit();
        edtor.putString("bank_id", flag);
        edtor.commit();
    }
    /**
     * 读取状态码
     */
    public static String onReadIsCode(Context context){

        SharedPreferences shares = context.getSharedPreferences("bank_id", 0);
        return shares.getString("bank_id", null);
    }
    /**
     * 连接网络操作
     */
    public static boolean isNetWorkConnected(Context context){

        ConnectivityManager manager = (ConnectivityManager)
                                      context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.isConnected();
    }
    /**
     * 连接Wi-Fi
     */
    public static boolean isWiFiConnected(Context context){

        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info != null && info.isAvailable() && info.isConnected();
    }
    /**
     * 连接Mobile
     */
    public static boolean isMobileConnected(Context context){

        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return info != null && info.isAvailable() && info.isConnected();
    }
    /**
     * 判断连接网络的类型
     */
    public static int getNetWorkType(Context context){
        try{
            ConnectivityManager manager = (ConnectivityManager)
                                context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if(info != null && info.isAvailable() && info.isConnected()){

                return info.getType();
            }
        }catch(Exception e){

            return -1;
        }
        return -1;
    }
    /**
     * 安装apk文件方法
     */
    public static void installApk(Context context,File file){

        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri,"application/vnd.android.package-archive");
        context.startActivity(intent);
    }
    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate(){

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    /**
     * 把一位数改变为两位数
     */
    public static String one2Two(int i){

        String value = i + "";
        if(value.length() < 2){

            value = "0" + i;
        }
        return value;
    }
    /**
     *  设置小数点后两位方法
     */
    public static void setPricePoint(final EditText editText,final TextView textView){
        editText.addTextChangedListener(new TextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count){
                if(s.toString().contains(".")){
                    if(s.length() - 1 - s.toString().indexOf(".") > 2){

                        String mValue = s.toString().substring(0, s.toString().indexOf(".") + 3);
//                        s = s.toString().subSequence(0,
//                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                        textView.setText(toChineseCurrency(Double.parseDouble(mValue)));
                    }
                }
                if(s.toString().trim().substring(0).equals(".")){

                    String mValue = "0" + s.toString();
                    //s = "0" + s;
                    editText.setText(mValue);
                    editText.setSelection(2);
                    textView.setText(toChineseCurrency(Double.parseDouble(mValue)));
                }
                if(s.toString().startsWith("0")
                        && s.toString().trim().length() > 1){
                    if(!s.toString().substring(1, 2).equals(".")){

                        String mValue = s.toString().substring(0, 1);
                        //editText.setText(s.subSequence(0, 1));
                        editText.setText(mValue);
                        editText.setSelection(1);
                        textView.setText(toChineseCurrency(Double.parseDouble(mValue)));
                        return;
                    }
                }
                if(!isNotEmpty(s.toString())){

                    textView.setText(null);
                }
//                editText.addTextChangedListener(this);
//                textView.addTextChangedListener(this);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after){}
            @Override
            public void afterTextChanged(Editable s){}
        });
    }
    /**
     * 显示吐司框方法
     */
    public static void toastShowContent(Context context,String str){

        SuperToast toast = new SuperToast(context);
        toast.setText(str);
        toast.setTextSize(SuperToast.TextSize.LARGE);
        toast.setTextColor(Color.parseColor("#ffffff"));
        //toast.setTextColor(Color.BLUE);
        //toast.setBackground(Color.parseColor("#8856ff"));
        toast.setBackground(SuperToast.Background.GRAY);
        toast.setDuration(SuperToast.Duration.MEDIUM);
        toast.setAnimations(SuperToast.Animations.FADE);
        toast.setGravity(Gravity.BOTTOM,0,0);
        //toast.setIcon(R.mipmap.ic_launcher, SuperToast.IconPosition.LEFT);
        toast.show();
    }
}
