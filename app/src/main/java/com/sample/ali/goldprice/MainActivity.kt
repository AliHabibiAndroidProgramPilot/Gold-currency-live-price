package com.sample.ali.goldprice

import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentManager
import com.sample.ali.goldprice.mvp.ext.ActivityUtils
import com.sample.ali.goldprice.mvp.model.ModelMainActivity
import com.sample.ali.goldprice.mvp.presenter.PresenterMainActivity
import com.sample.ali.goldprice.mvp.view.ViewMainActivity


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
        val view = ViewMainActivity(this, this)
        setContentView(view.binding.root)
        presenter = PresenterMainActivity(view, model, this)
        presenter.presenterOnCreate()

        /*
        binding.tabLayoutViewPager.adapter = TabLayoutAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.tabLayoutViewPager) { tab, position ->
            tab.text = tabLayoutItems[position]
        }.attach()*/
    }

    /*override fun onStart() {
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
    }*/

    /*override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
    }*/

    override fun onDestroy() {
        super.onDestroy()
        presenter.presenterOnDestroy()
    }

    override fun getContext(): Context = this

    override fun getActivitySupportFragmentManager(): FragmentManager = supportFragmentManager

    override fun getSystemWindow(): Window = window

}