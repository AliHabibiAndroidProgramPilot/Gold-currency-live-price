package com.sample.ali.goldprice

import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.sample.ali.goldprice.mvp.ext.ActivityUtils
import com.sample.ali.goldprice.mvp.model.ModelMainActivity
import com.sample.ali.goldprice.mvp.presenter.PresenterMainActivity
import com.sample.ali.goldprice.mvp.view.ViewMainActivity


class MainActivity : AppCompatActivity(), ActivityUtils {

    private lateinit var presenter: PresenterMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        val model = ModelMainActivity()
        val view = ViewMainActivity(this, this)
        setContentView(view.binding.root)
        presenter = PresenterMainActivity(view, model, this)
        view.presenterContract = presenter
        presenter.presenterOnCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.presenterOnDestroy()
    }

    override fun getContext(): Context = this

    override fun getActivitySupportFragmentManager(): FragmentManager = supportFragmentManager

    override fun getSystemWindow(): Window = window

    override fun getActivityLifecycle(): Lifecycle = lifecycle

    override fun getActivityContentResolver(): ContentResolver = contentResolver

    override fun getRegisteredFragmentManager(): FragmentManager = supportFragmentManager

}