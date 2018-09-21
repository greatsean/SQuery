package com.sean.myquery.ui.fang;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.dtds.mobileplatform.ui.BaseActivity;
import com.dtds.mobileplatform.util.NumberUtil;
import com.dtds.mobileplatform.util.UiHelper;
import com.sean.myquery.R;
import com.sean.myquery.bean.QuLunhouBean;
import com.sean.myquery.bean.ShiLunhouBean;
import com.sean.myquery.http.BaseBeanFunc1;
import com.sean.myquery.http.RetrofitHelper;
import com.sean.myquery.http.ZhuJianJuApi;

import java.util.List;

import butterknife.BindView;
import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LunhouDetailActivity extends BaseActivity {

    @BindView(R.id.wv_detail)
    WebView mWvDetail;
    private ShiLunhouBean mShiLunhouBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunhou_detail);
        Intent intent = getIntent();
        mShiLunhouBean = (ShiLunhouBean) intent.getSerializableExtra("item");
        String waittype = mShiLunhouBean.getWAIT_TPYE();
        String lhmcId = mShiLunhouBean.getLHMC_ID();
        String url = "http://bzflh.szjs.gov.cn/TylhW/lhmcAction.do?method=queryDetailLhc&lhmcId=" + lhmcId + "&waittype=" + waittype;
        mWvDetail.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lunhou_detail_menu, menu);
        return true;
    }
    Dialog mDialog;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shi_lunhou:
                if (mShiLunhouBean.getSFZH().endsWith("*")) {
                    final EditText editText = (EditText) View.inflate(this, R.layout.view_dialog_sfzh, null);
                    new AlertDialog.Builder(this).setTitle("请输入身份证后4位").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mShiLunhouBean.setSFZH(mShiLunhouBean.getSFZH().substring(0, mShiLunhouBean.getSFZH().length() - 4) + editText.getText().toString());
//                            new QuDialogFragment(mShiLunhouBean).show(getSupportFragmentManager(), "detail");
                            loadQuData();
                        }
                    }).setNegativeButton("取消", null).setView(editText).show();
                } else {
                    mDialog.show();
//                    new QuDialogFragment(mShiLunhouBean).show(getSupportFragmentManager(), "detail");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


//    public static class QuDialogFragment extends DialogFragment {
//
//
//        @BindView(R.id.t2)
//        TextView mT2;
//        @BindView(R.id.t3)
//        TextView mT3;
//        @BindView(R.id.t4)
//        TextView mT4;
//        @BindView(R.id.t5)
//        TextView mT5;
//        @BindView(R.id.t6)
//        TextView mT6;
//        @BindView(R.id.t7)
//        TextView mT7;
//
//
//        Unbinder unbinder;
//        private ShiLunhouBean mShiLunhouBean;
//
//        public QuDialogFragment() {
//        }
//
//        public QuDialogFragment(ShiLunhouBean mShiLunhouBean) {
//            this.mShiLunhouBean = mShiLunhouBean;
//        }
//
//        @Nullable
//        @Override
//        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            View inflate = inflater.inflate(R.layout.fragment_qu_detail, null);
//            unbinder = ButterKnife.bind(this, inflate);
//
//            return inflate;
//        }
//
//
//        @Override
//        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//            super.onViewCreated(view, savedInstanceState);
//            loadQuData();
//        }
//    private void loadQuData() {
//        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
//        ZhuJianJuApi service = retrofit.create(ZhuJianJuApi.class);
////             showLoadingDialog();
//        //        pageNumber=1&pageSize=10&waitTpye=2&bahzh=BHR00072854&xingm=%25E6%259D%258E%25E6%25A0%25A1%25E8%25BE%2589&sfz=431023198904215859
//        String shoulbahzh = mShiLunhouBean.getSHOULHZH();
//        String xingm = mShiLunhouBean.getXINGM();
//        String idcard = mShiLunhouBean.getSFZH();
//        int i = NumberUtil.parseIntSafely(mShiLunhouBean.getWAIT_TPYE());
//        service.getQuLunhou(i, shoulbahzh, xingm, idcard)
//                .subscribeOn(Schedulers.io())
//                .map(new BaseBeanFunc1<List<QuLunhouBean>>())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<QuLunhouBean>>() {
//                    @Override
//                    public void onCompleted() {
//                        System.out.println("done");
////                             dismissLoadingDialog();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        UiHelper.toast(LunhouDetailActivity.this, "错误："+e.getMessage());
//                        System.out.println(e);
////                             dismissLoadingDialog();
//                    }
//
//                    @Override
//                    public void onNext(List<QuLunhouBean> quLunhouBeanList) {
//                        QuLunhouBean item = quLunhouBeanList.get(0);
//                        mT2.setText(item.getAREA_PAIX() + "");
//                        mT3.setText(item.getPAIX() + "");
//                        mT4.setText(item.getSHOULHZH());
//                        mT5.setText(item.getXINGM());
//                        mT6.setText(item.getPERSON_TYPE());
//                        mT7.setText(item.getSFZH());
//                    }
//                });
//    }
//
//        @Override
//        public void onDestroyView() {
//            super.onDestroyView();
//            unbinder.unbind();
//        }
//    }

    private void loadQuData() {

        showLoadingDialog();
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        ZhuJianJuApi service = retrofit.create(ZhuJianJuApi.class);
//             showLoadingDialog();
        //        pageNumber=1&pageSize=10&waitTpye=2&bahzh=BHR00072854&xingm=%25E6%259D%258E%25E6%25A0%25A1%25E8%25BE%2589&sfz=431023198904215859
        String shoulbahzh = mShiLunhouBean.getSHOULHZH();
        String xingm = mShiLunhouBean.getXINGM();
        String idcard = mShiLunhouBean.getSFZH();
        int i = NumberUtil.parseIntSafely(mShiLunhouBean.getWAIT_TPYE());
        service.getQuLunhou(i, shoulbahzh, xingm, idcard)
                .subscribeOn(Schedulers.io())
                .map(new BaseBeanFunc1<List<QuLunhouBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<QuLunhouBean>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("done");
//                             dismissLoadingDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissLoadingDialog();
                        UiHelper.toast(LunhouDetailActivity.this, "错误：" + e.getMessage());
                        System.out.println(e);
//                             dismissLoadingDialog();
                    }

                    @Override
                    public void onNext(List<QuLunhouBean> quLunhouBeanList) {
                        dismissLoadingDialog();
                        QuLunhouBean item = quLunhouBeanList.get(0);

                        final TextView tv=new TextView(LunhouDetailActivity.this);
                        tv.setPadding(50,50,0,0);
                        tv.setText("区级排名是：" + item.getAREA_PAIX() + "");
                        mDialog=new AlertDialog.Builder(LunhouDetailActivity.this).setPositiveButton("确定", null).setView(tv).show();

                    }
                });
    }


}
