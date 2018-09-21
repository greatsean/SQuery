package com.dtds.mobileplatform.ui.dialog;


import android.app.Dialog;
import android.content.Context;

import com.dtds.mobileplatform.R;


/**
 * 本app内所有用到的加载框样式
 * Created by lixiaohui on 2017/6/27.
 */
public class LoadingDialog extends Dialog {

    public static final int LOADING_STYLE_NONE = -1;//无对话框
    public static final int LOADING_STYLE_DEFAULT = 0;//默认样式
    public static final int LOADING_STYLE_ROTATE_STICK = 1;//旋转棍子-半透明黑色背景
    public static final int LOADING_STYLE_STICK_BLACKBG = 3;//旋转棍子-透明背景


    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.setCanceledOnTouchOutside(false);
    }

    public LoadingDialog(Context context) {
        this(context, R.style.ulib_loading_dialog_transparent);
    }

    public static final class RotateStickLoadingDialog extends LoadingDialog {

        public RotateStickLoadingDialog(Context context) {
            super(context, R.style.ulib_loading_dialog_transparent);//全透明
            setContentView(R.layout.ulib_view_dialog_load_tick);
        }
    }

    public static final class StickBlackbgLoadingDialog extends LoadingDialog {

        public StickBlackbgLoadingDialog(Context context) {
            super(context, R.style.ulib_loading_dialog_translucent);//半透明
            setContentView(R.layout.ulib_view_dialog_load_tick);
        }
    }

//    public static final class BlueEyeLoadingDialog extends LoadingDialog {
//        private ImageView image;
//        private AnimationDrawable background;
//
//        public BlueEyeLoadingDialog(Context context) {
//            super(context, R.style.ulib_loading_dialog_transparent);//全透明
//            setContentView(R.layout.ulib_view_dialog_load_eye);
//            image = (ImageView) findViewById(R.id.iv_wait_dialog);
//        }
//
//
//        @Override
//        public void show() {
//            super.show();
//            background = (AnimationDrawable) image.getBackground();
//            background.start();
//        }
//
//        @Override
//        public void dismiss() {
//            background.stop();
//            super.dismiss();
//        }
//    }

}
