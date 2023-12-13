package com.example.challengechapter7.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.challengechapter7.presentation.main.home.HomeViewModel
import com.example.data.remote.response.auth.GetUserResponse
import com.example.domain.model.main.AllGames
import com.example.presentation.usecase.main.home.PopularGamesUseCase
import com.example.presentation.usecase.main.home.RecommendedGamesUseCase
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
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.kotlin.argumentCaptor

//@ExperimentalCoroutinesApi
//class HomeViewModelTest {
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//    private val testDispatcher = TestCoroutineDispatcher()
//    private val testCoroutineContext = testDispatcher + CoroutineName("TestCoroutine")
//    private val testScope = TestCoroutineScope(testDispatcher)
//
//    private val recommendedGamesUseCase: RecommendedGamesUseCase = mock(RecommendedGamesUseCase::class.java)
//    private val popularGamesUseCase: PopularGamesUseCase = mock(PopularGamesUseCase::class.java)
//
//    private val showRecommendedGamesObserver: Observer<List<AllGames>> = mock()
//    private val showPopularMobaGamesObserver: Observer<List<AllGames>> = mock()
//    private val showPopularRacingGamesObserver: Observer<List<AllGames>> = mock()
//    private val showUserObserver: Observer<GetUserResponse> = mock()
//    private val errorObserver: Observer<String?> = mock()
//
//    private lateinit var homeViewModel: HomeViewModel
//
//    @Before
//    fun setup() {
//        Dispatchers.setMain(testDispatcher)
//        homeViewModel = HomeViewModel(
//            recommendedGamesUseCase,
//            popularGamesUseCase,
//            testCoroutineContext
//        )
//
//        homeViewModel.showRecommendedGames.observeForever(showRecommendedGamesObserver)
//        homeViewModel.showPopularMobaGames.observeForever(showPopularMobaGamesObserver)
//        homeViewModel.showPopularRacingGames.observeForever(showPopularRacingGamesObserver)
//        homeViewModel.showUser.observeForever(showUserObserver)
//        homeViewModel.error.observeForever(errorObserver)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//        testScope.cleanupTestCoroutines()
//    }
//
//    @Test
//    fun `test recommendedGames success`() = testScope.runBlockingTest {
//        // Given
//        val expectedRecommendedGames = listOf(
//            AllGames(id = 1, title = "Game 1", thumbnail = "img1", developer = "Dev1",
//                freetogameProfileUrl = "url1", gameUrl = "url1", genre = "Genre1", platform = "PC",
//                publisher = "Valve", releaseDate = "January", shortDescription = "desc1"),
//            AllGames(id = 2, title = "Game 2", thumbnail = "img2", developer = "Dev2",
//                freetogameProfileUrl = "url2", gameUrl = "url2", genre = "Genre2", platform = "Xbox",
//                publisher = "Capcom", releaseDate = "February", shortDescription = "desc2")
//        )
//        `when`(recommendedGamesUseCase.invoke()).thenReturn(expectedRecommendedGames)
//
//        // When
//        homeViewModel.recommendedGames()
//
//        // Then
//        testDispatcher.scheduler.advanceTimeBy(1_000)
//        testDispatcher.scheduler.runCurrent()
//        verify(showRecommendedGamesObserver).onChanged(expectedRecommendedGames)
//        verify(errorObserver, never()).onChanged(any())
//    }
//
//    @Test
//    fun `test recommendedGames failure`() = testScope.runBlockingTest {
//        // Given
//        val errorMessage = "Failed to load recommended games"
//        `when`(recommendedGamesUseCase.invoke()).thenThrow(RuntimeException(errorMessage))
//
//        // When
//        homeViewModel.recommendedGames()
//
//        // Then
//        testDispatcher.scheduler.advanceTimeBy(1_000)
//        testDispatcher.scheduler.runCurrent()
//        verify(showRecommendedGamesObserver, never()).onChanged(anyList())
//        verify(errorObserver).onChanged(eq(errorMessage))
//    }
//
//    @Test
//    fun `test popularMobaGames success`() = testScope.runBlockingTest {
//        // Given
//        val expectedPopularMobaGames = listOf(
//            AllGames(id = 1, title = "Moba Game 1", thumbnail = "img1", developer = "Dev1",
//                freetogameProfileUrl = "url1", gameUrl = "url1", genre = "Moba", platform = "PC",
//                publisher = "Valve", releaseDate = "January", shortDescription = "moba desc1"),
//            AllGames(id = 2, title = "Moba Game 2", thumbnail = "img2", developer = "Dev2",
//                freetogameProfileUrl = "url2", gameUrl = "url2", genre = "Moba", platform = "Xbox",
//                publisher = "Capcom", releaseDate = "February", shortDescription = "moba desc2")
//        )
//        `when`(popularGamesUseCase.invoke("pc", "moba")).thenReturn(expectedPopularMobaGames)
//
//        // When
//        homeViewModel.popularMobaGames()
//
//        // Then
//        testDispatcher.scheduler.advanceTimeBy(1_000)
//        testDispatcher.scheduler.runCurrent()
//        verify(showPopularMobaGamesObserver).onChanged(expectedPopularMobaGames)
//        verify(errorObserver, never()).onChanged(any())
//    }
//
//    @Test
//    fun `test popularMobaGames failure`() = testScope.runBlockingTest {
//        // Given
//        val errorMessage = "Failed to load popular moba games"
//        `when`(popularGamesUseCase.invoke("pc", "moba")).thenThrow(RuntimeException(errorMessage))
//
//        // When
//        homeViewModel.popularMobaGames()
//
//        // Then
//        testDispatcher.scheduler.advanceTimeBy(1_000)
//        testDispatcher.scheduler.runCurrent()
//        verify(showPopularMobaGamesObserver, never()).onChanged(anyList())
//        verify(errorObserver).onChanged(eq(errorMessage))
//    }
//
//    @Test
//    fun `test popularRacingGames success`() = testScope.runBlockingTest {
//        // Given
//        val expectedPopularRacingGames = listOf(
//            AllGames(id = 1, title = "Racing Game 1", thumbnail = "img1", developer = "Dev1",
//                freetogameProfileUrl = "url1", gameUrl = "url1", genre = "Racing", platform = "PC",
//                publisher = "Valve", releaseDate = "January", shortDescription = "racing desc1"),
//            AllGames(id = 2, title = "Racing Game 2", thumbnail = "img2", developer = "Dev2",
//                freetogameProfileUrl = "url2", gameUrl = "url2", genre = "Racing", platform = "Xbox",
//                publisher = "Capcom", releaseDate = "February", shortDescription = "racing desc2")
//        )
//        `when`(popularGamesUseCase.invoke("pc", "racing")).thenReturn(expectedPopularRacingGames)
//
//        // When
//        homeViewModel.popularRacingGames()
//
//        // Then
//        testDispatcher.scheduler.advanceTimeBy(1_000)
//        testDispatcher.scheduler.runCurrent()
//        verify(showPopularRacingGamesObserver).onChanged(expectedPopularRacingGames)
//        verify(errorObserver, never()).onChanged(any())
//    }
//
//    @Test
//    fun `test popularRacingGames failure`() = testScope.runBlockingTest {
//        // Given
//        val errorMessage = "Failed to load popular racing games"
//        `when`(popularGamesUseCase.invoke("pc", "racing")).thenThrow(RuntimeException(errorMessage))
//
//        // When
//        homeViewModel.popularRacingGames()
//
//        // Then
//        testDispatcher.scheduler.advanceTimeBy(1_000)
//        testDispatcher.scheduler.runCurrent()
//        verify(showPopularRacingGamesObserver, never()).onChanged(anyList())
//        verify(errorObserver).onChanged(eq(errorMessage))
//    }
//}
