package com.weibank.com.weibankapp.beans;

/**
 * Created by Administrator on 2016/4/11.
 */
public class UserBean{

    private static UserBean userBean;

    public static UserBean getInstance(){
        if(userBean == null){

            userBean = new UserBean();
        }
        return userBean;
    }

    private UserBean(){}
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCasId() {
        return casId;
    }

    public void setCasId(String casId) {
        this.casId = casId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String  customerId;
    private String  casId;
    private String  name;
    private String  mobile;
}
