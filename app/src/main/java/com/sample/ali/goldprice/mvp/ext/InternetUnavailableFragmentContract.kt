package com.sample.ali.goldprice.mvp.ext

interface InternetUnavailableFragmentContract {

    interface View {

    }

    interface Presenter {
        fun attachView(view: View)
        fun viewCaller() {}
        fun detachView()
    }

    interface Model {

    }

}