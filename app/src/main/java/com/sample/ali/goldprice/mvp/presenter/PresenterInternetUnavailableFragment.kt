package com.sample.ali.goldprice.mvp.presenter

import android.content.Context
import com.sample.ali.goldprice.mvp.ext.InternetUnavailableFragmentContract
import com.sample.ali.goldprice.mvp.model.ModelInternetUnavailableFragment

class PresenterInternetUnavailableFragment(
    private val model: ModelInternetUnavailableFragment
) : InternetUnavailableFragmentContract.Presenter {

    private var view: InternetUnavailableFragmentContract.View? = null

    override fun attachView(view: InternetUnavailableFragmentContract.View) {
        this.view = view
    }

    override fun viewCaller(context: Context) {
        view?.btnContinueToAppClick(model.checkDeviceConnectivity(context))
    }

    override fun detachView() {
        this.view = null
    }

}