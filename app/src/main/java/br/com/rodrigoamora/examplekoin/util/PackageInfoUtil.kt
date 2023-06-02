package br.com.rodrigoamora.examplekoin.util

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager


class PackageInfoUtil {

    companion object {
        fun getVersionName(context: Context): String? {
            return try {
                val packageManager: PackageManager = context.packageManager
                val pInfo: PackageInfo = packageManager.getPackageInfo(context.packageName, 0)
                pInfo.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                ""
            }
        }

        fun getVersionCode(context: Context): Int? {
            return try {
                val packageManager: PackageManager = context.packageManager
                val pInfo: PackageInfo = packageManager.getPackageInfo(context.packageName, 0)
                pInfo.versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                0
            }
        }
    }

}