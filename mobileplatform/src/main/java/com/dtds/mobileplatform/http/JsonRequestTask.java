package com.dtds.mobileplatform.http;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dtds.mobileplatform.http.exception.InvalidDataResponseException;
import com.dtds.mobileplatform.http.exception.LoginExpiredException;
import com.dtds.mobileplatform.http.exception.UnexpectedDataResponseException;
import com.dtds.mobileplatform.util.JsonUtil;
import com.dtds.mobileplatform.util.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.RequestCall;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * json请求任务类
 * Created by lixiaohui on 2017/4/22.
 */
public class JsonRequestTask {

    private static final String TAG = "JsonRequestTask";
    TaskParams mTaskParams;
    private RequestCall mReqCall;

    public JsonRequestTask(Context ctx, String reqPath, Class<?> clazz) {
        this(new TaskParams(ctx, reqPath, clazz));
    }

    public JsonRequestTask(final TaskParams taskParams) {
        mTaskParams = taskParams;
        initCall();
    }

    private void initCall() {
        switch (mTaskParams.reqMethod) {
            case TaskParams.REQ_METHOD_POST:
                mReqCall = OkHttpUtils
                        .get()
                        .headers(mTaskParams.reqHeaderParams)
                        .url(mTaskParams.reqPath).params(mTaskParams.reqParams)
                        .build();
            case TaskParams.REQ_METHOD_GET:
            default:
                mReqCall = OkHttpUtils
                        .post()
                        .headers(mTaskParams.reqHeaderParams)
                        .url(mTaskParams.reqPath).params(mTaskParams.reqParams)
                        .build();
        }
    }

    /**
     * 提取核心的json内容
     *
     * @param originalStr
     * @return
     */
    public String extractCoreJson(String originalStr) throws Exception {
        switch (mTaskParams.coreJsonType) {
            case TaskParams.CORE_JSON_TYPE_CARRIER1:
                return JsonUtil.extractCoreJsonCarrier1(originalStr);
        }
        return originalStr;
    }

    /**
     * 检查json内容
     *
     * @param originalStr
     * @return
     */
    public void checkJson(String originalStr) throws Exception {
        //empty implement
    }

    /**
     * 异步启动当前任务
     */
    public void asyncStart() {
        if (mTaskParams.taskListener != null) {
            mTaskParams.taskListener.onTaskStart();
        }
        LogUtil.i("parseNetworkResponse: path>>" + mTaskParams.reqPath);
        LogUtil.i("parseNetworkResponse: params>>" + mTaskParams.reqParams);
        LogUtil.i("parseNetworkResponse: header>>" + mTaskParams.reqHeaderParams);
        mReqCall.execute(new RespCallback());
    }

    /**
     * 同步开启当前任务
     *
     * @param <T>
     * @return
     */
    public <T> T syncStart() throws Exception {
        Response response = mReqCall.execute();
        //同步拿到返回json信息
        String originJson = response.body().string();
        return (T) parseJson(originJson);
    }

    /**
     * 取消请求任务
     */
    public void cancel() {
        mReqCall.cancel();
    }

    /**
     * 请求返回的回调
     *
     * @param <T>
     */
    public class RespCallback<T> extends Callback<T> {

        @Override
        public T parseNetworkResponse(okhttp3.Response response, int id) throws Exception {
            LogUtil.d("parseNetworkResponse() called with: response = [" + response + "], id = [" + id + "]");
            String originJson = response.body().string();
            Object resultObj = null;
            resultObj = parseJson(originJson);
            return (T) resultObj;
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.d("onError() called with: call = [" + call + "], e = [" + e + "], id = [" + id + "]");
            if (mTaskParams.taskListener != null) {
                if (e instanceof UnexpectedDataResponseException) {//非预期数据
                    mTaskParams.taskListener.onTaskFailure(e, TaskListener.FAILURE_CODE_STATE_500);
                } else if (e instanceof LoginExpiredException) {//登录过期
                    mTaskParams.taskListener.onTaskFailure(e, TaskListener.FAILURE_CODE_STATE_401);
                } else if (e instanceof InvalidDataResponseException) {//非法json数据
                    mTaskParams.taskListener.onTaskFailure(e, TaskListener.FAILURE_CODE_SERVER_ABNORMAL);
                } else if (e instanceof SocketTimeoutException) {//超时
                    mTaskParams.taskListener.onTaskFailure(e, TaskListener.FAILURE_CODE_SERVER_TIMEOUT);
                } else {
                    mTaskParams.taskListener.onTaskFailure(e, TaskListener.FAILURE_CODE_NO_NET);
                }
                //任务结束
                mTaskParams.taskListener.onTaskFinish();
            }
        }

