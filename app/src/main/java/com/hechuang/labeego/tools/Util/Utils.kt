package com.hechuang.labeego.tools.Util

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AlertDialog
import android.util.DisplayMetrics
import android.util.Log
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.ui.activity.LabeegoActivity
import com.hechuang.labeego.ui.activity.LoginActivity
import java.util.regex.Pattern


object Utils {

    /**
     * 获取版本号
     *
     * @param context
     * @return 版本号
     */
    @JvmStatic
    fun getVersionCode(context: Context): Int {
        try {
            return context.packageManager.getPackageInfo(context.packageName, 0).versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            return 0
        }

    }


    /**
     * dip与px之间转换
     *
     * @param dipValue
     * @return
     */

    fun dip2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 获取屏幕宽度
     */
    fun getwindow_w(activity: Activity): Int {
        val manager = activity.windowManager
        val metrics = DisplayMetrics()
        manager.defaultDisplay.getMetrics(metrics)
        return metrics.widthPixels
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */
    fun isOPen(context: Context): Boolean {
        if (Build.VERSION.SDK_INT > 23) {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
            val gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
            val network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (gps || network) {
                return true
            }
        }
        return false
    }


    /**
     * 判断字符串是否含有大写字母，小写字母，数字
     *
     * @param pwd
     * @return
     */
    fun Pwdisok(pwd: String): Boolean {
        //        boolean isok = false;
        val upperstr = StringBuffer()
        val lowerstr = StringBuffer()
        if (pwd.length >= 8) {
            if (pwd.length <= 30) {
                for (i in 0 until pwd.length) {
                    val c = pwd[i]
                    if (Character.isUpperCase(c)) {
                        upperstr.append(c)
                    } else if (Character.isLowerCase(c)) {
                        lowerstr.append(c)
                    }
                }
                if (upperstr.length > 0 && lowerstr.length > 0) {
                    val pattern = Pattern.compile(".*\\d+.*")
                    val isNum = pattern.matcher(pwd)
                    if (isNum.matches()) {
                        return true
                    }
                }
            }
        }
        return false
    }

    //判断某一个类是否存在任务栈里面
    @JvmStatic
    fun isExistMainActivity(mContext: Context, activity: Class<*>): Boolean {
        val intent = Intent(mContext, activity)
        val cmpName = intent.resolveActivity(mContext.packageManager)
        var flag = false
        if (cmpName != null) { // 说明系统中存在这个activity
            val am = mContext.getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val taskInfoList = am.getRunningTasks(10)  //获取从栈顶开始往下查找的10个activity
            for (taskInfo in taskInfoList) {
                if (taskInfo.baseActivity == cmpName) { // 说明它已经启动了
                    flag = true
                    break  //跳出循环，优化效率
                }
            }
        }
        return flag
    }

    @JvmStatic
    fun finahactivity(context: Context) {
        if (Utils.isExistMainActivity(context, LabeegoActivity::class.java)) {
            Log.d("util", "labeegoactivity")
            LabeegoActivity().finish()
        }

        if (Utils.isExistMainActivity(context, LoginActivity::class.java)) {
            Log.d("util", "Loginactivity")
            LoginActivity().finish()
        }
    }

    @JvmStatic
    fun setdialotwidth(context: Activity, dialog: AlertDialog) {
        val windowManager = context.windowManager
        val display = windowManager.defaultDisplay
        val lp = dialog!!.getWindow()!!.attributes
        lp.width = (display.width * 0.85).toInt() //设置宽度
        dialog!!.getWindow()!!.attributes = lp
    }

    @JvmStatic
    fun ishasurl(host: String): Boolean? {
        var istrue = false
        for (i in 0 until UserData.bai_url.size) {
            if (host.contains(UserData.bai_url[i])) {
                return true
            } else {
                istrue = false
            }
        }
        return istrue
    }

}