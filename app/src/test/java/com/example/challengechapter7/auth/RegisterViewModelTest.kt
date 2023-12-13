package com.example.challengechapter7.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.challengechapter7.presentation.auth.register.RegisterViewModel
import com.example.domain.model.auth.UserRegisterRequest
import com.example.presentation.usecase.auth.register.UserRegisterUseCase
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
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

//@ExperimentalCoroutinesApi
//class RegisterViewModelTest {
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//    private val testDispatcher = TestCoroutineDispatcher()
//    private val testCoroutineContext = testDispatcher + CoroutineName("TestCoroutine")
//    private val testScope = TestCoroutineScope(testDispatcher)
//    private val userRegisterUseCase: UserRegisterUseCase = mock(UserRegisterUseCase::class.java)
//    private val openLoginPageObserver: Observer<Boolean> = mock()
//    private val errorObserver: Observer<String?> = mock()
//    private lateinit var registerViewModel: RegisterViewModel
//
//    @Before
//    fun setup() {
//        Dispatchers.setMain(testDispatcher)
//        registerViewModel = RegisterViewModel(userRegisterUseCase, testCoroutineContext)
//        registerViewModel.openLoginPage.observeForever(openLoginPageObserver)
//        registerViewModel.error.observeForever(errorObserver)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//        testScope.cleanupTestCoroutines()
//    }
//
//    @Test
//    fun `test userRegister success`() = testScope.runBlockingTest {
//        // Given
//        val request = UserRegisterRequest("testing", "test@example.com", "password")
//        `when`(userRegisterUseCase.invoke(request)).thenReturn(true)
//
//        // When
//        registerViewModel.userRegister(request)
//
//        // Then
//        testDispatcher.scheduler.apply { advanceTimeBy(1_000); runCurrent() }
//        testDispatcher.scheduler.runCurrent()
//        verify(openLoginPageObserver).onChanged(anyBoolean())
//        verify(errorObserver, never()).onChanged(any())
//    }
//
//    @Test
//    fun `test userRegister failure`() = testScope.runBlockingTest {
//        // Given
//        val request = UserRegisterRequest("testing", "test@example.com", "password")
//        val errorMessage = "Registration failed"
//        `when`(userRegisterUseCase.invoke(request)).thenThrow(RuntimeException(errorMessage))
//
//        // When
//        registerViewModel.userRegister(request)
//
//        // Then
//        testDispatcher.scheduler.apply { advanceTimeBy(1_000); runCurrent() }
//        testDispatcher.scheduler.runCurrent()
//        verify(openLoginPageObserver, never()).onChanged(anyBoolean())
//        verify(errorObserver).onChanged(eq(errorMessage))
//    }
//}
