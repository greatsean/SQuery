package com.dtds.mobileplatform.util;

import java.math.BigDecimal;

/**
 * 数字相关处理工具
 * Created by lixiaohui on 2016/12/28.
 */
public class NumberUtil {

    private static final String TAG = "NumberUtil";

    /**
     * 安全不报错的强制转换成int
     *
     * @param numStr
     * @return
     */
    public static int parseIntSafely(String numStr) {
        int num = 0;
        if (null == numStr || "".equals(numStr)) {
            return num;
        }
        try {
            num = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            LogUtil.i(TAG, e.getMessage());
        }
        return num;
    }

    /**
     * 安全不报错的强制转换成long
     *
     * @param numStr
     * @return
     */
    public static long parseLongSafely(String numStr) {
        long num = 0;
        if (null == numStr || "".equals(numStr)) {
            return num;
        }
        try {
            num = Long.parseLong(numStr);
        } catch (NumberFormatException e) {
            LogUtil.i(TAG, e.getMessage());
        }
        return num;
    }

    /**
     * 安全不报错的强制转换成double
     *
     * @param numStr
     * @return
     */
    public static float parseFloatSafely(String numStr) {
        float num = 0;
        if (null == numStr || "".equals(numStr)) {
            return num;
        }
        try {
            num = Float.parseFloat(numStr);
        } catch (NumberFormatException e) {
            LogUtil.i(TAG, e.getMessage());
        }
        return num;
    }


    /**
     * 安全不报错的强制转换成double
     *
     * @param numStr
     * @return
     */
    public static double parseDoubleSafely(String numStr) {
        double num = 0;
        if (null == numStr || "".equals(numStr)) {
            return num;
        }
        try {
            num = Double.parseDouble(numStr);
        } catch (NumberFormatException e) {
            LogUtil.i(TAG, e.getMessage());
        }
        return num;
    }

    /**
     * 保留指定位数将String强制转换成double数据
     *
     * @param numStr
     * @param scale
     * @return
     */
    public static double parseDoubleSafely(String numStr, int scale) {
        double num = 0;
        if (null == numStr || "".equals(numStr)) {
            return num;
        }
        try {
            num = new BigDecimal(numStr).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (Exception e) {
            LogUtil.i(TAG, e.getMessage());
        }
        return num;
    }

}
