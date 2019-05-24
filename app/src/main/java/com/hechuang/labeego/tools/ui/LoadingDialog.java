package com.hechuang.labeego.tools.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.hechuang.labeego.R;


/**
 * 等待框
 * Created by dyxdw on 2016/5/19.
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.loadingDialogStyle);
        setContentView(R.layout.loading);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setDimAmount(0f);
    }

}
