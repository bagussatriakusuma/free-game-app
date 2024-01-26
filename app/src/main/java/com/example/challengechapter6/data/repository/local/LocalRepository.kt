package com.example.challengechapter6.data.repository.local

import androidx.lifecycle.LiveData
import com.example.challengechapter6.data.local.dao.BookmarkDAO
import com.example.challengechapter6.data.local.entity.BookmarkEntity
import com.example.challengechapter6.domain.BookmarkRepository
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