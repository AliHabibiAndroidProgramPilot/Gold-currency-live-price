package com.sample.ali.goldprice.mvp.presenter

import android.content.Context
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sample.ali.goldprice.InternetUnavailableFragment
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
        CoroutineScope(Dispatchers.Main).launch {
            //TODO(View show Loading should be here)
            when (val result = model.getGoldPrices()) {
                is ApiResultHandler.OnSuccess -> {
                    view?.setupRecyclerView(result.body)
                }

                is ApiResultHandler.OnFailure -> {
                    view?.showErrorFetchingGoldPriceMessage(result.code ?: 0)
                    val errorMessageAndCode = "${result.code} \n ${result.errorMessage}"
                    Log.e("API", errorMessageAndCode)
                }
            }
            //TODO(View hide Loading should be here)
        }
    }

    override fun detachView() {
        this.view = null
    }

}