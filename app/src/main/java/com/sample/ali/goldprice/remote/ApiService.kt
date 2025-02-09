package com.sample.ali.goldprice.remote

import com.sample.ali.goldprice.remote.timeapi.TimeModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("date/now")
    fun getTime(@Query("short") short: Boolean): Call<TimeModel>

}