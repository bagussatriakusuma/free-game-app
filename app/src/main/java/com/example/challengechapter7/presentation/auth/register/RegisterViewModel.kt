package com.example.challengechapter7.presentation.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.auth.UserRegisterRequest
import com.example.presentation.usecase.auth.register.UserRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRegisterUseCase: UserRegisterUseCase,
    private val dispatcher: CoroutineContext
): ViewModel() {
    private val _openLoginPage = MutableLiveData<Boolean>()
    val openLoginPage: LiveData<Boolean> = _openLoginPage

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun userRegister(request: UserRegisterRequest){
        viewModelScope.launch(dispatcher){
            try{
                withContext(Dispatchers.Main){
                    _openLoginPage.value = userRegisterUseCase.invoke(request)
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    _error.value = e.message
                }
            }
        }
    }
}