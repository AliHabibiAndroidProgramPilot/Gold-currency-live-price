package com.sample.ali.goldprice.remote

import android.util.Log
import com.sample.ali.goldprice.remote.priceapi.PriceApiRespond
import com.sample.ali.goldprice.remote.priceapi.PriceModel
import com.sample.ali.goldprice.remote.timeapi.TimeApiRespond
import com.sample.ali.goldprice.remote.timeapi.TimeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRepository private constructor() {

    companion object {
        private var apiRepository: ApiRepository? = null
        val instance: ApiRepository
            get() {
                if (apiRepository == null)
                    apiRepository = ApiRepository()
                return apiRepository!!
            }
    }

    fun getTime(apiRespond: TimeApiRespond) {
        ApiRetrofitService.apiService.getTime(true).enqueue(
            object : Callback<TimeModel> {
                override fun onResponse(call: Call<TimeModel>, response: Response<TimeModel>) {
                    if (response.isSuccessful)
                        apiRespond.onApiRespond(response.body() as TimeModel)
                    else {
                        apiRespond.onApiRespondFailure(response.code().toString())
                        Log.e("API", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<TimeModel>, t: Throwable) {
                    val toastMessage = "خطا در دریافت اطلاعات اتصال اینترنت خود را بررسی کنید"
                    apiRespond.onApiRespondFailure(toastMessage)
                    Log.e("API", "${t.cause} \n ${t.message}")
                }

            }
        )
    }

    fun getPrices(apiRespond: PriceApiRespond) {
        ApiRetrofitService.apiService.getPrices().enqueue(
            object : Callback<PriceModel> {
                override fun onResponse(call: Call<PriceModel>, response: Response<PriceModel>) {
                    if (response.isSuccessful) {
                        apiRespond.onApiRespond(response.body() as PriceModel)
                    } else {
                        apiRespond.onApiRespondFailure(response.code().toString())
                        Log.e("API", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<PriceModel>, t: Throwable) {
                    val toastMessage = "خطا در دریافت اطلاعات اتصال اینترنت خود را بررسی کنید"
                    apiRespond.onApiRespondFailure(toastMessage)
                    Log.e("API", "${t.cause} \n ${t.message}")
                }

            }
        )
    }

}