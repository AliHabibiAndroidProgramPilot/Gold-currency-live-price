package info.alihabibi.goldcurrencyprice.mvp.presenter

import info.alihabibi.goldcurrencyprice.mvp.ext.CurrencyPriceFragmentContract

class PresenterCurrencyPriceFragment : CurrencyPriceFragmentContract.Presenter {

    private var view: CurrencyPriceFragmentContract.View? = null

    override fun attachView(view: CurrencyPriceFragmentContract.View) {
        this.view = view
    }

    override fun viewCaller() {
        view?.setupRecyclerView()
    }

    override fun detachView() {
        this.view = null
    }

}