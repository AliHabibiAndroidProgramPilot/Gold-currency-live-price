package com.sample.ali.goldprice

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
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
import com.sample.ali.goldprice.mvp.ext.ActivityUtils
import com.sample.ali.goldprice.mvp.model.ModelMainActivity
import com.sample.ali.goldprice.mvp.presenter.PresenterMainActivity
import com.sample.ali.goldprice.mvp.view.ViewMainActivity
import com.sample.ali.goldprice.remote.ApiRepository
import com.sample.ali.goldprice.remote.timeapi.TimeApiRespond
import com.sample.ali.goldprice.remote.timeapi.TimeModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.StringJoiner


class MainActivity : AppCompatActivity(), ActivityUtils {

    private lateinit var presenter: PresenterMainActivity

    /*private val tabLayoutItems = arrayListOf("قیمت طلا", "قیمت ارز")
    private var hasNetworkCapabilities = false
    private var isNotShowingSplashScreen = false
    private lateinit var fragmentLifecycleCallbacks: FragmentLifecycleCallbacks*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        val model = ModelMainActivity()
        val view = ViewMainActivity(this)
        setContentView(view.binding.root)
        presenter = PresenterMainActivity(view, model, this)
        presenter.presenterOnCreate()
        /*val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightNavigationBars = false
        insetsController.isAppearanceLightStatusBars = false*/


        /*if (hasNetworkCapabilities)
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


        binding.tabLayoutViewPager.adapter = TabLayoutAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.tabLayoutViewPager) { tab, position ->
            tab.text = tabLayoutItems[position]
        }.attach()*/
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

    override fun getContext(): Context = this

}