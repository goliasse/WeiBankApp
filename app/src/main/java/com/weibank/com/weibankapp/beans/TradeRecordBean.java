package com.weibank.com.weibankapp.beans;

/**
 * Created by Administrator on 2016/3/31.
 */
public class TradeRecordBean{

    public TradeRecordBean() {
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransStatusName() {
        return transStatusName;
    }

    public void setTransStatusName(String transStatusName) {
        this.transStatusName = transStatusName;
    }

    public String getTransTypeName() {
        return transTypeName;
    }

    public void setTransTypeName(String transTypeName) {
        this.transTypeName = transTypeName;
    }

    /**
     * 交易数额
     */
    private String transAmount;
    /**
     * 交易时间
     */
    private String transDate;
    /**
     * 交易状态
     */
    private String transStatusName;
    /**
     * 交易类型
     */
    private String transTypeName;
}
