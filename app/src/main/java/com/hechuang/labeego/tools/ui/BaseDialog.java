package com.hechuang.labeego.tools.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

public class BaseDialog extends Dialog {
    private int res;
    private Window dialogWindow;
    private WindowManager.LayoutParams dialogLayoutParams;

    public BaseDialog(Context context, int theme, int res) {
        super(context, theme);
        setContentView(res);
        this.res = res;
        setCanceledOnTouchOutside(false);

    }

    /**
     * 设置布局的宽高
     *
     * @param height
     * @param width
     */
    public void setLayoutHeightWidth(int height, int width) {

        dialogLayoutParams.height = height;
        dialogLayoutParams.width = width;
        dialogWindow.setAttributes(dialogLayoutParams);
    }
}
