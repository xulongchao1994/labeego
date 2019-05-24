package com.hechuang.labeego.tools.Util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 */
object KeyBoardUtils {
    /**
     * 关闭软键盘
     *
     * @param view 焦点view
     */
    fun closeKeyBoard(view: View) {
        val imm = view
                .context.getSystemService(
                Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive) {
            imm.hideSoftInputFromWindow(
                    view.applicationWindowToken, 0)
        }
    }

    /**
     * 打开软键盘
     *
     * @param view 焦点view
     */
    fun openKeyBoard(view: View) {
        val imm = view
                .context.getSystemService(
                Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}
