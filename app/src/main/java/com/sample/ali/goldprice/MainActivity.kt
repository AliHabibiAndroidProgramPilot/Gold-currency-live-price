package com.sample.ali.goldprice

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.sample.ali.goldprice.adapters.TabLayoutAdapter
import com.sample.ali.goldprice.databinding.ActivityMainBinding
import com.sample.ali.goldprice.remote.ApiRepository
import com.sample.ali.goldprice.remote.timeapi.TimeApiRespond
import com.sample.ali.goldprice.remote.timeapi.TimeModel
import java.util.StringJoiner


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tabLayoutItems = arrayListOf("قیمت طلا", "قیمت ارز")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var hasNetworkCapabilities = false
        installSplashScreen().setKeepOnScreenCondition{ !hasNetworkCapabilities }
        enableEdgeToEdge()
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightNavigationBars = false
        insetsController.isAppearanceLightStatusBars = false
        hasNetworkCapabilities = checkConnectivityManager()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.tabLayoutViewPager.adapter = TabLayoutAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.tabLayoutViewPager) { tab, position ->
            tab.text = tabLayoutItems[position]
        }.attach()
    }

    private fun checkConnectivityManager(): Boolean {
        val connectivityManager =
            getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true) {
            val apiRepositoryInstance = ApiRepository.instance
            apiRepositoryInstance.getTime(
                object : TimeApiRespond {
                    override fun onApiRespond(respond: TimeModel) {
                        binding.txtCurrentDatePersian.text = StringJoiner(" ")
                            .add(respond.date.dayOfMonthDigits)
                            .add(respond.date.monthFullName)
                            .add(respond.date.yearFourDigit)
                            .toString()
                    }

                    override fun onApiRespondFailure(message: String) {
                        Log.e("API", message)
                    }

                }
            )
            return true
        } else
            return false
    }
}