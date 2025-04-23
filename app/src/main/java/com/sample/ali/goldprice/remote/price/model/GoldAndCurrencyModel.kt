package com.sample.ali.goldprice.remote.price.model

import com.google.gson.annotations.SerializedName

data class GoldAndCurrencyModel(
    val name: String,
    val price: Int,
    @SerializedName("change_percent") val changePercent: Double
)