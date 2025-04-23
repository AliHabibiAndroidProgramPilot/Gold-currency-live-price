package com.sample.ali.goldprice.remote.model

data class ApiModel(
    val currency: ArrayList<GoldAndCurrencyModel>,
    val gold: ArrayList<GoldAndCurrencyModel>
)