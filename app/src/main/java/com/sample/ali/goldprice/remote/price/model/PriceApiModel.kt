package com.sample.ali.goldprice.remote.price.model

data class PriceApiModel(
    val currency: ArrayList<GoldAndCurrencyModel>,
    val gold: ArrayList<GoldAndCurrencyModel>
)