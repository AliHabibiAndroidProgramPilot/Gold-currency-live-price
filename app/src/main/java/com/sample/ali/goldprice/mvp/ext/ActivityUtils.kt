package com.sample.ali.goldprice.mvp.ext

import android.content.Context
import android.view.Window
import androidx.fragment.app.FragmentManager

interface ActivityUtils {
    fun getContext(): Context? { return null }
    fun getSystemWindow(): Window? { return null }
    fun getActivitySupportFragmentManager(): FragmentManager
}