package com.weibank.com.weibankapp.utils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import org.apache.http.entity.StringEntity;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2016/3/1.
 */
public class HttpUtilsHelper{
    /**
     *  GET方式请求
     */
    public <T> void get(String requestUrl,final RequestCallBack<T> callBack){
         handlerRequest(HttpRequest.HttpMethod.GET,requestUrl,null,callBack);
    }
    public <T> void get(String requestUrl,Object object,final RequestCallBack<T> callBack){
        handlerRequest(HttpRequest.HttpMethod.GET,requestUrl,getRequestParams(object),callBack);
    }
    ////////////////////////////////////////////////////////////////////////////////////
    /**
     *  之前传的是Map对象,现在改为Object对象,如上所示
     */
//    public <T> void get(String requestUrl,Map params,final RequestCallBack<T> callBack){
//        handlerRequest(HttpRequest.HttpMethod.GET,requestUrl,getRequestParams(params),callBack);
//    }
    ////////////////////////////////////////////////////////////////////////////////////
    /**
     * POST请求方式
     */
    public <T> void post(String requestUrl,final RequestCallBack<T> callBack){
        handlerRequest(HttpRequest.HttpMethod.POST,requestUrl,null,callBack);
    }
    public <T> void post(String requestUrl,Object object,final RequestCallBack<T> callBack){
        handlerRequest(HttpRequest.HttpMethod.POST,requestUrl,getRequestParams(object),callBack);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    /**
     *  之前传的是Map对象,现在改为Object对象,如上所示
     */
//    public <T> void post(String requestUrl,Map params,final RequestCallBack<T> callBack){
//        handlerRequest(HttpRequest.HttpMethod.POST,requestUrl,getRequestParams(params),callBack);
//    }
    /////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 处理请求方法
     */
    private <T> void handlerRequest(HttpRequest.HttpMethod method,String requestUrl,
                                     RequestParams params,final RequestCallBack<T> callBack){
        if(method == HttpRequest.HttpMethod.GET){

            httpUtils.send(HttpRequest.HttpMethod.GET,requestUrl,params,callBack);
        }else if(method == HttpRequest.HttpMethod.POST){

            httpUtils.send(HttpRequest.HttpMethod.POST,requestUrl,params,callBack);
        }
    }
    /**
     * HttpUtilsHelper对象
     */
    private volatile static HttpUtilsHelper instance;
    /**
     * HttpUtils对象
     */
    private static HttpUtils httpUtils = new HttpUtils();
    private HttpUtilsHelper(){}
    /**
     * 单例模式 生成HttpUtilsHelper的唯一实例
     */
    public static HttpUtilsHelper getInstance(){
        if(instance == null){
            synchronized (HttpUtilsHelper.class){
                if(instance == null){

                    instance = new HttpUtilsHelper();
                }
            }
        }
        return instance;
    }
    /**
     * RequestParams相关方法
     */
    private RequestParams getRequestParams(Object object){

        RequestParams params = new RequestParams();
        params.setContentType("application/json");
        params.addBodyParameter("platform", "Android");
        final Gson gson = new Gson();
        try{

            params.setBodyEntity(new StringEntity(gson.toJson(object),"UTF-8"));
        }catch (Exception e){

            e.printStackTrace();
        }
        return params;
    }
    ////////////////////////////////////////////////////////////////////////
    /**
     * RequestParams相关方法  之前传的是Map对象,现在改为Object对象,如上所示
     */
//    private RequestParams getRequestParams(Map map){
//
//        RequestParams params = new RequestParams();
//        params.setContentType("application/json");
//        params.addBodyParameter("platform", "Android");
//        final Gson gson = new Gson();
//        try{
//
//            params.setBodyEntity(new StringEntity(gson.toJson(map)));
//        }catch (Exception e){
//
//            e.printStackTrace();
//        }
//        return params;
//    }
    ///////////////////////////////////////////////////////////////////////////////////////////
}
