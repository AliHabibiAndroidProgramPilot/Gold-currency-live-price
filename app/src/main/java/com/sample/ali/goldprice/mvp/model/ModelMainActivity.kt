package com.sample.ali.goldprice.mvp.model

import android.content.ContentResolver
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.util.Calendar

class ModelMainActivity {

    fun checkDeviceConnectivity(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true && networkCapabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_VALIDATED
        )
    }

    fun isNetworkProvidingTimeZone(contentResolver: ContentResolver): Boolean {
        try {
            val time: Boolean =
                android.provider.Settings.Global.getInt(
                    contentResolver,
                    android.provider.Settings.Global.AUTO_TIME
                ) == 1
            val timeZone: Boolean =
                android.provider.Settings.Global.getInt(
                    contentResolver,
                    android.provider.Settings.Global.AUTO_TIME_ZONE
                ) == 1
            return time && timeZone
        } catch (e: Exception) {
            return false
        }
    }

    fun convertToPersianDate(): String {
        val persianDate = PersianDate(Calendar.getInstance().timeInMillis)
        return PersianDateFormat("l j F Y").format(persianDate)
    }

}