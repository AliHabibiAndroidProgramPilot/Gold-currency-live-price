package com.sample.ali.goldprice.timeapi

import com.google.gson.annotations.SerializedName

data class Date(
    @SerializedName("F") val monthFullName: String, // شهریور
    @SerializedName("Y") val yearFourDigit: String, // 1403
    @SerializedName("j") val dayOfMonthDigits: String, // 20
)