package com.dtds.tools.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/*****************************************
 * @description:微众游客支付
 * @author:lixiaohui
 * @date: 2017/12/1
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class WebankVisitorPayActivity extends Activity {

    //    @BindView(R.id.wv_pay_content)
    WebView mWvPayContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webank_visitor_pay);

        mWvPayContent = (WebView) findViewById(R.id.wv_pay_content);
        mWvPayContent.getSettings().setJavaScriptEnabled(true);
        mWvPayContent.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.91 Safari/537.36");
//        mWvPayContent.loadUrl("https://open.test.webank.com/s/web-wallet/#!/pay/pay/-1/ih506e4vj1f055fmif1eeyf06xt58lgd/6ce2813b331ec1ef33abebb3d3da592e45211964/SQ201712011634307870001/ZX201712011634307870002/W3944569");

    }
}
