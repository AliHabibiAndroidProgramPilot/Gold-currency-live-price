package com.sample.ali.goldprice.remote.priceapi

data class PriceModel(
    val data: Data
//  val message: String "به روزرسانی شده در تاریخ فلان"
)

data class Data(
    val cryptocurrencies: ArrayList<Cryptocurrency>,
    val currencies: ArrayList<GoldAndCurrencyContent>,
    val golds: ArrayList<GoldAndCurrencyContent>
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