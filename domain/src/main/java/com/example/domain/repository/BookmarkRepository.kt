package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.model.main.Bookmark

interface BookmarkRepository {
    suspend fun insertBookmark(bookmark: Bookmark)

    fun getBookmarkedGames(): LiveData<List<Bookmark>>

    suspend fun deleteBookmarkedGames(bookmark: Bookmark)
}