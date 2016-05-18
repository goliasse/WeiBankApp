package com.weibank.com.weibankapp.beans;

/**
 * Created by Administrator on 2016/3/3.
 */
public class UserRegisterInfoBean{

    public UserRegisterInfoBean(){}

    public String getPhone(){

        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValicode() {
        return valicode;
    }

    public void setValicode(String valicode) {
        this.valicode = valicode;
    }

    public String getRecommender() {
        return recommender;
    }

    public void setRecommender(String recommender) {
        this.recommender = recommender;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String valicode;
    /**
     * 推荐人
     */
    private String recommender;
    /**
     * 选择用户协议
     */
    private boolean isSelected;
}
