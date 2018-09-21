package com.dtds.mobileplatform.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dtds.mobileplatform.R;
import com.dtds.mobileplatform.util.UiHelper;


/**
 * 头布局控件
 * Created by lixiaohui on 2017/4/28.
 */
public class HeaderView extends RelativeLayout {
    private TextView mTitleTv;//中间标题文本
    private ImageView mLeftBtn;//左边第一个按钮

    private View mRightBtn;//右边第一个按钮的根布局
    private TextView mRightBtnTv;//右边第一个按钮的文本
    private ImageView mRightBtnImv;//右边第一个按钮的图片

    private View mRight2Btn;//右边第二个按钮的根布局
    private TextView mRight2BtnTv;//右边第二个按钮的文本
    private ImageView mRight2BtnImv;//右边第二个按钮的图片


    public HeaderView(Context context) {
        super(context);
        initViewContent();
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewContent();
        readAttributeAndSet(attrs);
    }

    /**
     * 初始化子视图
     */
    private void initViewContent() {
        View viewContent = View.inflate(getContext(), R.layout.ulib_header_view, this);
        //中间标题文本
        mTitleTv = (TextView) viewContent.findViewById(R.id.tv_title);
        //左边第一个按钮
        mLeftBtn = (ImageView) viewContent.findViewById(R.id.imv_left);
        //右边第一个按钮
        mRightBtn = viewContent.findViewById(R.id.layout_right);
        mRightBtnTv = (TextView) viewContent.findViewById(R.id.tv_right);
        mRightBtnImv = (ImageView) viewContent.findViewById(R.id.imv_right);
        //右边第二个按钮
        mRight2Btn = viewContent.findViewById(R.id.layout_right2);
        mRight2BtnTv = (TextView) viewContent.findViewById(R.id.tv_right2);
        mRight2BtnImv = (ImageView) viewContent.findViewById(R.id.imv_right2);
        defaultSet();
    }

