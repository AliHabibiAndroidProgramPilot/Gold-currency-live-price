package com.sample.ali.goldprice.mvp.model

import com.sample.ali.goldprice.mvp.ext.GoldPriceFragmentContract
import com.sample.ali.goldprice.remote.PriceApiRetrofitService
import com.sample.ali.goldprice.remote.model.PriceApiModel

class ModelGoldPriceFragment : GoldPriceFragmentContract.Model {

    override suspend fun getGoldPrices(apiKey: String): Result<PriceApiModel> {
        return try {
            val apiService = PriceApiRetrofitService.priceApiService
            val response = apiService.getTimeAndPrices(apiKey)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Server error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}