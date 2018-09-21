package com.dtds.mobileplatform.ui.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * 对话框工厂类-抽象对话框的创建过程
 * Created by lixiaohui on 2017/6/27.
 */

public class DialogFactory {

    public static Dialog createLoadingDialog(Context ctx, int loadingStyle) {
        Dialog dialog;
        switch (loadingStyle) {
            case LoadingDialog.LOADING_STYLE_ROTATE_STICK:
                dialog = new LoadingDialog.RotateStickLoadingDialog(ctx);//旋转棍子样式-透明
                break;
            case LoadingDialog.LOADING_STYLE_STICK_BLACKBG:
                dialog = new LoadingDialog.StickBlackbgLoadingDialog(ctx);//旋转棍子样式-黑色半透明
                break;
            case LoadingDialog.LOADING_STYLE_NONE:
                dialog = null;
                break;
            case LoadingDialog.LOADING_STYLE_DEFAULT:
                //默认样式
            default:
                dialog = new LoadingDialog.StickBlackbgLoadingDialog(ctx);
                break;
        }
        return dialog;
    }
}
