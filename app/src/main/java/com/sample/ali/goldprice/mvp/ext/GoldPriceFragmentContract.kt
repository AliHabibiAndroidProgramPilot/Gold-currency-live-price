package com.sample.ali.goldprice.mvp.ext

import android.content.Context
import com.sample.ali.goldprice.remote.model.ApiModel

interface GoldPriceFragmentContract {

    interface View {
        fun setupRecyclerView(data: ApiModel) {}
        fun errorFetchingGoldPrice(errorMessage: String) {}
    }

    interface Presenter {
        fun attachView(view: View)
        fun viewCaller(context: Context) {}
        fun fetchGoldPrices(apiKey: String) {}
        fun detachView()
    }

    interface Model {
        suspend fun getGoldPrices(apiKey: String): Result<ApiModel>
    }

}