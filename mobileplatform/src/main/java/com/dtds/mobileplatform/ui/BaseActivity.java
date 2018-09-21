/*
 * 文件名：BasicActivity.java
 * 版权：Copyright 2000-2100 Huawei Tech. Co. Ltd. All Rights Reserved. 
 * 描述：订单可视
 * 修改人：  zWX317775
 * 修改时间：2016年3月9日
 * 修改内容：新增 
 */
package com.dtds.mobileplatform.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dtds.mobileplatform.ui.dialog.DialogFactory;
import com.dtds.mobileplatform.ui.dialog.LoadingDialog;
import com.dtds.mobileplatform.util.ActivityMgr;
import com.dtds.mobileplatform.util.LogUtil;

import butterknife.ButterKnife;


/*****************************************
 * @Author:lixiaohui
 * @Description: 所有activity基类
 * @Date: 2017/8/25
 * @Company:深圳动态网络科技有限公司
 *****************************************/
public abstract class BaseActivity extends AppCompatActivity {
    private Dialog mLoadingDialog;//数据加载框
    private int mCallLoadingCount = 0;//调用显示加载框的次数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("ACTIVITY_MANAGER", this.getClass().getName() + ".onCreate");
        ActivityMgr.getInstance().addActivity(this);
    }


    //准备数据
    protected void prepareData() {
    }

    protected void initListener() {
    }

    protected void initDatas() {
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        LogUtil.d("ACTIVITY_MANAGER", this.getClass().getName() + ".onDestroy");
        ActivityMgr.getInstance().removeActivity(this);
        super.onDestroy();
    }

    /**
     * 显示加载对话框（调用此方法可以指定加载框样式）
     *
     * @param dialogStyle
     */
    public void showLoadingDialog(int dialogStyle) {
        if (ActivityMgr.activityIsFinish(this)) {
            return;
        }

        //        mCallLoadingCount++;//调用一次累计一次
        if (mLoadingDialog == null) {
            mLoadingDialog = DialogFactory.createLoadingDialog(this, dialogStyle);
        }
        if (mLoadingDialog != null) {
            mLoadingDialog.show();
        }
    }

    /**
     * 显示加载对话框（调用此方法将使用默认加载框样式）
     */
    public void showLoadingDialog() {
        showLoadingDialog(defaultLoadingDialogStyle());
    }

    public void dismissLoadingDialog() {
        if (ActivityMgr.activityIsFinish(this)) {
            return;
        }

        //        mCallLoadingCount--;//调用一次计减一次
        if (mCallLoadingCount == 0 && mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * Activity默认加载框样式
     *
     * @return
     */
    protected int defaultLoadingDialogStyle() {
        return LoadingDialog.LOADING_STYLE_DEFAULT;//默认样式
    }
}
