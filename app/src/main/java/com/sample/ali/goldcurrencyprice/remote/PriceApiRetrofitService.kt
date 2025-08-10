package info.alihabibi.goldcurrencyprice.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PriceApiRetrofitService {

    private const val BASE_URL: String = "https://BrsApi.ir/Api/Market/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val priceApiService: PriceApiService = retrofit.create(PriceApiService::class.java)

}