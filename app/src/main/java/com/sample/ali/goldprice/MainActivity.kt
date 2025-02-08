package com.sample.ali.goldprice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.sample.ali.goldprice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isActivityReady = false
        installSplashScreen().setKeepOnScreenCondition { !isActivityReady }
        enableEdgeToEdge()
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightNavigationBars = false
        insetsController.isAppearanceLightStatusBars = false
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isActivityReady = loadActivity()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadActivity(): Boolean {
        binding.tabLayoutViewPager.adapter = TabLayoutAdapter(supportFragmentManager, lifecycle)
        val tabLayoutTitles = arrayListOf("قیمت طلا", "قیمت ارز")
        TabLayoutMediator(binding.tabLayout, binding.tabLayoutViewPager) { tab, position ->
            tab.text = tabLayoutTitles[position]
        }.attach()
        return true
    }
}