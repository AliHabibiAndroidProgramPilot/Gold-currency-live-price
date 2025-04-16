package com.sample.ali.goldprice.mvp.ext

import android.content.Context

interface InternetUnavailableFragmentContract {

    interface View {
        fun btnContinueToAppClick(isConnectivityAvailable: Boolean) {}
    }

    interface Presenter {
        fun attachView(view: View)
        fun viewCaller(context: Context) {}
        fun detachView()
    }

    interface Model {
        fun checkDeviceConnectivity(context: Context): Boolean
    }

}