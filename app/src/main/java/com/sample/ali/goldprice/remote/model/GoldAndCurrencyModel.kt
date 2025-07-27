package com.sample.ali.goldprice.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoldAndCurrencyModel(
    val name: String,
    val price: Int,
    @SerializedName("change_percent") val changePercent: Double
) : Parcelable