package com.example.data.repository.local

import androidx.lifecycle.LiveData
import com.example.data.local.dao.BookmarkDAO
import com.example.data.local.entity.BookmarkEntity
import com.example.domain.repository.BookmarkRepository
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val bookmarkDAO: BookmarkDAO
): BookmarkRepository {
    override suspend fun insertBookmark(bookmarkEntity: BookmarkEntity) {
        return bookmarkDAO.insertBookmark(bookmarkEntity)
    }

    override fun getBookmarkedGames(): LiveData<List<BookmarkEntity>> {
        return bookmarkDAO.getBookmarkedGames()
    }

    override suspend fun deleteBookmarkedGames(bookmarkEntity: BookmarkEntity) {
        return bookmarkDAO.deleteBookmarkedGames(bookmarkEntity)
    }
}