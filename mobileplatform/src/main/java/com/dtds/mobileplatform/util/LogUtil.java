package com.dtds.mobileplatform.util;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.socks.library.KLog;

/**
 * Package_Name com.dtds.platform.m.util
 * Date 2017/8/25
 * Created by lixiaohui .
 */
public class LogUtil {
    private static String mGlobalTag;
    private static boolean mIsGlobalTagEmpty = true;
    private static boolean IS_SHOW_LOG = true;

    public static void init(boolean isShowLog) {
        init(isShowLog, "WarehouseMS");
    }

    public static void init(boolean isShowLog, @Nullable String tag) {
        IS_SHOW_LOG = isShowLog;
        mGlobalTag = tag;
        mIsGlobalTagEmpty = TextUtils.isEmpty(mGlobalTag);

        KLog.init(isShowLog, tag);
    }

    public static void d(String msg) {
        KLog.d(msg);
    }

    public static void d(String tag, String msg) {
        KLog.d(tag, msg);
    }

    public static void i(String msg) {
        KLog.i(msg);
    }

    public static void i(String tag, String msg) {
        KLog.i(tag, msg);
    }

    public static void w(String msg) {
        KLog.w(msg);
    }

    public static void w(String tag, String msg) {
        KLog.w(tag, msg);
    }

    public static void w(String tag, String msg, Exception e) {
        KLog.w(tag, msg, e);
    }

    /**
     * 使用默认的tag输出error级别错误
     *
     * @param msg
     */
    public static void e(String msg) {
        KLog.e(msg);
    }

    public static void e(String tag, String msg) {
        KLog.e(tag, msg);
    }

}
