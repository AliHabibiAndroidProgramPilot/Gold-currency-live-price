package com.sample.ali.goldprice.remote.priceapi

import com.google.gson.annotations.SerializedName

data class PriceModel(
    val `data`: Data,
    @SerializedName("last_update") val lastUpdate: String,
)