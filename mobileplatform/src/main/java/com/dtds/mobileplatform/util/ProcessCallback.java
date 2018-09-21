package com.dtds.mobileplatform.util;

/**
 * 过程（开始和结束）的回调接口
 * Created by lixiaohui on 2017/2/17.
 */

public interface ProcessCallback<T> {
    void onMethodStart(T... args);

    void onMethodEnd(T... args);

}
