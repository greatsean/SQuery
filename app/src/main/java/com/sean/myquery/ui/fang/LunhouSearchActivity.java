package com.sean.myquery.ui.fang;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.dtds.mobileplatform.ui.BaseActivity;
import com.dtds.mobileplatform.util.NumberUtil;
import com.dtds.mobileplatform.widget.pulltorefresh.PullToRefreshBase;
import com.dtds.mobileplatform.widget.pulltorefresh.PullToRefreshListView;
import com.sean.myquery.R;
import com.sean.myquery.adapter.ShiLunhouAdapter;
import com.sean.myquery.bean.ShiLunhouBean;
import com.sean.myquery.http.BaseBeanFunc1;
import com.sean.myquery.http.RetrofitHelper;
import com.sean.myquery.http.ZhuJianJuApi;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*****************************************
 * @description:轮候搜索
 * @author:lixiaohui
 * @date: 2017/10/18
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class LunhouSearchActivity extends BaseActivity {
    private static final String TAG = "LunhouSearchActivity";

    @BindView(R.id.sp_query_type)
    Spinner mSpQueryType;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_id)
    EditText mEtId;
    @BindView(R.id.et_return_receipt_no)
    EditText mEtReturnReceiptNo;
    @BindView(R.id.et_goto_page)
    EditText mEtGotoPage;
    @BindView(R.id.lv_data)
    PullToRefreshListView mLvData;

    private int mCurrentPage = 1;
    private static final int PAGE_SIZE = 10;
    private ShiLunhouAdapter mShiLunhouAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunhou_search);
        mSpQueryType.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"安居房", "公租房"}));
        mSpQueryType.setSelection(1);

        mLvData.setMode(PullToRefreshBase.Mode.BOTH);
        mLvData.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新
                resetLoad();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //加载更多
                loadMore();
            }

        });

        mLvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShiLunhouBean item = (ShiLunhouBean) parent.getAdapter().getItem(position);
                Intent intent = new Intent(LunhouSearchActivity.this, LunhouDetailActivity.class);
                intent.putExtra("item", item);
                startActivity(intent);
            }
        });
        mShiLunhouAdapter = new ShiLunhouAdapter(this);
        mLvData.setAdapter(mShiLunhouAdapter);
        loadData();
    }

    private void resetLoad() {
        mShiLunhouAdapter.clear();
        loadData();
    }

    private void loadMore() {
        mCurrentPage++;
        loadData();
    }

    @OnTextChanged(R.id.et_goto_page)
    public void onPageNumChange() {
        String pageStr = mEtGotoPage.getText().toString();
        int page = TextUtils.isEmpty(pageStr) ? 1 : NumberUtil.parseIntSafely(pageStr);
        mCurrentPage = page;
    }

    private void loadData() {
        showLoadingDialog();

        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        ZhuJianJuApi service = retrofit.create(ZhuJianJuApi.class);

        String shoulbahzh = mEtReturnReceiptNo.getText().toString();
        String xingm = mEtName.getText().toString();
        String idcard = mEtId.getText().toString();

        service.getShiLunhou(mCurrentPage, PAGE_SIZE, mSpQueryType.getSelectedItemPosition() + 1, 0, shoulbahzh, xingm, idcard)
                .subscribeOn(Schedulers.io())
                .map(new BaseBeanFunc1<List<ShiLunhouBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ShiLunhouBean>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("done");
                        mLvData.onRefreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissLoadingDialog();
                        System.out.println(e);
                        mLvData.onRefreshComplete();
                    }

                    @Override
                    public void onNext(List<ShiLunhouBean> fangBean) {
                        dismissLoadingDialog();
                        mShiLunhouAdapter.addAll(fangBean);
                    }
                });

    }

    @OnClick({R.id.btn_query, R.id.btn_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_query:
                resetLoad();
                break;
            case R.id.btn_clear:
                mShiLunhouAdapter.clear();
                mCurrentPage = 1;
                mEtId.setText(null);
                mEtName.setText(null);
                mEtGotoPage.setText(null);
                mEtReturnReceiptNo.setText(null);
                break;
        }
    }
}