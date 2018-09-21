package com.sean.myquery.file;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dtds.mobileplatform.ui.BaseActivity;
import com.sean.myquery.R;
import com.sean.myquery.file.model.MyFile;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class FileListActivity extends BaseActivity {

    private static final String TAG = "FileListActivity";
    @BindView(R.id.search_btn)
    Button mSearchBtn;
    @BindView(R.id.file_dir_et)
    EditText mFileDirEt;
    @BindView(R.id.files_rv)
    RecyclerView mFilesRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
        mSearchBtn.performClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.file_list_menu, menu);
        return true;
    }

    boolean isSelectAll = true;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.del:
                BaseQuickAdapter adapter = (BaseQuickAdapter) mFilesRv.getAdapter();
                int itemCount = adapter.getItemCount();
                for (int i = 0; i < itemCount; i++) {
                    MyFile myFile = (MyFile) adapter.getItem(i);

                    if (myFile.isSelected()) {
                        myFile.delete();
                    }
                }
                adapter.notifyDataSetChanged();

                mSearchBtn.performClick();//刷新
                break;
            case R.id.select_all:
                if (isSelectAll) {
                    adapter = (BaseQuickAdapter) mFilesRv.getAdapter();
                    itemCount = adapter.getItemCount();
                    for (int i = 0; i < itemCount; i++) {
                        MyFile myFile = (MyFile) adapter.getItem(i);
                        myFile.setSelected(true);
                    }
                    adapter.notifyDataSetChanged();
                    isSelectAll = false;
                } else {
                    adapter = (BaseQuickAdapter) mFilesRv.getAdapter();
                    itemCount = adapter.getItemCount();
                    for (int i = 0; i < itemCount; i++) {
                        MyFile myFile = (MyFile) adapter.getItem(i);
                        myFile.setSelected(false);
                    }
                    adapter.notifyDataSetChanged();
                    isSelectAll = true;
                }


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.search_btn)
    public void onViewClicked() {
        Subscription subscription = RxPermissions.getInstance(this).request("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").flatMap(new Func1<Boolean, Observable<List<MyFile>>>() {
            @Override
            public Observable<List<MyFile>> call(Boolean aBoolean) {
                if (aBoolean) {
                    return Observable.create(new Observable.OnSubscribe<List<MyFile>>() {
                        @Override
                        public void call(Subscriber<? super List<MyFile>> subscriber) {
                            List<MyFile> myFiles = new ArrayList<MyFile>();
                            List<File> files = Arrays.asList(new File(mFileDirEt.getText().toString()).listFiles(
                                    new FileFilter() {
                                        @Override
                                        public boolean accept(File pathname) {
                                            String name = pathname.getName();
                                            String[] split = name.split("_");
                                            String endStt = split[split.length - 1];
                                            Log.i(TAG, endStt + "accept: " + pathname);
                                            return split.length>3&&(endStt.startsWith("14716") || endStt.startsWith("1503"));
                                        }
                                    })
                            );
                            for (File file : files) {
                                MyFile myFile = new MyFile(file.getAbsolutePath());
                                myFiles.add(myFile);
                            }
                            Collections.sort(myFiles);
                            Collections.reverse(myFiles);
                            subscriber.onNext(myFiles);
                            subscriber.onCompleted();
                        }
                    });
                } else {
                    return Observable.error(new RuntimeException("没有权限"));
                }

            }
        })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<MyFile>>() {
                    @Override
                    public void call(List<MyFile> files) {
                        Toast.makeText(FileListActivity.this, "总文件数量" + files.size(), Toast.LENGTH_SHORT).show();
                        BaseQuickAdapter<MyFile, BaseViewHolder> adapter = new BaseQuickAdapter<MyFile, BaseViewHolder>(R.layout.listitem_file) {
                            @Override
                            protected void convert(BaseViewHolder helper, MyFile item) {
                                helper.setText(R.id.name_tv, item.getName());
                                helper.setText(R.id.path_tv, item.getPath());
                                ImageView imv = helper.getView(R.id.photo_imv);
                                Glide.with(FileListActivity.this).load(item.getAbsolutePath()).into(imv);
//                                Glide.with(FileListActivity.this).load( "file://" +item.getAbsolutePath()).into(imv);
                                helper.setChecked(R.id.select_cb, item.isSelected());
                            }
                        };
                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                MyFile item = (MyFile) adapter.getItem(position);
                                item.setSelected(!item.isSelected());
                                adapter.notifyDataSetChanged();
                            }
                        });
                        adapter.setNewData(files);
                        mFilesRv.setLayoutManager(new LinearLayoutManager(FileListActivity.this, LinearLayoutManager.VERTICAL, false));
                        mFilesRv.setAdapter(adapter);

                    }
                });


    }
}
