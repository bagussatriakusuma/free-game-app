package com.example.challengechapter7.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.auth.UserLoginRequest
import com.example.presentation.usecase.auth.login.UserLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLoginUseCase: UserLoginUseCase
): ViewModel() {
    private val _openHomePage = MutableLiveData<Boolean>()
    val openHomePage: LiveData<Boolean> = _openHomePage

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun userLogin(request: UserLoginRequest){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _openHomePage.value = userLoginUseCase.invoke(request)
                }
            }catch (e: Exception) {
                withContext(Dispatchers.Main){
                    _error.value = e.message
                }
            }
        }
    }
}