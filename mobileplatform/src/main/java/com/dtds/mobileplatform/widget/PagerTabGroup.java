//package com.dtds.mobileplatform.widget;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//
//import com.huawei.echannel.utils.R;
//
//import java.util.List;
//
///**
// * Created by lixiaohui on 2017/2/7.
// * <p>
// * 分组页签指示器容器布局
// */
//
//public class PagerTabGroup extends RadioGroup implements RadioGroup.OnCheckedChangeListener {
//
//    private static final int UI_STYLE_ORANGE = 0;//橙色tab样式
//    private static final int UI_STYLE_BLUE = 1;//蓝色的tab样式
//    private static final int UI_STYLE_WHITE = 2;//白色的tab样式
//    private OnTabChangeListener mOnTabChangeListener;//页签变化回调
//
//    private int textSpacing;//tab之间间距
//    private int mUiStyle = UI_STYLE_ORANGE;//显示UI风格,默认橙色风格
//    private CharSequence[] mTitleStrs;//tab上的文本
//    private boolean mIsTabWidthWrap;//是否让tab自适应大小
//
//    public PagerTabGroup(Context context) {
//        super(context);
//    }
//
//    public PagerTabGroup(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init(attrs);
//    }
//
//    private void init(AttributeSet attrs) {
//        //默认横向
//        setOrientation(HORIZONTAL);
//        setOnCheckedChangeListener(this);
//        CharSequence[] titleStrs = null;
//        if (attrs != null) {
//            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PagerTabGroupStyle);
//            titleStrs = typedArray.getTextArray(R.styleable.PagerTabGroupStyle_title_strs);
//            textSpacing = typedArray.getDimensionPixelOffset(R.styleable.PagerTabGroupStyle_text_spacing, 0);
//            mUiStyle = typedArray.getInt(R.styleable.PagerTabGroupStyle_ui_style, UI_STYLE_ORANGE);//默认橙色风格
//            mIsTabWidthWrap = typedArray.getBoolean(R.styleable.PagerTabGroupStyle_tab_width_wrap, false);//默认均分宽度
//            typedArray.recycle();
//        }
//
//        if (titleStrs != null) {
//            setTitleStrs(titleStrs);
//        }
//    }
//
//
//    /**
//     * 设置标题字符串数组
//     *
//     * @param titleStrs
//     */
//    public void setTitleStrs(CharSequence[] titleStrs) {
//        mTitleStrs = titleStrs;
//        makeTabs();
//    }
//
//    /**
//     * 设置标题字符串集合
//     *
//     * @param titleStrs
//     */
//    public void setTitleStrs(List<CharSequence> titleStrs) {
//        setTitleStrs(titleStrs.toArray(new CharSequence[titleStrs.size()]));
//    }
//
//    private void makeTabs() {
//        //移除之前的子view
//        removeAllViews();
//        int resLayoutId;
//        switch (mUiStyle) {
//            case UI_STYLE_WHITE:
//                resLayoutId = R.layout.ulib_pager_tab_item3;
//                break;
//            case UI_STYLE_BLUE:
//                resLayoutId = R.layout.ulib_pager_tab_item2;
//                break;
//            case UI_STYLE_ORANGE:
//            default:
//                resLayoutId = R.layout.ulib_pager_tab_item1;
//                break;
//        }
//        //添加子view
//        for (int i = 0; i < mTitleStrs.length; i++) {
//            RadioButton rbtn = (RadioButton) View.inflate(getContext(), resLayoutId, null);
//            rbtn.setText(mTitleStrs[i]);
//            rbtn.setId(i);//设置id与index保持一致
//            LayoutParams lp;
//            if (mIsTabWidthWrap) {
//                lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
//            } else {
//                lp = new LayoutParams(0, LayoutParams.MATCH_PARENT);//注意:此处必须要用RadioGroup.LayoutParams，如果用LinearLayout.LayoutParams在某些机型设置无效，详细参考protected boolean checkLayoutParams(ViewGroup.LayoutParams p)
//                lp.weight = 1;
//            }
//            lp.leftMargin = textSpacing;
//            lp.rightMargin = textSpacing;
//            addView(rbtn, lp);
//        }
//    }
//
//    /**
//     * 给每个tab设置tag
//     *
//     * @param tags
//     */
//    public void setTabTags(List<Object> tags) {
//        if (tags == null) {
//            return;
//        }
//        int tabSize = tags.size();
//        if (mTitleStrs == null || mTitleStrs.length != tabSize || tabSize != getChildCount()) {
//            throw new RuntimeException("The titles length must be equal tags length.");
//        }
//        //遍历给子view添加tag
//        for (int i = 0; i < tabSize; i++) {
//            View childAt = getChildAt(i);
//            childAt.setTag(tags.get(i));
//        }
//    }
//
//    @Override
//    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//        if (mOnTabChangeListener != null) {
//            mOnTabChangeListener.onTabChange(checkedId, radioGroup.getChildAt(checkedId));
//        }
//    }
//
//    /**
//     * 页签变化回调接口
//     */
//    public interface OnTabChangeListener {
//        void onTabChange(int selectedPos, View selectedView);
//    }
//
//    /**
//     * 设置tab变化监听
//     *
//     * @param onTabChangeListener
//     */
//    public void setOnTabChangeListener(OnTabChangeListener onTabChangeListener) {
//        mOnTabChangeListener = onTabChangeListener;
//    }
//
//    @Override
//    public void check(int id) {
////        super.check(id);//会执行两次onCheckedChanged的调用
////        ((RadioButton) navTab.getChildAt(tabPosition)).setChecked(true);
//        ((RadioButton) getChildAt(id)).setChecked(true);//id与position是重合的
//    }
//}
