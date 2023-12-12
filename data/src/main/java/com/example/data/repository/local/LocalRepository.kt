package com.example.data.repository.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.local.dao.BookmarkDAO
import com.example.data.local.entity.toBookmark
import com.example.data.local.entity.toBookmarkEntity
import com.example.domain.model.main.Bookmark
import com.example.domain.repository.BookmarkRepository
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val bookmarkDAO: BookmarkDAO
): BookmarkRepository {
    override suspend fun insertBookmark(bookmark: Bookmark) {
        bookmarkDAO.insertBookmark(bookmark.toBookmarkEntity())
    }

    override fun getBookmarkedGames(): LiveData<List<Bookmark>> {
        return bookmarkDAO.getBookmarkedGames().map { entities ->
            entities.map { it.toBookmark() }
        }
    }

    override suspend fun deleteBookmarkedGames(bookmark: Bookmark) {
        bookmarkDAO.deleteBookmarkedGames(bookmark.toBookmarkEntity())
    }
}