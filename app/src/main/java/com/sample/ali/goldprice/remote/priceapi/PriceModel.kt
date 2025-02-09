package com.sample.ali.goldprice.remote.priceapi

data class PriceModel(
    val data: Data
)

data class Data(
    val cryptocurrencies: List<Cryptocurrency>,
    val currencies: List<Currency>,
    val golds: List<Gold>
)

data class Cryptocurrency(
    val label: String,
    val name: String,
    val price: Int
)

data class Currency(
    val name: String,
    val price: Int,
    val symbol: String
)

data class Gold(
    val label: String,
    val price: Int
)