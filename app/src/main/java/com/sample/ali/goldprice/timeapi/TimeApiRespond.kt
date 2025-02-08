package com.sample.ali.goldprice.timeapi

interface TimeApiRespond {
    fun onApiRespond(respond: TimeModel)
    fun onApiRespondFailure(message: String)
}