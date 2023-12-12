package com.example.challengechapter7.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.challengechapter7.presentation.auth.login.LoginViewModel
import com.example.domain.model.auth.UserLoginRequest
import com.example.presentation.usecase.auth.login.UserLoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mockito.*
import kotlinx.coroutines.CoroutineName


@ExperimentalCoroutinesApi
class LoginViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineContext = testDispatcher + CoroutineName("TestCoroutine")
    private val testScope = TestCoroutineScope(testDispatcher)
    private val userLoginUseCase: UserLoginUseCase = mock(UserLoginUseCase::class.java)
    private val openHomePageObserver: Observer<Boolean> = mock()
    private val errorObserver: Observer<String?> = mock()
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        loginViewModel = LoginViewModel(userLoginUseCase, testCoroutineContext)
        loginViewModel.openHomePage.observeForever(openHomePageObserver)
        loginViewModel.error.observeForever(errorObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `test userLogin success`() = testScope.runBlockingTest {
        // Given
        val request = UserLoginRequest("test@example.com", "password")
        `when`(userLoginUseCase.invoke(request)).thenReturn(true)

        // When
        loginViewModel.userLogin(request)

        // Then
        testScheduler.apply { advanceTimeBy(1_000); runCurrent() }
        verify(openHomePageObserver).onChanged(anyBoolean())
        verify(errorObserver, never()).onChanged(any())
    }

    @Test
    fun `test userLogin failure`() = testScope.runBlockingTest {
        // Given
        val request = UserLoginRequest("test@example.com", "password")
        val errorMessage = "Login failed"
        `when`(userLoginUseCase.invoke(request)).thenThrow(RuntimeException(errorMessage))

        // When
        loginViewModel.userLogin(request)

        // Then
        testScheduler.apply { advanceTimeBy(1_000); runCurrent() }
        verify(openHomePageObserver, never()).onChanged(anyBoolean())
        verify(errorObserver).onChanged(eq(errorMessage))
    }
}


