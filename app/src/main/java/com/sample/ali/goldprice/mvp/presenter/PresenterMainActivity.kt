package com.sample.ali.goldprice.mvp.presenter

import android.util.Log
import com.sample.ali.goldprice.mvp.ext.ActivityLifecycle
import com.sample.ali.goldprice.mvp.ext.ActivityUtils
import com.sample.ali.goldprice.mvp.model.ModelMainActivity
import com.sample.ali.goldprice.mvp.view.ViewMainActivity
import com.sample.ali.goldprice.remote.ApiResultHandler
import com.sample.ali.goldprice.remote.model.PriceApiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity,
    private val utils: ActivityUtils
) : ActivityLifecycle {

    override fun presenterOnCreate() {
        view.setInsets()
        view.setSystemBarsColor()
        setPersianDate()
        connectivityManager()
        view.registerFragmentCallback()
        dateAndTimeHelperManager()
        view.wrongDateAndTimeBottomSheet(utils.getContext()!!)
        fetchPrices()
    }

    override fun presenterOnDestroy() {
        view.unregisterFragmentCallback()
    }

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

    fun fetchPrices() {
        CoroutineScope(Dispatchers.Main).launch {
            when (val result = model.getPrices()) {
                is ApiResultHandler.OnSuccess -> {
                    view.setMainTabLayout(result.body)
                    view.manageLoadingAnimation(false)
                }

                is ApiResultHandler.OnFailure -> {
//                    view?.showErrorFetchingGoldPriceMessage(result.code ?: 0)
                    view.setMainTabLayout(PriceApiModel(arrayListOf(), arrayListOf()))
                    view.manageLoadingAnimation(false)
                    val errorMessageAndCode = "${result.code} \n ${result.errorMessage}"
                    Log.e("API", errorMessageAndCode)
                }
            }
        }
    }

}