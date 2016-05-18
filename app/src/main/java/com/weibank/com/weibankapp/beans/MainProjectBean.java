package com.weibank.com.weibankapp.beans;

/**
 * Created by Administrator on 2016/3/29.
 */
public class MainProjectBean{

    public MainProjectBean(){}

    public Integer getMainImg() {
        return mainImg;
    }

    public void setMainImg(Integer mainImg) {
        this.mainImg = mainImg;
    }

    public Integer getViceImg1() {
        return viceImg1;
    }

    public void setViceImg1(Integer viceImg1) {
        this.viceImg1 = viceImg1;
    }

    public Integer getViceImg2() {
        return viceImg2;
    }

    public void setViceImg2(Integer viceImg2) {
        this.viceImg2 = viceImg2;
    }

    public Integer getViceImg3() {
        return viceImg3;
    }

    public void setViceImg3(Integer viceImg3) {
        this.viceImg3 = viceImg3;
    }

    public String getmYearRate() {
        return mYearRate;
    }

    public void setmYearRate(String mYearRate) {
        this.mYearRate = mYearRate;
    }

    /**
     * 主图片
     */
    private Integer mainImg;
    /**
     * 副图片1
     */
    private Integer viceImg1;
    /**
     * 副图片2
     */
    private Integer viceImg2;
    /**
     * 副图片3
     */
    private Integer viceImg3;
    /**
     * 年化收益率
     */
    private String mYearRate;

}
