package com.weibank.com.weibankapp.constants;

/**
 *  Created by Administrator on 2016/1/13.
 */
public class Constants{
    /**
     *  SharedPrefrences存储数据
     */
    public static final String FIRST_OR_NOT = "firstornot";
    public static final String SHAREDDATAS = "shareddatas";
    /**
     * 存储银行名
     */
    public static final String BANKNAME = "bankname";
    /**
     *  String常量
     */
    public  static final String RETURN_CODE = "return_code";
    public  static final String DATA = "data";
    /**
     * 正则表达式----用户登录、注册、推荐人、验证码、人民币数值
     */
    public static final String USER_PHONE_NUMBER
                          = "^(0\\d{2,3}-\\d{7,8})|([1-9]\\d{4,})|(1[3584]\\d{9})$";
    public static final String USER_PWD_VALICODE = "\\d{6}";
    public static final String TUIJIANREN = "^([u4e00-u9fa5]{1,})|([a-zA-Z]{1,})";
    public static final String RMBNUMBER = "^(\\d{1}\\.\\d{2})|([1-9]\\d{1,6}\\.\\d{2})|([1-9]\\d{0,8})";
    /**
     *  正则表达式----url
     */
    //public static final String URL = "(http://|ftp://|https://|www){1}[^\\u4e00-\\u9fa5\\\\s]*?\\\\.(com|net|cn|me|tw|fr)[^\\u4e00-\\u9fa5\\\\s]*\")";
    public static final String URL = "[a-zA-z]+://[^\\s]*";

    /**
     * 请求银行的状态码
     */
    public static final int REQUESTCODEBANK = 1;
    /**
     * 扫一扫二维码请求码
     */
    public static final int SCANNIN_GREQUEST_CODE = 1;


    /**
     * 与服务器端交互的url(头部分)
     */
    public static final String WEBURL ="http://192.168.1.158:1234/weibank-portal/";
    /**
     *  用户注册URL
     */
    public static final String USER_REGISTER_URL = WEBURL + "app/regist";
    /**
     *  获取验证码
     */
    public static final String GET_VERCODE_URL = WEBURL + "app/getVercode";
    /**
     *  用户登录
     */
    public static final String USERLOGIN_URL = WEBURL + "app/login";
    /**
     *  产品列表
     */
    public static final String PRODUCT_LIST_URL = WEBURL + "app/queryProductInfo";
    /**
     *  账户余额
     */
    public static final String GET_BALANCE_URL = WEBURL + "app/getBalance";
    /**
     *  购买记录
     */
    public static final String TOUZHI_RECORD_URL = WEBURL + "app/queryCustomerFinancialList";
    /**
     *  交易记录
     */
    public static final String JIAOYI_RECORD_URL = WEBURL + "app/getTransList";
    /**
     *  产品详情
     */
    public static final String PRODUCT_XIANGQING_URL = WEBURL + "app/queryProductList";
    /**
     *  赎回
     */
    public static final String SHUHUI_URL = WEBURL + "app/redeem";
    /**
     *  产品收益
     */
    public static final String PRODUCT_SHOUYI_URL = WEBURL + "app/queryInterestByNow";
    /**
     *  绑定银行卡
     */
    public static final String BINDING_BANK_CARD_URL = WEBURL + "app/bindBankCard";
    /**
     *  获取银行卡
     */
    public static final String GET_BANK_CARD_URL = WEBURL + "app/getBankCard";
    /**
     *  app更新
     */
    public static final String APP_UPDATE_URL = WEBURL + "app/appDownload";
    /**
     *  获取充值清单
     */
    public static final String GET_CHONGZHI_LIST_URL =
                                WEBURL + "app/getRechargeWithdrawList";
    /**
     *  查询推荐人列表
     */
    public static final String GET_RECOMMENDER_LIST_URL =
                                WEBURL + "app/getRecommenderList";
    /**
     *  查询主页信息
     */
    public static final String GET_HOME_PAGE_INFO_URL = WEBURL + "app/getHomePageInfo";
    /**
     *  查询我的收益信息
     */
    public static final String GET_MYINCOME_URL = WEBURL + "app/getCustomerIncome";
    /**
     *  app分享好友
     */
    public static final String APP_SHAREFIREND_URL = WEBURL + "app/appRegister";
    /**
     *  修改密码
     */
    public static final String MODIFY_PASSWORD_URL = WEBURL + "app/modifyPassword";
    /**
     *  获取费用
     */
    public static final String GET_CHARGE_NO_URL = WEBURL + "app/getChargeNo";
    /**
     *  获得总奖金
     */
    public static final String GET_TOTAL_BONUS_URL = WEBURL + "app/getTotalBonus";
    /**
     * 获取订单号
     */
    public static final String GET_ORDERID_URL = WEBURL + "app/getOrderId";



    /**
     *  支付相关的url
     */
    public static final String ZHIFUWEBURL ="http://192.168.1.158:1234/caifu-front/";
    /**
     *  预下单接口
     */
    public static final String ZHIFU_GET_CHARGE_NO_URL = ZHIFUWEBURL + "app/getchargeno";
    /**
     *  订单支付通知
     */
    public static final String ZHIFU_NOTIFY_URL = ZHIFUWEBURL + "app/notify";

    /**
     *  支付相关的常量
     */
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String IDENTITYNO = "identityNo";
    public static final String USERID = "userId";
    public static final String BANKCODE = "bankCode";
    public static final String MAC = "mac";
    public static final String MERCHANTID = "merchantId";
    public static final String MERCHANTURL = "merchantUrl";
    public static final String ORDERTIME = "orderTime";


    /**
     *  activity跳转所需常量
     */
    public static final int STARTACTIVITYFORRESULT = 0x110;
}
