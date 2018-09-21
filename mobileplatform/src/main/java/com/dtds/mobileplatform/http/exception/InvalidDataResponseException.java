package com.dtds.mobileplatform.http.exception;

/*****************************************
 * @description:返回数据异常
 * @author:lixiaohui
 * @date: 2017/8/28
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class InvalidDataResponseException extends Exception {

    public InvalidDataResponseException() {
        super("JSON数据异常");
    }

    public InvalidDataResponseException(String message) {
        super(message);
    }
}
