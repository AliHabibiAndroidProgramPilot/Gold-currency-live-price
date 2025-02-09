package com.sample.ali.goldprice.remote.priceapi

data class Data(
    val cryptocurrencies: List<Cryptocurrency>,
    val currencies: List<Currency>,
    val golds: List<Gold>
)