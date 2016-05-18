package com.weibank.com.weibankapp.utils;
import java.security.MessageDigest;

/**
 * Created by Administrator on 2016/2/29.
 */
public class MD5Util {

    public static String getMD5(String str){
        String reStr = null;
        try {
            // 创建具有指定算法名称的信息
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());// 使用指定的字节更新摘要。
            byte ss[] = md.digest();// 通过执行诸如填充之类的最终操作完成哈希计算
            reStr = bytes2String(ss);
        }catch(Exception e){

            e.printStackTrace();
        }
        return reStr.toUpperCase() ;
    }
    // 将字节数组转换为字符串
    private static String bytes2String(byte[] aa){
        String hash = "";
        for (int i = 0; i < aa.length; i++) {// 循环数组
            int temp;
            if (aa[i] < 0) // 如果小于零，将其变为正数
                temp = 256 + aa[i];
            else
                temp = aa[i];
            if (temp < 16)
                hash += "0";
            hash += Integer.toString(temp, 16);// 转换为16进制
        }
        //hash = hash.toUpperCase();// 全部转换为大写
        return hash;
    }
}
