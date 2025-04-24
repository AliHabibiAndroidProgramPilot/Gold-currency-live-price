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
        setPersianDate()
        view.setMainTabLayout()
        connectivityManager()
        dateAndTimeHelperManager()
    }

    override fun presenterOnDestroy() {}

    private fun connectivityManager() {
        val connectivityState: Boolean = model.checkDeviceConnectivity(utils.getContext()!!)
        if (!connectivityState) {
            view.setInternetUnavailableFragment()
        }
    }

    private fun dateAndTimeHelperManager() {
        val contentResolver = utils.getActivityContentResolver()
        val timeAndTimeZoneProviderState = model.isNetworkProvidingTimeZone(contentResolver)
        if (!timeAndTimeZoneProviderState)
            view.visibleDateAndTimeHelperIcon()
    }

    private fun setPersianDate() {
        val dateText = model.convertToPersianDate()
        view.setDateText(dateText)
    }

}