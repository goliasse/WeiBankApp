package com.weibank.com.weibankapp.constants;

/**
 * Created by Administrator on 2016/3/1.
 */
public interface UserOperationParams {

    String BACK_RETURN_CODE = "0000";

    int LOGIN_SUCCESS = 0x1020;
    int LOGIN_FAILED = 0x1030;

    int REGISTER_SUCCESS = 0x2020;
    int REGISTER_FAILED = 0x2030;

    int GETPROJECTVALUE = 0x9005;

    int TRADERECORD_PULLTOREFRESH = 0x8070;
    int TRADERECORD_UPTOLOADING = 0x8080;
    int TRADERECORDFAILED = 0x8090;

    int GETPROJECTINFO_SUCCESS = 0x5050;
    int GETPROJECTINFO_FAILED = 0x5060;

    int GETGOUMAIRECORD_SUCCESS = 0x3030;
    int GETGOUMAIRECORD_FAILED = 0x3040;
}
