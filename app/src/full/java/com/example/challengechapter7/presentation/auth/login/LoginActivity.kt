package com.example.challengechapter7.presentation.auth.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.challengechapter7.databinding.ActivityLoginBinding
import com.example.challengechapter7.presentation.MainActivity
import com.example.challengechapter7.presentation.auth.register.RegisterActivity
import com.example.domain.model.auth.UserLoginRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeLiveData()
        bindView()
    }

    private fun observeLiveData(){
        viewModel.openHomePage.observe(this, ::handleOpenHomePage)
        viewModel.openHomePage.observe(this){ isLoggedIn ->
            if(isLoggedIn){
                binding.btnLogin.setOnClickListener {
                    //coding open bottom sheet dialog
                }
            }
        }
        viewModel.error.observe(this, ::handleError)
    }

    private fun bindView() {
        binding.btnLogin.setOnClickListener{ handleValidation() }
        binding.tvRegister.setOnClickListener{ RegisterActivity.startActivity(this) }
    }

    private fun handleOpenHomePage(isLoggedIn: Boolean){
        if(isLoggedIn){
            MainActivity.startActivity(this)
        }
    }

    private fun handleError(error: String?){
        binding.tilEmail.error = error
        binding.tilPassword.error = error
    }

    private fun handleValidation(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if (validator(email, password)){
            val request = UserLoginRequest(email, password)
            viewModel.userLogin(request)
        }
    }

    private fun validator(email: String, password: String): Boolean {
        resetErrors()

        return when {
            email.isEmpty() -> {
                binding.tilEmail.error = "Email cannot be empty"
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.tilEmail.error = "Invalid email address"
                false
            }
            password.isEmpty() -> {
                binding.tilPassword.error = "Password cannot be empty"
                false
            }
            password.length < 8 -> {
                binding.tilPassword.error = "Your password is less than 8 characters"
                false
            }
            else -> {
                true
            }
        }
    }

    private fun resetErrors() {
        binding.tilEmail.error = null
        binding.tilPassword.error = null
    }
}