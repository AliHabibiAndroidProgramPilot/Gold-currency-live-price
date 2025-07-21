package com.sample.ali.goldprice.mvp.ext

import android.content.Context
import com.sample.ali.goldprice.remote.ApiResultHandler
import com.sample.ali.goldprice.remote.model.PriceApiModel

interface GoldPriceFragmentContract {

    interface View {
        fun setupRecyclerView(data: PriceApiModel) {}
        fun setupSwipeRefresh()
        fun showErrorFetchingGoldPriceMessage(errorCode: Int) {}
    }

    interface Presenter {
        fun attachView(view: View)
        fun viewCaller(context: Context) {}
        fun fetchGoldPrices() {}
        fun detachView()
    }

    interface Model {
        suspend fun getGoldPrices(): ApiResultHandler<PriceApiModel>
    }

}