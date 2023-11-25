package com.example.challengechapter6.presentation.main.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.challengechapter6.databinding.ActivityRoutingBinding
import com.example.challengechapter6.presentation.MainActivity
import com.example.challengechapter6.presentation.auth.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoutingBinding
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityRoutingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashScreen.setKeepOnScreenCondition { true }

        observeLiveData()
        viewModel.checkLoggedIn()
    }

    private fun observeLiveData(){
        viewModel.openLoginPage.observe(this, ::handleOpenLoginPage)
        viewModel.openHomePage.observe(this, ::handleOpenHomePage)
    }

    private fun startToNextActivity(isLoggedIn: Boolean){
        if (isLoggedIn) {
            MainActivity.startActivity(this)
        } else {
            LoginActivity.startActivity(this)
        }
        finish()
    }

    private fun handleOpenHomePage(isLoggedIn: Boolean) {
        if(isLoggedIn){
            startToNextActivity(true)
        }
    }

    private fun handleOpenLoginPage(isLoggedOut: Boolean) {
        if(isLoggedOut){
            startToNextActivity(false)
        }
    }
}