package com.sample.ali.goldprice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sample.ali.goldprice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        bindind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindind.root)
        ViewCompat.setOnApplyWindowInsetsListener(bindind.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}