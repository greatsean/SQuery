package com.sean.myquery.file.model;

import android.support.annotation.NonNull;

import java.io.File;
import java.net.URI;

import lombok.Getter;
import lombok.Setter;

/*****************************************
 * @description:
 * @author:lixiaohui
 * @date: 2018/8/28
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class MyFile extends File {
    @Getter
    @Setter
    private boolean selected;

    public MyFile(@NonNull String pathname) {
        super(pathname);
    }

    public MyFile(String parent, @NonNull String child) {
        super(parent, child);
    }

    public MyFile(File parent, @NonNull String child) {
        super(parent, child);
    }

    public MyFile(@NonNull URI uri) {
        super(uri);
    }
}
