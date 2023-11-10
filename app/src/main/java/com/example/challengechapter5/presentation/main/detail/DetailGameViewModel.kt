package com.example.challengechapter5.presentation.main.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter5.data.remote.response.main.GetAllGamesResponse
import com.example.challengechapter5.data.remote.response.main.GetDetailGameResponse
import com.example.challengechapter5.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailGameViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel(){
    private val _showGameDetails = MutableLiveData<GetDetailGameResponse>()
    val showGameDetails: LiveData<GetDetailGameResponse> = _showGameDetails

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun gameDetails(id: Int){
        viewModelScope.launch(Dispatchers.Main){
            try {
                withContext(Dispatchers.Main){
                    val result = mainRepository.getDetailGames(id)
                    _showGameDetails.value = result
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    _error.value = e.message
                }
            }
        }
    }
}