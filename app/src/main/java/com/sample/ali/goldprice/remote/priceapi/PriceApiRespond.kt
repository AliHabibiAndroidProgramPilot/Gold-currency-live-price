package com.sample.ali.goldprice.remote.priceapi

interface PriceApiRespond {

    fun onApiRespond(respond: PriceModel)

    fun onApiRespondFailure(message: String)

}