package com.sample.ali.goldprice.mvp.presenter

import android.content.Context
import android.util.Log
import com.sample.ali.goldprice.mvp.ext.GoldPriceFragmentContract
import com.sample.ali.goldprice.mvp.model.ModelGoldPriceFragment
import com.sample.ali.goldprice.remote.ApiResultHandler
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

    override fun viewCaller(context: Context) {
        view?.setupSwipeRefresh()
        view?.fetchPricesOnFragmentRemoval()
    }

    override fun fetchGoldPrices() {
        view?.manageLoadingAnimation(true)
        CoroutineScope(Dispatchers.Main).launch {
            when (val result = model.getGoldPrices()) {
                is ApiResultHandler.OnSuccess -> {
                    view?.manageLoadingAnimation(false)
                    view?.setupRecyclerView(result.body)
                }

                is ApiResultHandler.OnFailure -> {
                    view?.manageLoadingAnimation(false)
                    view?.showErrorFetchingGoldPriceMessage(result.code ?: 0)
                    val errorMessageAndCode = "${result.code} \n ${result.errorMessage}"
                    Log.e("API", errorMessageAndCode)
                }
            }
        }
    }

    override fun detachView() {
        this.view = null
    }

}