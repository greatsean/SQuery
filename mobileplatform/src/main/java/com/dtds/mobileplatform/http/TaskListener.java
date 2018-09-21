package com.dtds.mobileplatform.http;

/**
 * Created by lixiaohui on 2017/4/22.
 * 任务监听
 */
public interface TaskListener<T> {
    int FAILURE_CODE_NO_NET = 1;//无网络
    int FAILURE_CODE_SERVER_TIMEOUT = 2;//请求超时或者网络状况不佳
    int FAILURE_CODE_SERVER_ABNORMAL = 3;//服务端数据异常
    int FAILURE_CODE_UNKNOWN = 4;//未知错误
    int FAILURE_CODE_STATE_500 = 5;//失败
    int FAILURE_CODE_STATE_401 = 6;//令牌失效,令牌过期

    /**
     * 任务开始
     */
    void onTaskStart();

    /**
     * 任务结束
     */
    void onTaskFinish();

    /**
     * 任务失败
     *
     * @param throwable
     * @param failureCode
     * @return 是否处理完毕
     */
    boolean onTaskFailure(Throwable throwable, int failureCode);

    /**
     * 任务成功返回
     *
     * @param resultEntity
     * @return 是否处理完毕
     */
    boolean onTaskSuccess(T resultEntity);
}
