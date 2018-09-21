package com.sean.myquery.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dtds.mobileplatform.ui.BaseActivity;
import com.dtds.mobileplatform.util.UiHelper;
import com.sean.myquery.R;
import com.sean.myquery.file.FileListActivity;
import com.sean.myquery.ui.fang.LunhouSearchActivity;
import com.sean.myquery.ui.shebao.ShebaoLoginActivity;
import com.sean.myquery.ui.test.Test1Activity;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2;
    @BindView(R.id.btn_lunhou)
    Button mBtnLunhou;
    @BindView(R.id.btn_shebao)
    Button mBtnShebao;
    @BindView(R.id.btn_permisson)
    Button mBtnPermisson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Abc abc = new Abc();
        abc = new Abc.AbcBuilder().isShare(true).build();
        System.out.println(abc);
    }


    @OnClick({R.id.btn_lunhou, R.id.btn_shebao, R.id.btn_dialog, R.id.btn_permisson, R.id.btn_file_batch})
    public void onClick(View view) {
        switch (view.getId()) {
            default:
            case R.id.btn_lunhou:
                startActivity(new Intent(this, LunhouSearchActivity.class));
                break;
            case R.id.btn_shebao:
                startActivity(new Intent(this, ShebaoLoginActivity.class));
                break;
            case R.id.btn_file_batch:
                startActivity(new Intent(this, FileListActivity.class));
                break;
            case R.id.btn_dialog:
                new AlertDialog.Builder(this).setTitle("标题").setMessage("消息体")
                        .setView(R.layout.dialog_alert_1)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                break;
            case R.id.btn_permisson:
                startActivity(new Intent(this, Test1Activity.class));
//                new AlertDialog.Builder(this).setTitle("选择支付方式")
//                        .setSingleChoiceItems(new String[]{"微众支付", "通联支付"}, 0, null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        int checkedItemPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
//                        Log.i("lxh", "确定 checkedItemPosition:" + checkedItemPosition);
//                    }
//                }).show();

                // 申请运行时权限
                RxPermissions.getInstance(this)
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)//读取sd权限
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean granted) {
                                if (granted) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
                                    UiHelper.toast(MainActivity.this, "获取权限成功！");
                                } else {
                                    UiHelper.toast(MainActivity.this, "获取相册访问权限失败，无法选择图片");
                                }
                            }
                        });

//// Here, this is the current activity
//                if (ContextCompat.checkSelfPermission(this,
//                        Manifest.permission.READ_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//
//                    // Should we show an explanation?
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
//
//                        Log.d(TAG, "onClick() returned:explanation" +
//                                " "  );
//                        // Show an expanation to the user *asynchronously* -- don't block
//                        // this thread waiting for the user's response! After the user
//                        // sees the explanation, try again to request the permission.
//
//                    } else {
//
//                        // No explanation needed, we can request the permission.
//
//                        ActivityCompat.requestPermissions(this,
//                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//
//                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                        // app-defined int constant. The callback method gets the
//                        // result of the request.
//                    }
//                }
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult() called with: requestCode = [" + requestCode + "], permissions = [" + Arrays.toString(permissions) + "], grantResults = [" + Arrays.toString(grantResults) + "]");
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
            default:
                break;
        }
    }

}
