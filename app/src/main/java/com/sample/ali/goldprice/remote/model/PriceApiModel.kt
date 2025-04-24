package com.sample.ali.goldprice.remote.model

data class PriceApiModel(
    val currency: ArrayList<GoldAndCurrencyModel>,
    val gold: ArrayList<GoldAndCurrencyModel>
)