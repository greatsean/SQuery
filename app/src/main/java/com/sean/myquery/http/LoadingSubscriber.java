package com.sean.myquery.http;

import android.content.Context;

import rx.Subscriber;

/*****************************************
 * @description:
 * @author:lixiaohui
 * @date: 2017/9/19
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class LoadingSubscriber extends Subscriber {

    Context ctx;

    public LoadingSubscriber(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Object o) {

    }
}
