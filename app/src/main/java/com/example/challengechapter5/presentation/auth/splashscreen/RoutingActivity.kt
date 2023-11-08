package com.example.challengechapter5.presentation.auth.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.challengechapter5.R
import com.example.challengechapter5.databinding.ActivityRoutingBinding
import com.example.challengechapter5.presentation.MainActivity
import com.example.challengechapter5.presentation.auth.login.LoginActivity
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

    private fun startToNextActivity(isLoggedIn: Boolean){
        if (isLoggedIn) {
            MainActivity.startActivity(this)
        } else {
            LoginActivity.startActivity(this)
        }
        finish()
    }

    private fun observeLiveData(){
        viewModel.openLoginPage.observe(this, ::handleOpenLoginPage)
        viewModel.openHomePage.observe(this, ::handleOpenHomePage)
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