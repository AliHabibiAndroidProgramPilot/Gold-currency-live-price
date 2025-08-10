package info.alihabibi.goldcurrencyprice.mvp.presenter

import android.content.Context
import info.alihabibi.goldcurrencyprice.mvp.ext.InternetUnavailableFragmentContract
import info.alihabibi.goldcurrencyprice.mvp.model.ModelInternetUnavailableFragment

class PresenterInternetUnavailableFragment(
    private val model: ModelInternetUnavailableFragment
) : InternetUnavailableFragmentContract.Presenter {

    private var view: InternetUnavailableFragmentContract.View? = null

    override fun attachView(view: InternetUnavailableFragmentContract.View) {
        this.view = view
    }

    override fun viewCaller(context: Context) {
        view?.btnContinueToAppClick()
        view?.txtInternetSettingClick()
    }

    override fun connectivityChecker(context: Context): Boolean {
        return model.checkDeviceConnectivity(context)
    }

    override fun detachView() {
        this.view = null
    }

}