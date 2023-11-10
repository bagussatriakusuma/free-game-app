package com.example.challengechapter5.domain

import com.example.challengechapter5.data.remote.request.auth.LoginRequest
import com.example.challengechapter5.data.remote.request.auth.RegisterRequest
import com.example.challengechapter5.data.remote.response.auth.GetUserResponse
import com.example.challengechapter5.data.remote.response.auth.LoginResponse
import com.example.challengechapter5.data.remote.response.auth.RegisterResponse
import com.example.challengechapter5.data.remote.response.auth.UpdateUserResponse
import com.example.challengechapter5.data.remote.response.main.GetAllGamesResponse
import com.example.challengechapter5.data.remote.response.main.GetDetailGameResponse
import com.example.challengechapter5.data.remote.service.AuthAPI
import com.example.challengechapter5.data.remote.service.MainAPI
import com.example.challengechapter5.datastore.DatastoreManager
import com.example.challengechapter5.domain.repository.AuthRepository
import com.example.challengechapter5.domain.repository.MainRepository
import com.example.challengechapter5.domain.repository.TokenRepository
import kotlinx.coroutines.flow.firstOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val datastore: DatastoreManager,
    private val apiAuth: AuthAPI,
    private val apiMain: MainAPI
): TokenRepository, AuthRepository, MainRepository {
    override suspend fun setToken(token: String) {
        datastore.setToken(token)
    }

    override suspend fun getToken(): String? {
        return datastore.getToken().firstOrNull()
    }

    override suspend fun clearToken() {
        setToken("")
    }

    override suspend fun userLogin(request: LoginRequest): LoginResponse {
        return apiAuth.login(request)
    }

    override suspend fun userRegister(request: RegisterRequest): RegisterResponse {
        return apiAuth.register(request)
    }

    override suspend fun getDataUser(token: String): GetUserResponse {
        return apiAuth.getUser(token = "Bearer $token")
    }

    override suspend fun updateDataUser(
        token: String,
        picture: MultipartBody.Part?,
        name: RequestBody?,
        phoneNumber: RequestBody?,
        city: RequestBody?,
        address: RequestBody?
    ): UpdateUserResponse {
        return apiAuth.updateUser(
            token = "Bearer $token",
            picture,
            name,
            phoneNumber,
            city,
            address
        )
    }

    override suspend fun getAllGames(platform: String): List<GetAllGamesResponse> {
        return apiMain.allGames(platform)
    }

    override suspend fun getGamesBySorted(
        platform: String,
        category: String
    ): List<GetAllGamesResponse> {
        return apiMain.gamesBySorted(platform, category)
    }

    override suspend fun getDetailGames(id: Int): GetDetailGameResponse {
        return apiMain.detailGame(id)
    }
}