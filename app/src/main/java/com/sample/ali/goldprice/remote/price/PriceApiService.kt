package com.sample.ali.goldprice.remote.price

import com.sample.ali.goldprice.remote.price.model.PriceApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PriceApiService {

    @GET("Gold_Currency.php?")
    suspend fun getTimeAndPrices(@Query("key") apiKey: String) : Response<PriceApiModel>

}