/*
 * 文件名：JsonUtils.java
 * 版权：Copyright 2000-2100 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：订单可视
 * 修改人：  zWX317775
 * 修改时间：2016年3月16日
 * 修改内容：新增
 */
package com.dtds.mobileplatform.util;

import org.json.JSONObject;

/*****************************************
 * @Author:lixiaohui
 * @Description: json常用解析方法工具类
 * @Date: 2017/8/25
 * @Company:深圳动态网络科技有限公司
 *****************************************/
public class JsonUtil {

    private static final String LOG_TAG = JsonUtil.class.getSimpleName();

//    public static <T> List<T> parseJson2List(String json, Class<T> mClass) {
//        if (json == null) {
//            LogUtil.i(LOG_TAG, "json can't be empty!");
//            return null;
//        }
//        if (mClass == null) {
//            LogUtil.i(LOG_TAG, "mClass can't be empty!");
//            return null;
//        }
//        return JSON.parseArray(json, mClass);
//    }
//
//    @SuppressWarnings({"unchecked", "rawtypes"})
//    public static HashMap<String, Object> parseJson2Map(String json) {
//        if (json == null) {
//            LogUtil.e(LOG_TAG, "json can't be empty!");
//            return null;
//        }
//
//        try {
//            return (HashMap) JSON.parseObject(json, new TypeReference() {
//            }, new Feature[0]);
//        } catch (Exception e) {
//            LogUtil.e(LOG_TAG, "mClass parseArray error!");
//        }
//
//        return null;
//    }
//
//    public static String parseObject2Json(Object object) {
//        if (object == null) {
//            LogUtil.e(LOG_TAG, "object can't be empty!");
//            return null;
//        }
//
//        try {
//            return JSON.toJSONString(object);
//        } catch (Exception e) {
//            LogUtil.e(LOG_TAG, "mClass parseArray error!");
//        }
//
//        return null;
//    }
//
//    public static <T> T parseJson2Object(String json, Class<T> mClass) {
//        if (json == null) {
//            LogUtil.e(LOG_TAG, "json can't be empty!");
//            return null;
//        }
//        if (mClass == null) {
//            LogUtil.e(LOG_TAG, "mClass can't be empty!");
//            return null;
//        }
//
//        try {
//            return (T) JSON.parseObject(json, mClass);
//        } catch (Exception e) {
//            LogUtil.e(LOG_TAG, "mClass parseArray error!");
//        }
//        return null;
//    }

    /**
     * 获取核心json内容-运营商1
     *
     * @param originalStr
     * @return
     * @throws Exception
     */
    public static String extractCoreJsonCarrier1(String originalStr) throws Exception {
        //    {"status":"200","msg":null,"data":[{"whName":"捷风仓600001","whCode":"JF600001"}],"code":0,"codeMsg":null}
        JSONObject jsonObject = new JSONObject(originalStr);
        String coreJsonStr = jsonObject.getString("data");
        return coreJsonStr;
    }

}
