package com.sean.myquery.http;

import com.sean.myquery.bean.BaseBean;

import rx.functions.Func1;

/*****************************************
 * @description:
 * @author:lixiaohui
 * @date: 2017/9/19
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class BaseBeanFunc1<T> implements Func1<BaseBean<T>, T> {

    @Override
    public T call(BaseBean<T> baseBean) {
//        Log.e("error", baseBean.getRows() + "");
//        if (httpResult.getCode() != 0) {
//            throw new ApiException(httpResult.getCode());
//        }
        return baseBean.getRows();
    }
}
