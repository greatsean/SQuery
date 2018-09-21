package com.dtds.mobileplatform.ui;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/*****************************************
 * @description:应用相关工具类
 * @author:lixiaohui
 * @date: 2017/9/6
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class AppUtil {

    /**
     * 获取App版本号，如果获取失败返回-1，不会报错
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        if (context == null) {
            return -1;
        }
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi == null ? -1 : pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取App版本名称，如果获取失败返回null，不会报错
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi == null ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
