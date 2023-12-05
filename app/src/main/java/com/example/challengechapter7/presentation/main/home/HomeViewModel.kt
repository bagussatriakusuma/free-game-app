package com.example.challengechapter7.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.main.AllGames
import com.example.presentation.usecase.main.home.PopularGamesUseCase
import com.example.presentation.usecase.main.home.RecommendedGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recommendedGamesUseCase: RecommendedGamesUseCase,
    private val popularGamesUseCase: PopularGamesUseCase,
): ViewModel(){
    private val _showRecommendedGames = MutableLiveData<List<AllGames>>()
    val showRecommendedGames: LiveData<List<AllGames>> = _showRecommendedGames

    private val _showPopularMobaGames = MutableLiveData<List<AllGames>>()
    val showPopularMobaGames: LiveData<List<AllGames>> = _showPopularMobaGames

    private val _showPopularRacingGames = MutableLiveData<List<AllGames>>()
    val showPopularRacingGames: LiveData<List<AllGames>> = _showPopularRacingGames

    private val _showUser = MutableLiveData<com.example.data.remote.response.auth.GetUserResponse>()
    val showUser: LiveData<com.example.data.remote.response.auth.GetUserResponse> = _showUser

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun recommendedGames(){
        viewModelScope.launch(Dispatchers.Main){
            try {
                withContext(Dispatchers.Main){
                    _showRecommendedGames.value = recommendedGamesUseCase.invoke()
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
                    _showPopularMobaGames.value = popularGamesUseCase.invoke("pc", "moba")
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
                    _showPopularRacingGames.value = popularGamesUseCase.invoke("pc", "racing")
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    _error.value = e.message
                }
            }
        }
    }
}