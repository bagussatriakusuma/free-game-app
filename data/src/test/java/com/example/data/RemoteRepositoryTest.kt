package com.example.data

import com.example.data.remote.response.auth.toUserLogin
import com.example.data.remote.service.AuthAPI
import com.example.data.remote.service.MainAPI
import com.example.data.repository.remote.RemoteRepository
import com.example.domain.model.auth.*
import com.example.domain.model.main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import java.io.IOException

@ExperimentalCoroutinesApi
class RemoteRepositoryTest {

    private lateinit var remoteRepository: RemoteRepository
    private val testDispatcher = TestCoroutineDispatcher()
    private val apiAuth: AuthAPI = mock(AuthAPI::class.java)
    private val apiMain: MainAPI = mock(MainAPI::class.java)

    @Before
    fun setUp() {
        remoteRepository = RemoteRepository(mock(), apiAuth, apiMain)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `setToken should call datastore setToken`() = testDispatcher.runBlockingTest {
        // Given
        val token = "testToken"

        // When
        remoteRepository.setToken(token)

        // Then
        verify(remoteRepository.datastore).setToken(token)
    }

    @Test
    fun `getToken should return token from datastore`() = testDispatcher.runBlockingTest {
        // Given
        val expectedToken = "testToken"
        `when`(remoteRepository.datastore.getToken()).thenReturn(flowOf(expectedToken))

        // When
        val result = remoteRepository.getToken()

        // Then
        assert(result == expectedToken)
    }

    @Test
    fun `clearToken should call setToken with an empty string`() = testDispatcher.runBlockingTest {
        // When
        remoteRepository.clearToken()

        // Then
        verify(remoteRepository.datastore).setToken("")
    }

    @Test
    fun `userLogin success should return UserLogin`() = testDispatcher.runBlockingTest {
        // Given
        val request = mock(UserLoginRequest::class.java)
        val expectedUserLogin = mock(UserLogin::class.java)
        `when`(apiAuth.login(request)).thenReturn(mock())
        `when`(apiAuth.login(request).toUserLogin()).thenReturn(expectedUserLogin)

        // When
        val result = remoteRepository.userLogin(request)

        // Then
        assert(result == expectedUserLogin)
    }

    @Test
    fun `userLogin failure should throw IOException`() = testDispatcher.runBlockingTest {
        // Given
        val request = mock(UserLoginRequest::class.java)
        `when`(apiAuth.login(request)).thenThrow(IOException("Network error"))

        // When
        try {
            remoteRepository.userLogin(request)
        } catch (e: IOException) {
            // Then
            assert(e.message == "Network error")
        }
    }

}
