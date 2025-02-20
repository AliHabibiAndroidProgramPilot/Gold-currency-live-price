package com.sample.ali.goldprice

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class InternetUnavailableFragment : Fragment(R.layout.fragment_internet_unavailable) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_try_again)
            .setOnClickListener {
                val networkCapabilities: Boolean? = checkConnectivityManager()
                if (networkCapabilities == true) {
                    if (isAdded) {
                        parentFragmentManager.beginTransaction()
                            .remove(this)
                            .commit()
                    }
                }
            }
    }

    private fun checkConnectivityManager(): Boolean? {
        val connectivityManager =
            context?.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}