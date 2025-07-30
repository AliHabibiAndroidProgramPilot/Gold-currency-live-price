package com.sample.ali.goldprice.mvp.ext

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.view.Window
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope

interface ActivityUtils {
    fun getContext(): Context? { return null }
    fun getSystemWindow(): Window? { return null }
    fun getActivitySupportFragmentManager(): FragmentManager
    fun getActivityLifecycle(): Lifecycle
    fun getActivityLifecycleScope(): LifecycleCoroutineScope
    fun getActivityContentResolver(): ContentResolver
    fun getRegisteredFragmentManager(): FragmentManager
    fun getAppResources(): Resources
}