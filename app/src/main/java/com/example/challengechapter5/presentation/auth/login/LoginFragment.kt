package com.example.challengechapter5.presentation.auth.login

import android.os.Bundle
import android.text.Editable
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.challengechapter5.R
import com.example.challengechapter5.data.remote.request.auth.LoginRequest
import com.example.challengechapter5.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        bindView()
    }

    private fun observeLiveData(){
        viewModel.openHomePage.observe(viewLifecycleOwner, ::handleOpenHomePage)
        viewModel.error.observe(viewLifecycleOwner, ::handleError)
    }

    private fun bindView() {
        binding.btnLogin.setOnClickListener{ handleValidation() }
    }

    private fun handleOpenHomePage(isOpen: Boolean){
        if(isOpen){
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
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
            val request = LoginRequest(email, password)
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