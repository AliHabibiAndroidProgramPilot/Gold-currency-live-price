package com.sample.ali.goldprice.remote.priceapi

data class PriceModel(
    val data: Data
)

data class Data(
    val cryptocurrencies: List<Cryptocurrency>,
    val currencies: List<GoldAndCurrencyContent>,
    val golds: List<GoldAndCurrencyContent>
)

data class Cryptocurrency(
    val label: String, // بیت کوین
    val name: String, // BitCoin
    val price: Int
)

data class GoldAndCurrencyContent(
    val label: String, // طلای 18 عیار | دلار
    val price: Int,
)