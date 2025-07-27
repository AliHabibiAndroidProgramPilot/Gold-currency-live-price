package com.sample.ali.goldprice.mvp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.sample.ali.goldprice.CurrencyPriceFragment
import com.sample.ali.goldprice.GoldPriceFragment
import com.sample.ali.goldprice.InternetUnavailableFragment
import com.sample.ali.goldprice.R
import com.sample.ali.goldprice.adapters.TabLayoutAdapter
import com.sample.ali.goldprice.databinding.ActivityMainBinding
import com.sample.ali.goldprice.databinding.WrongDateAndTimeLayoutBinding
import com.sample.ali.goldprice.mvp.ext.ActivityUtils
import com.sample.ali.goldprice.mvp.presenter.PresenterMainActivity
import com.sample.ali.goldprice.remote.model.PriceApiModel

class ViewMainActivity(
    context: Context,
    private val utils: ActivityUtils
) {

    val binding: ActivityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(context))

    private lateinit var goldPriceFragment: GoldPriceFragment

    private lateinit var currencyPriceFragment: CurrencyPriceFragment

    lateinit var presenterContract: PresenterMainActivity

    private val fragmentCallback = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentDetached(fm: FragmentManager, fragment: Fragment) {
            if (fragment is InternetUnavailableFragment) {
                presenterContract.fetchPrices()
            }
            super.onFragmentDetached(fm, fragment)
        }
    }

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
        binding.animLoading.visibility = View.GONE
        binding.fragmentContainer.visibility = View.VISIBLE
        val supportFragmentManager: FragmentManager = utils.getActivitySupportFragmentManager()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, InternetUnavailableFragment())
            .commit()
    }

    fun setMainTabLayout(data: PriceApiModel) {
        val bundle = Bundle().apply {
            putParcelable("DATA", data)
        }
        goldPriceFragment = GoldPriceFragment().apply {
            arguments = bundle
        }
        currencyPriceFragment = CurrencyPriceFragment().apply {
            arguments = bundle
        }
        val supportFragmentManager = utils.getActivitySupportFragmentManager()
        val lifecycle = utils.getActivityLifecycle()
        binding.tabLayoutViewPager.adapter = TabLayoutAdapter(
            supportFragmentManager,
            lifecycle,
            goldPriceFragment,
            currencyPriceFragment
        )
        TabLayoutMediator(binding.tabLayout, binding.tabLayoutViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "طلا"
                1 -> tab.text = "ارز"
            }
        }.attach()
    }

    fun visibleDateAndTimeHelperIcon() {
        binding.icDateAndTimeInfo.visibility = View.VISIBLE
    }

    fun setDateText(dateText: String) {
        binding.txtCurrentDatePersian.text = dateText
    }

    fun wrongDateAndTimeBottomSheet(context: Context) {
        if (binding.icDateAndTimeInfo.visibility == View.VISIBLE) {
            binding.icDateAndTimeInfo.setOnClickListener {
                val bottomSheet = BottomSheetDialog(context)
                val contentView =
                    WrongDateAndTimeLayoutBinding.inflate(LayoutInflater.from(context))
                bottomSheet.setContentView(contentView.root)
                bottomSheet.show()
            }
        }
    }

    fun manageLoadingAnimation(isShowing: Boolean) {
        if (isShowing)
            binding.animLoading.visibility = View.VISIBLE
        else
            binding.animLoading.visibility = View.GONE
    }

    fun registerFragmentCallback() {
        utils.getRegisteredFragmentManager()
            .registerFragmentLifecycleCallbacks(fragmentCallback, true)
    }

    fun unregisterFragmentCallback() {
        utils.getRegisteredFragmentManager().unregisterFragmentLifecycleCallbacks(fragmentCallback)
    }

}