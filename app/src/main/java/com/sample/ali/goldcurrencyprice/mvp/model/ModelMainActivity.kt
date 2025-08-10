package info.alihabibi.goldcurrencyprice.mvp.model

import android.content.ContentResolver
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import info.alihabibi.goldcurrencyprice.remote.ApiResultHandler
import info.alihabibi.goldcurrencyprice.remote.PriceApiRetrofitService
import info.alihabibi.goldcurrencyprice.remote.model.PriceApiModel
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.util.Calendar

private const val apiKey: String = "Freedh1PXvgCtNrcn374FDBUVintkvGa"

class ModelMainActivity(context: Context) {

    private val prefs = context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)

    private val connectivityManager by lazy {
        context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    }

    fun checkDeviceConnectivity(): Boolean {
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true && networkCapabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_VALIDATED
        )
    }

    fun isNetworkProvidingTimeZone(contentResolver: ContentResolver): Boolean {
        try {
            val time: Boolean =
                android.provider.Settings.Global.getInt(
                    contentResolver,
                    android.provider.Settings.Global.AUTO_TIME
                ) == 1
            val timeZone: Boolean =
                android.provider.Settings.Global.getInt(
                    contentResolver,
                    android.provider.Settings.Global.AUTO_TIME_ZONE
                ) == 1
            return time && timeZone
        } catch (e: Exception) {
            return false
        }
    }

    suspend fun getPrices(): ApiResultHandler<PriceApiModel> {
        return try {
            val apiService = PriceApiRetrofitService.priceApiService
            val response = apiService.getPrices(apiKey)
            if (response.isSuccessful && response.body() != null) {
                ApiResultHandler.OnSuccess(response.body()!!, response.code())
            } else {
                ApiResultHandler.OnFailure(
                    errorMessage = response.message(),
                    code = response.code(),
                    throwable = Exception(response.errorBody().toString())
                )
            }
        } catch (e: Exception) {
            ApiResultHandler.OnFailure(throwable = Exception("Failed To Call Api"))
        }
    }

    fun convertToPersianDate(): String {
        val persianDate = PersianDate(Calendar.getInstance().timeInMillis)
        return PersianDateFormat("l j F Y").format(persianDate)
    }

    fun checkForVpnService(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_VPN) ?: return false
    }

    val changePreferences: (Boolean) -> Unit = { isAccepted ->
        prefs.edit()
            .putBoolean("PREFERENCES", isAccepted)
            .apply()
    }

    val preferencesState: () -> Boolean = {
        prefs.getBoolean("PREFERENCES", false)
    }

    init {
        if (!prefs.contains("PREFERENCES"))
            prefs.edit()
                .putBoolean("PREFERENCES", false)
                .apply()
    }

}