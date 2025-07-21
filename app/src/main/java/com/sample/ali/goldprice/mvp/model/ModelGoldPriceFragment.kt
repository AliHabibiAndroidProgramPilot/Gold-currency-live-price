package com.sample.ali.goldprice.mvp.model

import com.sample.ali.goldprice.mvp.ext.GoldPriceFragmentContract
import com.sample.ali.goldprice.remote.ApiResultHandler
import com.sample.ali.goldprice.remote.PriceApiRetrofitService
import com.sample.ali.goldprice.remote.model.PriceApiModel

class ModelGoldPriceFragment : GoldPriceFragmentContract.Model {

    override suspend fun getGoldPrices(): ApiResultHandler<PriceApiModel> {
        return try {
            val apiService = PriceApiRetrofitService.priceApiService
            val response = apiService.getPrices("Freedh1PXvgCtNrcn374FDBUVintkvGa")
            if (response.isSuccessful && response.body() != null) {
                ApiResultHandler.OnSuccess(response.body()!!, response.code())
            } else {
                ApiResultHandler.OnFailure(
                    errorMessage = response.message(),
                    code = response.code(),
                    throwable = Exception(response.errorBody().toString())
                )
            }
        } catch (e: Exception) {
            ApiResultHandler.OnFailure(throwable = Exception("Failed To Call Api"))
        }
    }

}