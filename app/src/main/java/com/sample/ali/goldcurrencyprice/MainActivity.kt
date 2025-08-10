package info.alihabibi.goldcurrencyprice

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import info.alihabibi.goldcurrencyprice.mvp.ext.ActivityUtils
import info.alihabibi.goldcurrencyprice.mvp.model.ModelMainActivity
import info.alihabibi.goldcurrencyprice.mvp.presenter.PresenterMainActivity
import info.alihabibi.goldcurrencyprice.mvp.view.ViewMainActivity


class MainActivity : AppCompatActivity(), ActivityUtils {

    private lateinit var presenter: PresenterMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        val model = ModelMainActivity(this)
        val view = ViewMainActivity(this, this)
        setContentView(view.binding.root)
        presenter = PresenterMainActivity(view, model, this)
        view.presenterContract = presenter
        presenter.presenterOnCreate()
    }

    override fun onStart() {
        presenter.presenterOnStart()
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.presenterOnDestroy()
    }

    override fun getContext(): Context = this

    override fun getActivitySupportFragmentManager(): FragmentManager = supportFragmentManager

    override fun getSystemWindow(): Window = window

    override fun getActivityLifecycle(): Lifecycle = lifecycle

    override fun getActivityLifecycleScope(): LifecycleCoroutineScope = lifecycleScope

    override fun getActivityContentResolver(): ContentResolver = contentResolver

    override fun getRegisteredFragmentManager(): FragmentManager = supportFragmentManager

    override fun getAppResources(): Resources = resources

}