package com.sample.ali.goldprice.timeapi

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimeApiRepository private constructor() {

    companion object {
        private var timeApiRepository: TimeApiRepository? = null
        val instance: TimeApiRepository
            get() {
                if (timeApiRepository == null)
                    timeApiRepository = TimeApiRepository()
                return timeApiRepository!!
            }
    }

    fun getTime(apiRespond: TimeApiRespond) {
        TimeRetrofitService.apiService.getTime(true).enqueue(
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
                    val toastMessage = "اتصال اینترنت خود را بررسی کنید"
                    apiRespond.onApiRespondFailure(toastMessage)
                    Log.e("API", "${t.cause} \n ${t.message}")
                }

            }
        )
    }

}