package com.dtds.mobileplatform.http.exception;

/*****************************************
 * @description:登录过期异常
 * @author:lixiaohui
 * @date: 2017/8/28
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class LoginExpiredException extends Exception {
    public LoginExpiredException() {
        super("登录已过期");
    }

    public LoginExpiredException(String message) {
        super(message);
    }
}
