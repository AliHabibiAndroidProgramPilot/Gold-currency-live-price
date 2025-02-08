package com.sample.ali.goldprice.timeapi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimeApiRepository {

    companion object {
        private var timeApiRepository: TimeApiRepository? = null
        val instance: TimeApiRepository
            get() {
                if (timeApiRepository == null)
                    timeApiRepository = TimeApiRepository()
                return timeApiRepository!!
            }
    }

    fun getTime() {
        TimeRetrofitService.apiService.getTime(true).enqueue(
            object : Callback<TimeModel> {
                override fun onResponse(call: Call<TimeModel>, response: Response<TimeModel>) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<TimeModel>, t: Throwable) {
                    val toastMessage = "اتصال اینترنت خود را بررسی کنید"
                }

            }
        )
    }

}