package com.dtds.mobileplatform.http.exception;


/*****************************************
 * @description:非预期返回数据
 * @author:lixiaohui
 * @date: 2017/8/28
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class UnexpectedDataResponseException extends Exception {
    public UnexpectedDataResponseException() {
        super("失败");
    }

    public UnexpectedDataResponseException(String message) {
        super(message);
    }
}
