package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.data.local.entity.BookmarkEntity

interface BookmarkRepository {
    suspend fun insertBookmark(bookmarkEntity: BookmarkEntity)

    fun getBookmarkedGames(): LiveData<List<BookmarkEntity>>

    suspend fun deleteBookmarkedGames(bookmarkEntity: BookmarkEntity)
}