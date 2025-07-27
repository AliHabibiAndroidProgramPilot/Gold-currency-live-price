package com.sample.ali.goldprice.mvp.ext

interface CurrencyPriceFragmentContract {

    interface View {
        fun setupRecyclerView() {}
        fun setupSwipeRefresh()
    }

    interface Presenter {
        fun attachView(view: View)
        fun viewCaller() {}
        fun detachView()
    }

}