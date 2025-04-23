package com.sample.ali.goldprice.mvp.presenter

import com.sample.ali.goldprice.mvp.ext.ActivityLifecycle
import com.sample.ali.goldprice.mvp.ext.ActivityUtils
import com.sample.ali.goldprice.mvp.model.ModelMainActivity
import com.sample.ali.goldprice.mvp.view.ViewMainActivity

class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity,
    private val utils: ActivityUtils
) : ActivityLifecycle {

    override fun presenterOnCreate() {
        view.setInsets()
        view.setSystemBarsColor()
        view.setMainTabLayout()
        connectivityManager()
    }

    override fun presenterOnDestroy() {}

    private fun connectivityManager() {
        val connectivityState: Boolean = model.checkDeviceConnectivity(utils.getContext()!!)
        if (!connectivityState) {
            view.setInternetUnavailableFragment()
        }
    }

}