package com.sample.ali.goldprice.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PriceApiModel(
    val currency: ArrayList<GoldAndCurrencyModel>,
    val gold: ArrayList<GoldAndCurrencyModel>
) : Parcelable