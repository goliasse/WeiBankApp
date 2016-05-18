package com.weibank.com.weibankapp.beans;

/**
 * Created by Administrator on 2016/1/28.
 */
public class PayParams{

    public PayParams(){}

    public double getPayMoneyNum(){
        return payMoneyNum;
    }
    public void setPayMoneyNum(double payMoneyNum){
        this.payMoneyNum = payMoneyNum;
    }

    public String getPayIdCard(){
        return payIdCard;
    }
    public void setPayIdCard(String payIdCard){
        this.payIdCard = payIdCard;
    }

    public String getPayPerson(){
        return payPerson;
    }
    public void setPayPerson(String payPerson){
        this.payPerson = payPerson;
    }

    public String getPayBankCard(){
        return payBankCard;
    }
    public void setPayBankCard(String payBankCard){
        this.payBankCard = payBankCard;
    }

    public String getPayPhoneNum(){
        return payPhoneNum;
    }
    public void setPayPhoneNum(String payPhoneNum){
        this.payPhoneNum = payPhoneNum;
    }
    /**
     * 支付金额
     */
    private double payMoneyNum;
    /**
     * 身份证号
     */
    private String payIdCard;
    /**
     * 支付人名
     */
    private String payPerson;
    /**
     * 银行卡号
     */
    private String payBankCard;
    /**
     * 支付电话
     */
    private String payPhoneNum;
}
