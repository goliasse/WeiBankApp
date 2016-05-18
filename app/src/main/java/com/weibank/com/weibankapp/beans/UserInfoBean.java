package com.weibank.com.weibankapp.beans;

/**
 * Created by Administrator on 2016/3/1.
 */
public class UserInfoBean {

    public UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfo{
        /**
         * 身份证号码
         */
        private String idcard;
        /**
         * 用户姓名
         */
        private String name;
        /**
         * 手机号
         */
        private String mobile;
        /**
         * 返回信息
         */
        private String return_message;
        /**
         *  返回码
         */
        private String return_code;

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
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

        public String getReturn_message() {
            return return_message;
        }

        public void setReturn_message(String return_message) {
            this.return_message = return_message;
        }

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }
    }
}
