package com.sample.ali.goldprice.mvp.view

import android.content.Context
import android.view.LayoutInflater
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

}