        @Override
        public void onResponse(T response, int id) {
            LogUtil.d("onResponse() called with: response = [" + response + "], id = [" + id + "]");
            if (mTaskParams.taskListener != null) {//是否有设置回调接口
                if (response != null) {//成功转换成Java bean实体合法的json
                    mTaskParams.taskListener.onTaskSuccess(response);
                } else {//转换失败
                    mTaskParams.taskListener.onTaskFailure(new InvalidDataResponseException(), TaskListener.FAILURE_CODE_SERVER_ABNORMAL);
                }
                //任务结束
                mTaskParams.taskListener.onTaskFinish();
            }
        }

        @Override
        public void onAfter(int id) {
            super.onAfter(id);
            LogUtil.d("onAfter() called with: id = [" + id + "]");
        }
    }

    private Object parseJson(String originJson) throws Exception {
        LogUtil.i("parseJson>>origin json: " + originJson);
        //检查json是否符合预期的
        checkJson(originJson);
        //提取核心的json
        String coreJson = extractCoreJson(originJson);
        LogUtil.i("parseJson>>core json: " + coreJson);
        if (mTaskParams.clazz.isAssignableFrom(String.class)) {
            return coreJson;
        }
        Object resultObj;
        if (mTaskParams.isListResult) {
            resultObj = JSON.parseArray(coreJson, mTaskParams.clazz);
        } else {
            resultObj = JSON.parseObject(coreJson, mTaskParams.clazz);
        }
        return resultObj;
    }

    /**
     * JsonRequestTask的构建类
     */
    public static class Builder {

        private final TaskParams P;

        /**
         * 创建JsonRequestTask的构造
         *
         * @param ctx     上下文
         * @param reqPath 请求路径
         * @param clazz   类模板字节码对象
         */
        public Builder(Context ctx, String reqPath, Class<?> clazz) {
            P = new TaskParams(ctx, reqPath, clazz);
        }

        /**
         * 设置请求方式方法
         *
         * @param reqMethod
         * @return
         */
        public Builder setReqMethod(int reqMethod) {
            P.reqMethod = reqMethod;
            return this;
        }

        /**
         * 设置返回结果是否是集合类型
         *
         * @param isListResult
         * @return
         */
        public Builder setIsListResult(boolean isListResult) {
            P.isListResult = isListResult;
            return this;
        }

        /**
         * 设置请求参数
         *
         * @param reqParams
         * @return
         */
        public Builder setReqParams(Map<String, String> reqParams) {
            P.reqParams = reqParams;
            return this;
        }

        /**
         * 插入一个请求参数
         *
         * @param key
         * @param value
         * @return
         */
        public Builder putReqParam(String key, String value) {
            if (P.reqParams == null) {
                P.reqParams = new HashMap<>();
            }
            P.reqParams.put(key, value);
            return this;
        }

        /**
         * 设置请求头参数
         *
         * @param reqHeaderParams
         * @return
         */
        public Builder setReqHeaderParams(Map<String, String> reqHeaderParams) {
            P.reqHeaderParams = reqHeaderParams;
            return this;
        }

        /**
         * 插入一个请求头参数
         *
         * @param key
         * @param value
         * @return
         */
        public Builder putReqHeaderParam(String key, String value) {
            if (P.reqHeaderParams == null) {
                P.reqHeaderParams = new HashMap<>();
            }
            P.reqHeaderParams.put(key, value);
            return this;
        }

        /**
         * 设置任务的回调监听
         *
         * @param taskListener
         * @return
         */
        public Builder setTaskListener(TaskListener taskListener) {
            P.taskListener = taskListener;
            return this;
        }

        /**
         * 设置核心的json内容数据
         *
         * @param coreJsonType
         * @return
         */
        public Builder setCoreJsonType(int coreJsonType) {
            P.coreJsonType = coreJsonType;
            return this;
        }

        /**
         * 设置加载测试数据 用的资源id
         *
         * @param testFileID
         * @return
         */
        public Builder setTestFileID(int testFileID) {
            P.testFileID = testFileID;
            return this;
        }

        public String extractCoreJson(String originalStr) throws Exception {
            return originalStr;
        }

        /**
         * 检查json内容
         *
         * @param originalStr
         * @return
         */
        public void checkJson(String originalStr) throws Exception {
            //empty implement
        }

        public JsonRequestTask build() {
            return new JsonRequestTask(P) {
                @Override
                public String extractCoreJson(String originalStr) throws Exception {
                    boolean unspecifyCoreType = mTaskParams.coreJsonType == TaskParams.CORE_JSON_TYPE_NONE;//是否没有指定核心json类型
                    //如果是去取Builder类中的extractCoreJson方法，如果指定了，就使用原有逻辑
                    return unspecifyCoreType ? Builder.this.extractCoreJson(originalStr) : super.extractCoreJson(originalStr);
                }

                @Override
                public void checkJson(String originalStr) throws Exception {
                    Builder.this.checkJson(originalStr);
                }
            };
        }
    }


}
