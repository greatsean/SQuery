package com.dtds.mobileplatform.http;

import android.content.Context;

import com.dtds.mobileplatform.R;
import com.dtds.mobileplatform.ui.BaseActivity;
import com.dtds.mobileplatform.util.UiHelper;
import com.dtds.mobileplatform.util.ActivityMgr;

import java.lang.ref.WeakReference;

/**
 * 简单通用的任务处理监听
 *
 * @param <T>
 */
public abstract class SimpleTaskListener<T> implements TaskListener<T> {
    protected WeakReference<Context> mCtxRef;//上下文的弱引用
    private int mDialogStyle;//对话框样式
    private static final int DIALOG_STYLE_GIVEUP_SET = Integer.MAX_VALUE;//放弃设置对话框样式

    public SimpleTaskListener(Context ctx) {
        this(ctx, DIALOG_STYLE_GIVEUP_SET);//默认放弃透传加载框样式
    }

    public SimpleTaskListener(Context ctx, int dialogStyle) {
        mCtxRef = new WeakReference<Context>(ctx);
        mDialogStyle = dialogStyle;
    }

    @Override
    public void onTaskStart() {
        Context context = mCtxRef.get();
        if (context instanceof BaseActivity) {
            if (DIALOG_STYLE_GIVEUP_SET == mDialogStyle) {//放弃传递对话框样式
                ((BaseActivity) context).showLoadingDialog();//使用默认对话框样式
            } else {
                ((BaseActivity) context).showLoadingDialog(mDialogStyle);//将对话框样式透传给下一级
            }
        }
    }

    @Override
    public void onTaskFinish() {
        Context context = mCtxRef.get();
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).dismissLoadingDialog();
        }
    }

    @Override
    public boolean onTaskFailure(Throwable throwable, int failureCode) {
        Context context = mCtxRef.get();
        if (!ActivityMgr.activityIsFinish(context)) {
            switch (failureCode) {
                case FAILURE_CODE_NO_NET://无网络
                    UiHelper.toast(context, R.string.ulib_exception_no_network);
                    return true;
                case FAILURE_CODE_SERVER_ABNORMAL://服务端数据异常
                    UiHelper.toast(context, R.string.ulib_exception_server_abnormal);
                    return true;
                case FAILURE_CODE_SERVER_TIMEOUT://请求超时或者网络状况不佳
                    UiHelper.toast(context, R.string.ulib_request_error);
                    return true;
                case FAILURE_CODE_UNKNOWN://未知错误
                    UiHelper.toast(context, R.string.ulib_exception_server_busy);
                    return true;
            }
        }
        return false;
    }

}
