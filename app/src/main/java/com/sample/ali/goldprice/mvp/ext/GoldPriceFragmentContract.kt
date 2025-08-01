package com.sample.ali.goldprice.mvp.ext

import android.content.Context

interface GoldPriceFragmentContract {

    interface View {
        fun setupRecyclerView() {}
    }

    interface Presenter {
        fun attachView(view: View)
        fun viewCaller(context: Context) {}
        fun detachView()
    }

}