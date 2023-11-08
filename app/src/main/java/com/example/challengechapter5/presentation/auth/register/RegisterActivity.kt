package com.example.challengechapter5.presentation.auth.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.activity.viewModels
import com.example.challengechapter5.R
import com.example.challengechapter5.data.remote.request.auth.LoginRequest
import com.example.challengechapter5.data.remote.request.auth.RegisterRequest
import com.example.challengechapter5.databinding.ActivityRegisterBinding
import com.example.challengechapter5.presentation.MainActivity
import com.example.challengechapter5.presentation.auth.login.LoginActivity
import com.example.challengechapter5.presentation.auth.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeLiveData()
        bindView()
    }

    private fun observeLiveData(){
        viewModel.openLoginPage.observe(this, ::handleOpenLoginPage)
        viewModel.error.observe(this, ::handleError)
    }

    private fun bindView() {
        binding.btnRegister.setOnClickListener{ handleValidation() }
    }

    private fun handleOpenLoginPage(isOpen: Boolean){
        if(isOpen){
            LoginActivity.startActivity(this)
        }
    }

    private fun handleError(error: String?){
        binding.tilName.error = error
        binding.tilEmail.error = error
        binding.tilPassword.error = error
    }

    private fun handleValidation(){
        val fullName = binding.etName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if (validator(fullName, email, password)){
            val request = RegisterRequest(fullName, email, password)
            viewModel.userRegister(request)
        }
    }

    private fun validator(
        fullName: String,
        email: String,
        password: String
    ): Boolean {
        resetErrors()

        return when {
            fullName.isEmpty() -> {
                binding.tilName.error = "Name cannot be empty"
                false
            }
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