package com.sample.ali.goldprice.remote.timeapi

import com.google.gson.annotations.SerializedName

data class TimeModel(
    val date: TimeModelDate
)

data class TimeModelDate(
    @SerializedName("F") val monthFullName: String, // شهریور
    @SerializedName("Y") val yearFourDigit: String, // 1403
    @SerializedName("j") val dayOfMonthDigits: String, // 20
)