package com.sample.ali.goldprice.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayoutMediator
import com.sample.ali.goldprice.InternetUnavailableFragment
import com.sample.ali.goldprice.R
import com.sample.ali.goldprice.adapters.TabLayoutAdapter
import com.sample.ali.goldprice.databinding.ActivityMainBinding
import com.sample.ali.goldprice.mvp.ext.ActivityUtils

class ViewMainActivity(
    context: Context,
    private val utils: ActivityUtils
) {

    val binding: ActivityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(context))

    fun setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun setSystemBarsColor() {
        val window = utils.getSystemWindow()!!
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = false
        insetsController.isAppearanceLightNavigationBars = false
    }

    fun setInternetUnavailableFragment() {
        binding.fragmentContainer.visibility = View.VISIBLE
        val supportFragmentManager: FragmentManager = utils.getActivitySupportFragmentManager()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, InternetUnavailableFragment())
            .commit()
    }

    fun setMainTabLayout() {
        val supportFragmentManager = utils.getActivitySupportFragmentManager()
        val lifecycle = utils.getActivityLifecycle()
        binding.tabLayoutViewPager.adapter = TabLayoutAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.tabLayoutViewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "طلا"
                }

                1 -> {
                    tab.text = "ارز"
                }
            }
        }.attach()
    }

}