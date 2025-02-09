package com.sample.ali.goldprice.remote.timeapi

interface TimeApiRespond {

    fun onApiRespond(respond: TimeModel)

    fun onApiRespondFailure(message: String)

}