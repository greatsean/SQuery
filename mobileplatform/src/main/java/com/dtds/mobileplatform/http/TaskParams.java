package com.dtds.mobileplatform.http;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * Created by lixiaohui on 2017/4/22.
 * 任务需要的参数
 */
public class TaskParams {

    public static final int REQ_METHOD_GET = 0;//get请求
    public static final int REQ_METHOD_POST = 1;//post请求
    public static final int CORE_JSON_TYPE_NONE = 0;//未指定核心内容
    public static final int CORE_JSON_TYPE_CARRIER1 = 1;//核心json内容类型-运营商1
    //必须参数
    String reqPath;//请求路径
    Class<?> clazz;//即将要转换的模型类
    WeakReference<Context> ctxRef;//上下文的弱引用

    //非必须参数
    int reqMethod = REQ_METHOD_POST;//请求方法，默认为POST
    boolean isListResult = false;//返回是否集合列表数据
    TaskListener taskListener;//请求任务监听
    Map<String, String> reqParams;//请求参数
    Map<String, String> reqHeaderParams;//请求头参数
    int coreJsonType = CORE_JSON_TYPE_NONE;//核心json内容类型,默认不设置
    int testFileID = 0;//测试数据的资源id

    public TaskParams(Context ctx, String reqPath, Class<?> clazz) {
        this.reqPath = reqPath;
        this.clazz = clazz;
        this.ctxRef = new WeakReference<Context>(ctx);
    }

}
