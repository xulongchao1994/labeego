package com.hechuang.labeego.tools.Util

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * md5加密
 */

object MD5Builder {
    @JvmStatic
    fun getMD5Str(str: String): String {
        var messageDigest: MessageDigest? = null
        try {
            messageDigest = MessageDigest.getInstance("MD5")
            messageDigest!!.reset()
            messageDigest.update(str.toByteArray(charset("UTF-8")))
        } catch (e: NoSuchAlgorithmException) {
            //            System.out.println("NoSuchAlgorithmException caught!");
            //            System.exit(-1);
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        val byteArray = messageDigest!!.digest()
        val md5StrBuff = StringBuffer()
        for (i in byteArray.indices) {
            if (Integer.toHexString(0xFF and byteArray[i].toInt()).length == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF and byteArray[i].toInt()))
            else
                md5StrBuff.append(Integer.toHexString(0xFF and byteArray[i].toInt()))
        }
        return md5StrBuff.toString()
    }

}