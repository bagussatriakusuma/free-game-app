package com.example.challengechapter6.domain

import androidx.lifecycle.LiveData
import com.example.challengechapter6.data.local.entity.BookmarkEntity

interface BookmarkRepository {
    suspend fun insertBookmark(bookmarkEntity: BookmarkEntity)

    fun getBookmarkedGames(): LiveData<List<BookmarkEntity>>

    suspend fun deleteBookmarkedGames(bookmarkEntity: BookmarkEntity)
}