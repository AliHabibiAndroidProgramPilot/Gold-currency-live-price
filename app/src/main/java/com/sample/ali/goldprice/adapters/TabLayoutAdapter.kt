package com.sample.ali.goldprice.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sample.ali.goldprice.CurrencyPriceFragment
import com.sample.ali.goldprice.GoldPriceFragment

class TabLayoutAdapter(
    supportFragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(supportFragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> GoldPriceFragment()
        1 -> CurrencyPriceFragment()
        else -> GoldPriceFragment()
    }

}