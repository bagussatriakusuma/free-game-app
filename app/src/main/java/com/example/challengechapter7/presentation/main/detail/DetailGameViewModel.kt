package com.example.challengechapter7.presentation.main.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.entity.BookmarkEntity
import com.example.domain.model.main.Bookmark
import com.example.domain.model.main.DetailGames
import com.example.domain.repository.BookmarkRepository
import com.example.presentation.usecase.main.detail.GameDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class DetailGameViewModel @Inject constructor(
    private val gameDetailsUseCase: GameDetailsUseCase,
    private val bookmarkRepository: BookmarkRepository,
    private val dispatcher: CoroutineContext
): ViewModel(){
    private val _showGameDetails = MutableLiveData<DetailGames>()
    val showGameDetails: LiveData<DetailGames> = _showGameDetails

    private val _bookmarkClicked = MutableLiveData<Int>()
    val bookmarkClicked: LiveData<Int> = _bookmarkClicked

    private val _isBookmarked = MutableLiveData<Boolean>()
    val isBookmarked: LiveData<Boolean> = _isBookmarked

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun gameDetails(id: Int){
        viewModelScope.launch(dispatcher){
            try {
                withContext(Dispatchers.Main){
                    _showGameDetails.value = gameDetailsUseCase.invoke(id)
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    _error.value = e.message
                }
            }
        }
    }

    fun onBookmarkIconClick(gameId: Int) {
        _bookmarkClicked.value = gameId
    }

    fun insertToBookmark(gameId: Int) {
        viewModelScope.launch(dispatcher) {
            try {
                val gameDetails = gameDetailsUseCase.invoke(gameId)
                val bookmark = Bookmark(
                    id = gameDetails.id,
                    title = gameDetails.title,
                    thumbnail = gameDetails.thumbnail,
                    genre = gameDetails.genre
                )
                bookmarkRepository.insertBookmark(bookmark)
                Log.d("DetailGameViewModel", "Game bookmarked successfully")

            } catch (e: Exception) {
                _error.postValue(e.message)
                Log.e("DetailGameViewModel", "Error bookmarking game", e)
            }
        }
    }

}