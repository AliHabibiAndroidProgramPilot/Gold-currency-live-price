package com.sample.ali.goldprice.mvp.ext

import android.content.Context

interface InternetUnavailableFragmentContract {

    interface View {

    }

    interface Presenter {
        fun attachView(view: View)
        fun viewCaller() {}
        fun detachView()
    }

    interface Model {
        fun checkDeviceConnectivity(context: Context): Boolean
    }

}