package info.alihabibi.goldcurrencyprice.mvp.ext

import android.content.Context

interface InternetUnavailableFragmentContract {

    interface View {
        fun btnContinueToAppClick() {}
        fun txtInternetSettingClick() {}
    }

    interface Presenter {
        fun attachView(view: View)
        fun viewCaller(context: Context) {}
        fun connectivityChecker(context: Context): Boolean { return false }
        fun detachView()
    }

    interface Model {
        fun checkDeviceConnectivity(context: Context): Boolean
    }

}