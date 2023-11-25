package com.example.challengechapter6.presentation.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter6.presentation.usecase.main.profile.ClearDataUserUseCase
import com.example.challengechapter6.presentation.usecase.main.profile.GetDataUserUseCase
import com.example.challengechapter6.presentation.usecase.main.profile.UpdateDataUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val updateDataUserUseCase: UpdateDataUserUseCase,
    private val getDataUserUseCase: GetDataUserUseCase,
    private val clearDataUserUseCase: ClearDataUserUseCase
): ViewModel(){
    private val _editProfile = MutableLiveData<Boolean>()
    val editProfile: LiveData<Boolean> = _editProfile

    private val _showUser = MutableLiveData<com.example.data.remote.response.auth.GetUserResponse>()
    val showUser: LiveData<com.example.data.remote.response.auth.GetUserResponse> = _showUser

    private val _openLoginPage = MutableLiveData<Boolean>()
    val openLoginPage: LiveData<Boolean> = _openLoginPage

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun updateDataUser(
        picture: File?,
        name: String,
        phoneNumber: String,
        city: String,
        address: String
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                updateDataUserUseCase.invoke(picture, name, phoneNumber, city, address)
                withContext(Dispatchers.Main) {
                    _editProfile.value = true
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = e.message
                }
            }
        }
    }


    fun getDataUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _showUser.value = getDataUserUseCase.invoke()
                }
            }catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = e.message
                }
            }
        }
    }

    fun clearDataUser(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                clearDataUserUseCase.invoke()
                withContext(Dispatchers.Main) {
                    _openLoginPage.value = true
                }
            }catch (e: Exception) {
                withContext(Dispatchers.Main){
                    _error.value = e.message
                }
            }
        }
    }
}