    /**
     * 本控件默认设置
     */
    private void defaultSet() {
        //设置左边退出按钮
        setLeftBtnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activityFromView = UiHelper.getActivityFromView(v);
                if (activityFromView != null) {
                    activityFromView.finish();
                }
            }
        });
        //设置标题超出内容后跑马灯效果
        UiHelper.setTextViewMarquee(mTitleTv);
        //设置头背景（黄色）
        setBackgroundColor(Color.parseColor("#27B5EE"));
        //设置默认布局参数
        int headerHeight = getContext().getResources().getDimensionPixelOffset(R.dimen.ulib_header_height);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, headerHeight));
    }

    /**
     * 读取属性并设置
     */
    private void readAttributeAndSet(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HeaderViewStyle);
        //设置文本
        CharSequence titleText = typedArray.getText(R.styleable.HeaderViewStyle_titleTxt);
        if (titleText != null) {
            setTitleTxt(titleText);
        }
        CharSequence rightText = typedArray.getText(R.styleable.HeaderViewStyle_rightTxt);
        if (rightText != null) {
            setRightBtnTxt(rightText);
        }
        CharSequence right2Text = typedArray.getText(R.styleable.HeaderViewStyle_right2Txt);
        if (right2Text != null) {
            setRight2BtnTxt(right2Text);
        }
        //设置图片
        int leftImgId = typedArray.getResourceId(R.styleable.HeaderViewStyle_leftImg, 0);
        if (leftImgId != 0) {//有配置图片资源才设置上去
            setLeftBtnImg(leftImgId);
        }
        int rightImgId = typedArray.getResourceId(R.styleable.HeaderViewStyle_rightImg, 0);
        setRightBtnImg(rightImgId);
        int right2ImgId = typedArray.getResourceId(R.styleable.HeaderViewStyle_right2Img, 0);
        setRight2BtnImg(right2ImgId);
        typedArray.recycle();
    }

    /**
     * 设置标题文本
     *
     * @param resTxtId
     */
    public void setTitleTxt(int resTxtId) {
        mTitleTv.setText(resTxtId);
    }

    /**
     * 设置标题文本
     *
     * @param txtStr
     */
    public void setTitleTxt(CharSequence txtStr) {
        mTitleTv.setText(txtStr);
    }

    /**
     * 设置左边第一个按钮的图片
     *
     * @param imgResId
     */
    public void setLeftBtnImg(int imgResId) {
        if (imgResId > 0) {//合法图片id才设置
            mLeftBtn.setImageResource(imgResId);
        } else {//不合法就移除老图片
            mLeftBtn.setImageDrawable(null);//the Drawable to set, or null to clear the content
        }
    }

    /**
     * 设置左边第一个按钮的点击事件
     *
     * @param l
     */
    public void setLeftBtnClickListener(OnClickListener l) {
        mLeftBtn.setOnClickListener(l);
    }

    /**
     * 隐藏左边第一个按钮
     */
    public void hiddenLeftBtn() {
        mLeftBtn.setVisibility(View.GONE);
    }

    //--------------------【右边第一个按钮】mRightBtn start--------------------

    /**
     * 右边第一个按钮的图片
     *
     * @param imgResId
     */
    public void setRightBtnImg(int imgResId) {
        if (imgResId > 0) {//合法图片id才设置
            mRightBtnImv.setImageResource(imgResId);
            showRightBtn();
        } else {//不合法就移除老图片
            mRightBtnImv.setImageDrawable(null);//the Drawable to set, or null to clear the content
        }
    }

    /**
     * 右边第一个按钮的文本
     *
     * @param stringId
     */
    public void setRightBtnTxt(int stringId) {
        mRightBtnTv.setText(stringId);
        showRightBtn();
    }

    /**
     * 右边第一个按钮的文本
     *
     * @param txtStr
     */
    public void setRightBtnTxt(CharSequence txtStr) {
        mRightBtnTv.setText(txtStr);
        showRightBtn();
    }

    /**
     * 显示右边第一个按钮
     */
    public void showRightBtn() {
        mRightBtn.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏右边第一个按钮
     */
    public void hiddenRightBtn() {
        mRightBtn.setVisibility(View.GONE);
    }

    /**
     * 设置右边第一个按钮的点击事件
     *
     * @param l
     */
    public void setRightBtnClickListener(OnClickListener l) {
        mRightBtn.setOnClickListener(l);
    }

    /**
     * 设置右边第一个按钮可用性
     *
     * @param enable
     */
    public void setRightBtnEnable(boolean enable) {
        mRightBtn.setEnabled(enable);
        mRightBtnTv.setEnabled(enable);
        mRightBtnImv.setEnabled(enable);
    }
    //--------------------【右边第一个按钮】mRightBtn end--------------------


    //--------------------【右边第二个按钮】mRight2Btn start--------------------

    /**
     * 右边第二个按钮的图片
     *
     * @param imgResId
     */
    public void setRight2BtnImg(int imgResId) {
        if (imgResId > 0) {//合法图片id才设置
            mRight2BtnImv.setImageResource(imgResId);
            showRight2Btn();
        } else {//不合法就移除老图片
            mRight2BtnImv.setImageDrawable(null);//the Drawable to set, or null to clear the content
        }
    }

    /**
     * 右边第二个按钮的文本
     *
     * @param stringId
     */
    public void setRight2BtnTxt(int stringId) {
        mRight2BtnTv.setText(stringId);
        showRight2Btn();
    }

    /**
     * 右边第一个按钮的文本
     *
     * @param txtStr
     */
    public void setRight2BtnTxt(CharSequence txtStr) {
        mRight2BtnTv.setText(txtStr);
        showRight2Btn();
    }

    /**
     * 显示右边第二个按钮
     */
    public void showRight2Btn() {
        mRight2Btn.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏右边第二个按钮
     */
    public void hiddenRight2Btn() {
        mRight2Btn.setVisibility(View.GONE);
    }

    /**
     * 设置右边第二个按钮的点击事件
     *
     * @param l
     */
    public void setRight2BtnClickListener(OnClickListener l) {
        mRight2Btn.setOnClickListener(l);
    }

    /**
     * 设置右边第二个按钮可用性
     *
     * @param enable
     */
    public void setRight2BtnEnable(boolean enable) {
        mRight2Btn.setEnabled(enable);
    }
    //--------------------【右边第二个按钮】mRight2Btn end--------------------

}
