package com.dtds.mobileplatform.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Package_Name com.dtds.platform.m.util
 * Date 2017/8/24
 * Created by lixiaohui .
 */

public class UiHelper {
    /**
     * 设置TextView文本走马灯
     *
     * @param textView
     */
    public static void setTextViewMarquee(TextView textView) {
        textView.setMarqueeRepeatLimit(3);
        textView.setSingleLine(true);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        textView.requestFocus();
        textView.setHorizontallyScrolling(true);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
    }

    public static void toast(Context context, String msg) {
        toast(context, msg, Toast.LENGTH_SHORT, Gravity.CENTER, 0, 0);
    }

    public static void toast(Context context, String msg, int duration,
                             int gravity, int xoffset, int yoffset) {
        if (context == null || TextUtils.isEmpty(msg)) {
            return;
        }
        if (context instanceof Activity && ((Activity) context).isFinishing()) {//如果窗口已经关闭就不必要弹出toast了
            return;
        }
        Toast toast = Toast.makeText(context, msg, duration);
        toast.setGravity(gravity, xoffset, yoffset);
        toast.show();
    }

    public static void toast(Context context, int id) {
        if (context != null) {
            toast(context, context.getString(id));
        }
    }

    /**
     * try get host activity from view.
     * views hosted on floating window like dialog and toast will sure return null.
     * @return host activity; or null if not available
     */
    public static Activity getActivityFromView(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

}
