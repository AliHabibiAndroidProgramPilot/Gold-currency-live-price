package info.alihabibi.goldcurrencyprice.mvp.model

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import info.alihabibi.goldcurrencyprice.mvp.ext.InternetUnavailableFragmentContract

class ModelInternetUnavailableFragment : InternetUnavailableFragmentContract.Model {

    override fun checkDeviceConnectivity(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true && networkCapabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_VALIDATED
        )
    }

}