package com.sample.ali.goldprice.remote.model

data class ApiModel(
    val currency: List<GoldAndCurrencyModel>,
    val gold: List<GoldAndCurrencyModel>
)