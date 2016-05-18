package com.weibank.com.weibankapp.beans;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ProjectInfo{

    public ProjectInfo() {}

    public String getmRate() {
        return mRate;
    }

    public void setmRate(String mRate) {
        this.mRate = mRate;
    }

    public String getmHeadProjectId() {
        return mHeadProjectId;
    }

    public void setmHeadProjectId(String mHeadProjectId) {
        this.mHeadProjectId = mHeadProjectId;
    }

    public String getmProjectId() {
        return mProjectId;
    }

    public void setmProjectId(String mProjectId) {
        this.mProjectId = mProjectId;
    }

    public String getmStartRateDate() {
        return mStartRateDate;
    }

    public void setmStartRateDate(String mStartRateDate) {
        this.mStartRateDate = mStartRateDate;
    }

    public Integer getmBgImage() {
        return mBgImage;
    }

    public void setmBgImage(Integer mBgImage) {
        this.mBgImage = mBgImage;
    }

    public String getmProjectDesc1() {
        return mProjectDesc1;
    }

    public void setmProjectDesc1(String mProjectDesc1) {
        this.mProjectDesc1 = mProjectDesc1;
    }

    public String getmProjectDesc2() {
        return mProjectDesc2;
    }

    public void setmProjectDesc2(String mProjectDesc2) {
        this.mProjectDesc2 = mProjectDesc2;
    }

    public Integer getmRepayMonth() {
        return mRepayMonth;
    }

    public void setmRepayMonth(Integer mRepayMonth) {
        this.mRepayMonth = mRepayMonth;
    }

    public String getmProStartDate() {
        return mProStartDate;
    }

    public void setmProStartDate(String mProStartDate) {
        this.mProStartDate = mProStartDate;
    }

    public String getmProEndDate() {
        return mProEndDate;
    }

    public void setmProEndDate(String mProEndDate) {
        this.mProEndDate = mProEndDate;
    }

    /**
     * 盈利率
     */
    private String mRate;
    /**
     * 项目头编号
     */
    private String mHeadProjectId;
    /**
     * 项目编号
     */
    private String mProjectId;
    /***
     *  起始利息日期
     */
    private String mStartRateDate;
    /**
     * 背景图片
     */
    private Integer mBgImage;
    /**
     *  项目销售状态描述1
     */
    private String mProjectDesc1;
    /**
     *  项目销售状态描述2
     */
    private String mProjectDesc2;
    /**
     *  返利月数
     */
    private Integer mRepayMonth;
    /**
     *  起始日期
     */
    private String mProStartDate;
    /**
     *  截止日期
     */
    private String mProEndDate;
}
