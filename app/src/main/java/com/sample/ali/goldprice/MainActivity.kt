package com.sample.ali.goldprice

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.sample.ali.goldprice.adapters.TabLayoutAdapter
import com.sample.ali.goldprice.databinding.ActivityMainBinding
import com.sample.ali.goldprice.remote.ApiRepository
import com.sample.ali.goldprice.remote.timeapi.TimeApiRespond
import com.sample.ali.goldprice.remote.timeapi.TimeModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.StringJoiner


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tabLayoutItems = arrayListOf("قیمت طلا", "قیمت ارز")
    private var hasNetworkCapabilities = false
    private var isNotShowingSplashScreen = false
    private lateinit var fragmentLifecycleCallbacks: FragmentLifecycleCallbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { !isNotShowingSplashScreen }
        enableEdgeToEdge()
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightNavigationBars = false
        insetsController.isAppearanceLightStatusBars = false
        hasNetworkCapabilities = checkConnectivityManager()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (hasNetworkCapabilities)
            isNotShowingSplashScreen = true
        else {
            lifecycleScope.launch {
                delay(4500)
                isNotShowingSplashScreen = true
            }
            binding.fragmentContainer.visibility = View.VISIBLE
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InternetUnavailableFragment())
                .commit()
        }
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

    override fun onStart() {
        super.onStart()
        fragmentLifecycleCallbacks = object : FragmentLifecycleCallbacks() {
            override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
                super.onFragmentDetached(fm, f)
                ApiRepository.instance.getTime(
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
            }
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
    }

    private fun checkConnectivityManager(): Boolean {
        val connectivityManager =
            getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true) {
            ApiRepository.instance.getTime(
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