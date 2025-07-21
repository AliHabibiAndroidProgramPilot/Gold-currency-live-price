package com.sample.ali.goldprice.mvp.ext

import android.content.Context
import com.sample.ali.goldprice.remote.model.PriceApiModel

interface GoldPriceFragmentContract {

    interface View {
        fun setupRecyclerView(data: PriceApiModel) {}
        fun setupSwipeRefresh()
        fun errorFetchingGoldPrice(errorMessage: String) {}
    }

    interface Presenter {
        fun attachView(view: View)
        fun viewCaller(context: Context) {}
        fun fetchGoldPrices(apiKey: String) {}
        fun detachView()
    }

    interface Model {
        suspend fun getGoldPrices(apiKey: String): Result<PriceApiModel>
    }

}