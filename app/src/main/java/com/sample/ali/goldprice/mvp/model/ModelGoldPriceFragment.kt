package com.sample.ali.goldprice.mvp.model

import com.sample.ali.goldprice.mvp.ext.GoldPriceFragmentContract
import com.sample.ali.goldprice.remote.ApiRetrofitService
import com.sample.ali.goldprice.remote.model.ApiModel

class ModelGoldPriceFragment : GoldPriceFragmentContract.Model {

    override suspend fun getGoldPrices(apiKey: String): Result<ApiModel> {
        return try {
            val apiService = ApiRetrofitService.apiService
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