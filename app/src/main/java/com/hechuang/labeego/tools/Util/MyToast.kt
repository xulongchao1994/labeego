package com.hechuang.labeego.tools.Util

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

/**
 */

class MyToast constructor(val mContext: Context) {
    private var currentToast: Toast? = null

    companion object {
        private var mInstance: MyToast? = null
        @JvmStatic
        fun init(context: Context) {
            mInstance = MyToast(context)
        }

        @JvmStatic
        @SuppressLint("ShowToast")
        fun showMsg(text: String) {
            if (mInstance!!.currentToast == null) {
                mInstance!!.currentToast = Toast.makeText(mInstance!!.mContext, text, Toast.LENGTH_SHORT)
            } else {
                mInstance!!.currentToast!!.setText(text)
                mInstance!!.currentToast!!.duration = Toast.LENGTH_SHORT
            }
            mInstance!!.currentToast!!.show()
        }

        fun showMsgOfLong(text: String) {
            if (mInstance!!.currentToast == null) {
                mInstance!!.currentToast = Toast.makeText(mInstance!!.mContext, text, Toast.LENGTH_LONG)
            } else {
                mInstance!!.currentToast!!.setText(text)
                mInstance!!.currentToast!!.duration = Toast.LENGTH_LONG
            }
            mInstance!!.currentToast!!.show()
        }

        /** 取消  */
        fun cancelToast() {
            if (mInstance!!.currentToast == null) {
            } else {
                mInstance!!.currentToast!!.cancel()
            }
        }
    }
}
