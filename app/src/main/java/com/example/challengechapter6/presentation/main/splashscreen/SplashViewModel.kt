package com.example.challengechapter6.presentation.main.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter6.presentation.usecase.main.splashscreen.CheckLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkLoggedInUseCase: CheckLoggedInUseCase
): ViewModel(){
    private val _openHomePage = MutableLiveData<Boolean>()
    val openHomePage: LiveData<Boolean> = _openHomePage

    private val _openLoginPage = MutableLiveData<Boolean>()
    val openLoginPage: LiveData<Boolean> = _openLoginPage

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun checkLoggedIn(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val isLoggedIn = checkLoggedInUseCase.invoke()
                withContext(Dispatchers.Main) {
                    if (isLoggedIn) _openLoginPage.value = true
                    else _openHomePage.value = true
                }
            }catch (e: Exception) {
                withContext(Dispatchers.Main){
                    _error.value = e.message
                }
            }
        }
    }
}