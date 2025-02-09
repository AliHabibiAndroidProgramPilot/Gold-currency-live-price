package com.sample.ali.goldprice

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.sample.ali.goldprice.remote.priceapi.PriceApiRespond
import com.sample.ali.goldprice.remote.priceapi.PriceModel
import com.sample.ali.goldprice.remote.timeapi.TimeApiRespond
import com.sample.ali.goldprice.remote.timeapi.TimeModel
import java.util.StringJoiner


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
        isActivityReady = loadActivity()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loadActivity(): Boolean {
        val apiRepositoryInstance = ApiRepository.instance
        // region Set Time Text
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
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                }

            }
        )
        // endregion
        // region Set Prices
        apiRepositoryInstance.getPrices(
            object : PriceApiRespond {
                override fun onApiRespond(respond: PriceModel) {

                }

                override fun onApiRespondFailure(message: String) {
                    Log.i("API", message)
                }
            }
        )
        // endregion
        // region Build Up Tab Layout
        binding.tabLayoutViewPager.adapter = TabLayoutAdapter(supportFragmentManager, lifecycle)
        val tabLayoutTitles = arrayListOf("قیمت طلا", "قیمت ارز")
        TabLayoutMediator(binding.tabLayout, binding.tabLayoutViewPager) { tab, position ->
            tab.text = tabLayoutTitles[position]
        }.attach()
        // endregion
        return true
    }
}