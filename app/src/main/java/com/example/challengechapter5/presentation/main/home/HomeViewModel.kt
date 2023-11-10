package com.example.challengechapter5.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter5.data.remote.response.auth.GetUserResponse
import com.example.challengechapter5.data.remote.response.main.GetAllGamesResponse
import com.example.challengechapter5.domain.repository.AuthRepository
import com.example.challengechapter5.domain.repository.MainRepository
import com.example.challengechapter5.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository,
    private val mainRepository: MainRepository
): ViewModel(){
    private val _showRecommendedGames = MutableLiveData<List<GetAllGamesResponse>>()
    val showRecommendedGames: LiveData<List<GetAllGamesResponse>> = _showRecommendedGames

    private val _showPopularMobaGames = MutableLiveData<List<GetAllGamesResponse>>()
    val showPopularMobaGames: LiveData<List<GetAllGamesResponse>> = _showPopularMobaGames

    private val _showPopularRacingGames = MutableLiveData<List<GetAllGamesResponse>>()
    val showPopularRacingGames: LiveData<List<GetAllGamesResponse>> = _showPopularRacingGames

    private val _showUser = MutableLiveData<GetUserResponse>()
    val showUser: LiveData<GetUserResponse> = _showUser

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun recommendedGames(){
        viewModelScope.launch(Dispatchers.Main){
            try {
                withContext(Dispatchers.Main){
                    val result = mainRepository.getAllGames(platform = "all")
                    _showRecommendedGames.value = result
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    _error.value = e.message
                }
            }
        }
    }

    fun popularMobaGames(){
        viewModelScope.launch(Dispatchers.Main){
            try {
                withContext(Dispatchers.Main){
                    val result = mainRepository.getGamesBySorted(platform = "pc", category = "moba")
                    _showPopularMobaGames.value = result
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    _error.value = e.message
                }
            }
        }
    }

    fun popularRacingGames(){
        viewModelScope.launch(Dispatchers.Main){
            try {
                withContext(Dispatchers.Main){
                    val result = mainRepository.getGamesBySorted(platform = "pc", category = "racing")
                    _showPopularRacingGames.value = result
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
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = e.message
                }
            }
        }
    }
}