package com.example.challengechapter5.presentation.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter5.data.remote.response.auth.GetUserResponse
import com.example.challengechapter5.domain.repository.AuthRepository
import com.example.challengechapter5.domain.repository.TokenRepository
import com.example.challengechapter5.utils.reduceFileImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository
): ViewModel(){
    private val _editProfile = MutableLiveData<Boolean>()
    val editProfile: LiveData<Boolean> = _editProfile

    private val _showUser = MutableLiveData<GetUserResponse>()
    val showUser: LiveData<GetUserResponse> = _showUser

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun updateDataUser(
        image: File?,
        name: String,
        phoneNumber: String
    ){
        val requestFile = image?.let { reduceFileImage(it).asRequestBody("image/jpg".toMediaTypeOrNull()) }
        val imageRequest =
            requestFile?.let { MultipartBody.Part.createFormData("image", image.name, it) }
        val nameRequest = name.toRequestBody("text/plain".toMediaType())
        val phoneNumberRequest = phoneNumber.toRequestBody("text/plain".toMediaType())

        viewModelScope.launch(Dispatchers.Main){
            try {
                authRepository.updateDataUser(
                    token = tokenRepository.getToken()!!,
                    imageRequest,
                    nameRequest,
                    phoneNumberRequest
                )
                withContext(Dispatchers.Main) {
                    _editProfile.value = true
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    _error.value = e.message
                }
            }
        }
    }

    fun getDataUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    val result = authRepository.getDataUser(token = tokenRepository.getToken()!!)
                    _showUser.value = result
                }
            }catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = e.message
                }
            }
        }
    }
}