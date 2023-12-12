package com.example.challengechapter7.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challengechapter7.presentation.main.bookmark.BookmarkViewModel
import com.example.domain.model.main.Bookmark
import com.example.domain.repository.BookmarkRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var bookmarkRepository: BookmarkRepository

    private lateinit var bookmarkViewModel: BookmarkViewModel

    private lateinit var testDispatcher: TestCoroutineDispatcher

    @Before
    fun setup() {
        testDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(testDispatcher)

        MockitoAnnotations.initMocks(this)

        bookmarkViewModel = BookmarkViewModel(bookmarkRepository)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test loadBookmarkedGames success`() = testDispatcher.runBlockingTest {
        // Given
        val bookmarkList = listOf(
            Bookmark(id = 1, thumbnail = "thumbnail1", title = "title1", genre = "Action")
        )

        val liveDataBookmarkList: LiveData<List<Bookmark>> = MutableLiveData(bookmarkList)

        `when`(bookmarkRepository.getBookmarkedGames()).thenReturn(liveDataBookmarkList)

        // When
        bookmarkViewModel.loadBookmarkedGames()

        // Then
        testDispatcher.scheduler.advanceTimeBy(1_000)
        testDispatcher.scheduler.runCurrent()
        verify(bookmarkRepository).getBookmarkedGames()
        assertEquals(bookmarkList, bookmarkViewModel.bookmarkedGames.value)
        assertNull(bookmarkViewModel.error.value)
    }


}
