package com.example.challengechapter7.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.challengechapter7.presentation.main.detail.DetailGameViewModel
import com.example.domain.model.main.DetailGames
import com.example.domain.model.main.ScreenshotsGames
import com.example.domain.repository.BookmarkRepository
import com.example.presentation.usecase.main.detail.GameDetailsUseCase
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import kotlin.coroutines.CoroutineContext

//@ExperimentalCoroutinesApi
//class DetailGameViewModelTest {
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private val testDispatcher = TestCoroutineDispatcher()
//    private val testCoroutineContext = testDispatcher + CoroutineName("TestCoroutine")
//    private val testScope = TestCoroutineScope(testDispatcher)
//
//    private val gameDetailsUseCase: GameDetailsUseCase = mock(GameDetailsUseCase::class.java)
//    private val bookmarkRepository: BookmarkRepository = mock()
//    private val dispatcher: CoroutineContext = testCoroutineContext
//
//    private val showGameDetailsObserver: Observer<DetailGames> = mock()
//    private val bookmarkClickedObserver: Observer<Int> = mock()
//    private val isBookmarkedObserver: Observer<Boolean> = mock()
//    private val errorObserver: Observer<String?> = mock()
//
//    private lateinit var detailGameViewModel: DetailGameViewModel
//
//    @Before
//    fun setup() {
//        Dispatchers.setMain(testDispatcher)
//        detailGameViewModel = DetailGameViewModel(gameDetailsUseCase, bookmarkRepository, dispatcher)
//        detailGameViewModel.showGameDetails.observeForever(showGameDetailsObserver)
//        detailGameViewModel.bookmarkClicked.observeForever(bookmarkClickedObserver)
//        detailGameViewModel.isBookmarked.observeForever(isBookmarkedObserver)
//        detailGameViewModel.error.observeForever(errorObserver)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//        testScope.cleanupTestCoroutines()
//    }
//
//    @Test
//    fun `test gameDetails success`() = testScope.runBlockingTest {
//        // Given
//        val gameId = 1
//        val expectedDetailGames = DetailGames(
//            id = 1,
//            title = "Game 1",
//            thumbnail = "img1",
//            developer = "Dev1",
//            freetogameProfileUrl = "url1",
//            gameUrl = "url1",
//            genre = "Genre1",
//            platform = "PC",
//            publisher = "Valve",
//            releaseDate = "January",
//            shortDescription = "desc1",
//            status = "live",
//            description = "desc1",
//            screenshots = listOf(
//                ScreenshotsGames(id = 1, image = "screenshot1_url"),
//                ScreenshotsGames(id = 2, image = "screenshot2_url")
//            )
//        )
//        `when`(gameDetailsUseCase.invoke(gameId)).thenReturn(expectedDetailGames)
//
//        // When
//        detailGameViewModel.gameDetails(gameId)
//
//        // Then
//        testDispatcher.scheduler.advanceTimeBy(1_000)
//        testDispatcher.scheduler.runCurrent()
//        verify(showGameDetailsObserver).onChanged(expectedDetailGames)
//        verify(errorObserver, never()).onChanged(any())
//    }
//
//    @Test
//    fun `test gameDetails failure`() = testScope.runBlockingTest {
//        // Given
//        val gameId = 1
//        val errorMessage = "Failed to load game details"
//        `when`(gameDetailsUseCase.invoke(gameId)).thenThrow(RuntimeException(errorMessage))
//
//        // When
//        detailGameViewModel.gameDetails(gameId)
//
//        // Then
//        testDispatcher.scheduler.advanceTimeBy(1_000)
//        testDispatcher.scheduler.runCurrent()
//
//        // Adjust the verification to check for the error instead of showGameDetailsObserver
//        verify(showGameDetailsObserver, never()).onChanged(any())
//        verify(errorObserver).onChanged(eq(errorMessage))
//
//        verify(bookmarkClickedObserver, never()).onChanged(any())
//        verify(isBookmarkedObserver, never()).onChanged(any())
//    }
//
//
//    @Test
//    fun `test onBookmarkIconClick`() {
//        // Given
//        val gameId = 1
//
//        // When
//        detailGameViewModel.onBookmarkIconClick(gameId)
//
//        // Then
//        verify(bookmarkClickedObserver).onChanged(gameId)
//    }
//
//    @Test
//    fun `test insertToBookmark success`() = testScope.runBlockingTest {
//        // Given
//        val gameId = 1
//        val expectedDetailGames = DetailGames(
//            id = 1,
//            title = "Game 1",
//            thumbnail = "img1",
//            developer = "Dev1",
//            freetogameProfileUrl = "url1",
//            gameUrl = "url1",
//            genre = "Genre1",
//            platform = "PC",
//            publisher = "Valve",
//            releaseDate = "January",
//            shortDescription = "desc1",
//            status = "live",
//            description = "desc1",
//            screenshots = listOf(
//                ScreenshotsGames(id = 1, image = "screenshot1_url"),
//                ScreenshotsGames(id = 2, image = "screenshot2_url")
//            )
//        )
//        `when`(gameDetailsUseCase.invoke(gameId)).thenReturn(expectedDetailGames)
//
//        // When
//        detailGameViewModel.insertToBookmark(gameId)
//
//        // Then
//        testDispatcher.scheduler.advanceTimeBy(1_000)
//        testDispatcher.scheduler.runCurrent()
//        verify(bookmarkRepository).insertBookmark(any())
//        verify(errorObserver, never()).onChanged(any())
//    }
//
//    @Test
//    fun `test insertToBookmark failure`() = testScope.runBlockingTest {
//        // Given
//        val gameId = 1
//        val errorMessage = "Failed to load game details"
//        `when`(gameDetailsUseCase.invoke(gameId)).thenThrow(RuntimeException(errorMessage))
//
//        // When
//        detailGameViewModel.insertToBookmark(gameId)
//
//        // Then
//        testDispatcher.scheduler.advanceTimeBy(1_000)
//        testDispatcher.scheduler.runCurrent()
//        verify(bookmarkRepository, never()).insertBookmark(any())
//        verify(errorObserver).onChanged(eq(errorMessage))
//    }
//}
