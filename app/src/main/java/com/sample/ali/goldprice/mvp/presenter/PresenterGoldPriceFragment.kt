package com.sample.ali.goldprice.mvp.presenter

import android.content.Context
import com.sample.ali.goldprice.mvp.ext.GoldPriceFragmentContract
import com.sample.ali.goldprice.mvp.model.ModelGoldPriceFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PresenterGoldPriceFragment(
    private val model: ModelGoldPriceFragment
) : GoldPriceFragmentContract.Presenter {

    private var view: GoldPriceFragmentContract.View? = null

    override fun attachView(view: GoldPriceFragmentContract.View) {
        this.view = view
    }

    override fun fetchGoldPrices(apiKey: String) {
        CoroutineScope(Dispatchers.Main).launch {
            //TODO(View show Loading should be here)
            val result = model.getGoldPrices(apiKey)
            //TODO(View hide Loading should be here)
            result
                .onSuccess { data ->
                    view?.setupRecyclerView(data)
                }
                .onFailure { errorMessage ->
                    view?.errorFetchingGoldPrice(errorMessage.message ?: "Unknown error")
                }
        }
    }

    override fun detachView() {
        this.view = null
    }

}