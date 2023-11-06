package com.example.challengechapter5.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter5.data.remote.response.auth.GetUserResponse
import com.example.challengechapter5.domain.repository.AuthRepository
import com.example.challengechapter5.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository
): ViewModel() {
    private val _showUser = MutableLiveData<GetUserResponse>()
    val showUser: LiveData<GetUserResponse> = _showUser

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun getUserData(){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                authRepository.getDataUser(token = tokenRepository.getToken()!!)
            }.onFailure { exception ->
                withContext(Dispatchers.Main) {
                    _error.value = exception.message
                }
            }.onSuccess { user ->
                withContext(Dispatchers.Main) {
                    _showUser.value = user
                }
            }
        }
    }
}