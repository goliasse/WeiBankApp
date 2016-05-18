package com.weibank.com.weibankapp.beans;

/**
 *  Created by Administrator on 2016/4/6.
 */
public class GoumaiInfoBean{

    public GoumaiInfoBean(){

    }

    public String getmRate1() {
        return mRate1;
    }

    public void setmRate1(String mRate1) {
        this.mRate1 = mRate1;
    }

    public String getmRate2() {
        return mRate2;
    }

    public void setmRate2(String mRate2) {
        this.mRate2 = mRate2;
    }

    public String getmProject() {
        return mProject;
    }

    public void setmProject(String mProject) {
        this.mProject = mProject;
    }

    public String getmStartRateMoney() {
        return mStartRateMoney;
    }

    public void setmStartRateMoney(String mStartRateMoney) {
        this.mStartRateMoney = mStartRateMoney;
    }

    public String getmMonths() {
        return mMonths;
    }

    public void setmMonths(String mMonths) {
        this.mMonths = mMonths;
    }

    public String getmAmount() {
        return mAmount;
    }

    public void setmAmount(String mAmount) {
        this.mAmount = mAmount;
    }
    /**
     *  利率1
     */
    private String mRate1;
    /**
     *  利率2
     */
    private String mRate2;
    /**
     * 项目名称
     */
    private String mProject;
    /**
     *  起息下线
     */
    private String mStartRateMoney;
    /**
     *  月数
     */
    private String mMonths;
    /**
     *  已投金额
     */
    private String mAmount;
}
