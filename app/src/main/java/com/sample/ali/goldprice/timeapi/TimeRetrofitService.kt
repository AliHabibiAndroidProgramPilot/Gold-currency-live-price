package com.sample.ali.goldprice.timeapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TimeRetrofitService {

    private const val BASE_URL = "https://tools.daneshjooyar.com/api/v1/"
    private val converterFactory = GsonConverterFactory.create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converterFactory)
        .build()
    val apiService: TimeApiService = retrofit.create(TimeApiService::class.java)

}