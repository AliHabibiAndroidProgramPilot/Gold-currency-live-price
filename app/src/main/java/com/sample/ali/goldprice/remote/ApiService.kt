package com.sample.ali.goldprice.remote

import com.sample.ali.goldprice.remote.model.ApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("Gold_Currency.php?")
    suspend fun getTimeAndPrices(@Query("key") apiKey: String) : Response<ApiModel>

}