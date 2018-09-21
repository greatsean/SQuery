package com.sean.myquery.ui.shebao;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.dtds.mobileplatform.ui.BaseActivity;
import com.sean.myquery.R;
import com.xiaoleilu.hutool.lang.Base64;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*****************************************
 * @description:社保登录界面
 * @author:lixiaohui
 * @date: 2017/10/18
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class ShebaoLoginActivity extends BaseActivity {

    ShebaoApi shebaoApi;
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shebao_login);

        //日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .baseUrl(ShebaoApi.ROOT_PATH)
                .client(client)
                .build();
        shebaoApi = retrofit.create(ShebaoApi.class);

    }

    @OnClick(R.id.btn_shebao_login)
    public void onClick() {

        String pwd = mEtPwd.getText().toString();
        String name = mEtUsername.getText().toString();
//        byte[] encode = Base64.encode(pwd.getBytes(), Base64.DEFAULT);
        String encodePwd = Base64.encode(pwd);
        Observable<String> observable = shebaoApi.login(name, encodePwd, "P", 1508148058531L, "", 5789);
        Subscription subscribe = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
//                        LogUtil.i("lxh",s);
                        Log.i("lxh", s);
                    }
                });

    }

    //    Method=P&pid=1508148058531&type=&AAC002=lixiaohui_life&CAC222=bHhoNjU0MzIxR08%3D&PSINPUT=5789
//    Method=P&pid=1508148058531&type=&AAC002=lixiaohui_life&CAC222=bHhoNjU0MzIxR08=&PSINPUT=5789
    public interface ShebaoApi {

        String ROOT_PATH = "https://e.szsi.gov.cn";

        @POST("/siservice/LoginAction.do")
        Observable<String> login(@Query("AAC002") String acc, @Query("CAC222") String pwd, @Query("Method") String Method, @Query("pid") long pid, @Query("type") String type, @Query("PSINPUT") int PSINPUT);

    }
}
