package com.example.data

import androidx.lifecycle.MutableLiveData
import com.example.data.local.dao.BookmarkDAO
import com.example.data.local.entity.BookmarkEntity
import com.example.data.local.entity.toBookmark
import com.example.data.local.entity.toBookmarkEntity
import com.example.data.repository.local.LocalRepository
import com.example.domain.model.main.Bookmark
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalRepositoryTest {

    @Mock
    private lateinit var bookmarkDAO: BookmarkDAO

    private lateinit var localRepository: LocalRepository

    @Before
    fun setup() {
        localRepository = LocalRepository(bookmarkDAO)
    }

    @Test
    fun `test insertBookmark`() = runBlocking {
        // Given
        val bookmark = Bookmark(id = 1, thumbnail = "thumbnail1", title = "title1", genre = "Action")

        // When
        localRepository.insertBookmark(bookmark)

        // Then
        verify(bookmarkDAO).insertBookmark(bookmark.toBookmarkEntity())
    }


    @Test
    fun `test getBookmarkedGames`() {
        // Given
        val bookmarkEntities = listOf(
            BookmarkEntity(id = 1, thumbnail = "thumbnail1", title = "title1", genre = "Action")
        )

        `when`(bookmarkDAO.getBookmarkedGames()).thenReturn(MutableLiveData(bookmarkEntities))

        // When
        val bookmarkedGamesLiveData = localRepository.getBookmarkedGames()

        // Then
        assertNotNull(bookmarkedGamesLiveData.value) // This line is causing the failure

        // Instead of the above line, you should compare the expected and actual values
        assertEquals(bookmarkEntities.map { it.toBookmark() }, bookmarkedGamesLiveData.value)
    }


    @Test
    fun `test deleteBookmarkedGames`() = runBlocking {
        // Given
        val bookmark = Bookmark(id = 1, thumbnail = "thumbnail1", title = "title1", genre = "Action")

        // When
        localRepository.deleteBookmarkedGames(bookmark)

        // Then
        verify(bookmarkDAO).deleteBookmarkedGames(bookmark.toBookmarkEntity())
    }
}
