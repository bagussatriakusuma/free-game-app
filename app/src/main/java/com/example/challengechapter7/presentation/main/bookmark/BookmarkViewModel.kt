package com.example.challengechapter7.presentation.main.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.local.entity.BookmarkEntity
import com.example.domain.model.main.Bookmark
import com.example.domain.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    private val _bookmarkedGames = MediatorLiveData<List<Bookmark>>()
    val bookmarkedGames: LiveData<List<Bookmark>> = _bookmarkedGames

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadBookmarkedGames()
    }

    fun loadBookmarkedGames() {
        _bookmarkedGames.addSource(bookmarkRepository.getBookmarkedGames()) { bookmarks ->
            _bookmarkedGames.value = bookmarks
        }
    }
}
