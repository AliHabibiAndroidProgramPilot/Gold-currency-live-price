package com.sample.ali.goldprice.mvp.presenter

import android.content.Context
import com.sample.ali.goldprice.mvp.ext.GoldPriceFragmentContract
import com.sample.ali.goldprice.mvp.model.ModelGoldPriceFragment

class PresenterGoldPriceFragment(
    private val model: ModelGoldPriceFragment
) : GoldPriceFragmentContract.Presenter {

    private var view: GoldPriceFragmentContract.View? = null

    override fun attachView(view: GoldPriceFragmentContract.View) {
        this.view = view
    }

    override fun viewCaller(context: Context) {

    }

    override fun detachView() {
        this.view = null
    }

}