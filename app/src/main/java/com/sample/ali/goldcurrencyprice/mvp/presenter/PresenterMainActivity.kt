package info.alihabibi.goldcurrencyprice.mvp.presenter

import android.util.Log
import info.alihabibi.goldcurrencyprice.mvp.ext.ActivityLifecycle
import info.alihabibi.goldcurrencyprice.mvp.ext.ActivityUtils
import info.alihabibi.goldcurrencyprice.mvp.model.ModelMainActivity
import info.alihabibi.goldcurrencyprice.mvp.view.ViewMainActivity
import info.alihabibi.goldcurrencyprice.remote.ApiResultHandler
import info.alihabibi.goldcurrencyprice.remote.model.PriceApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity,
    private val utils: ActivityUtils
) : ActivityLifecycle {

    override fun presenterOnCreate() {
        view.setInsets()
        view.setSystemBarsColor()
        if (!model.preferencesState())
            view.showPrivacyAlertDialog {
                model.changePreferences(true)
            }
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
        val connectivityState: Boolean = model.checkDeviceConnectivity()
        if (!connectivityState) {
            view.setInternetUnavailableFragment()
        }
    }

    private fun checkVpnState() {
        if (model.checkForVpnService())
            view.showVpnProblemMessage()
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
        view.manageLoadingAnimation(true)
        utils.getActivityLifecycleScope().launch {
            val result = withContext(Dispatchers.IO) {
                model.getPrices()
            }
            view.manageLoadingAnimation(false)
            when (result) {
                is ApiResultHandler.OnSuccess -> view.setMainTabLayout(result.body)

                is ApiResultHandler.OnFailure -> {
                    view.setMainTabLayout(PriceApiModel(arrayListOf(), arrayListOf()))
                    view.enableErrorBox(result.code?.toShort() ?: 400)
                    val errorMessageAndCode = "${result.code} \n ${result.errorMessage}"
                    Log.e("API", errorMessageAndCode)
                }
            }
        }
    }

    override fun presenterOnStart() {
        checkVpnState()
    }

}