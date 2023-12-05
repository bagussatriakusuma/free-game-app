package com.example.data.repository.remote

import com.example.data.local.datastore.DatastoreManager
import com.example.data.remote.response.auth.toUpdateUser
import com.example.data.remote.response.auth.toUserData
import com.example.data.remote.response.auth.toUserLogin
import com.example.data.remote.response.auth.toUserRegister
import com.example.data.remote.response.main.toAllGames
import com.example.data.remote.response.main.toDetailGames
import com.example.data.remote.service.AuthAPI
import com.example.data.remote.service.MainAPI
import com.example.domain.model.auth.UpdateUser
import com.example.domain.model.auth.UserData
import com.example.domain.model.auth.UserLogin
import com.example.domain.model.auth.UserLoginRequest
import com.example.domain.model.auth.UserRegister
import com.example.domain.model.auth.UserRegisterRequest
import com.example.domain.model.main.AllGames
import com.example.domain.model.main.DetailGames
import com.example.domain.repository.AccountRepository
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.MainRepository
import com.example.domain.repository.TokenRepository
import kotlinx.coroutines.flow.firstOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val datastore: DatastoreManager,
    private val apiAuth: AuthAPI,
    private val apiMain: MainAPI
): TokenRepository, AuthRepository, AccountRepository, MainRepository {
    override suspend fun setToken(token: String) {
        datastore.setToken(token)
    }

    override suspend fun getToken(): String? {
        return datastore.getToken().firstOrNull()
    }

    override suspend fun clearToken() {
        setToken("")
    }

    override suspend fun userLogin(request: UserLoginRequest): UserLogin {
        return apiAuth.login(request).toUserLogin()
    }

    override suspend fun userRegister(request: UserRegisterRequest): UserRegister {
        return apiAuth.register(request).toUserRegister()
    }

    override suspend fun getDataUser(token: String): UserData {
        return apiAuth.getUser(token = "Bearer $token").data!!.toUserData()
    }

    override suspend fun updateDataUser(
        token: String,
        picture: MultipartBody.Part?,
        name: RequestBody?,
        phoneNumber: RequestBody?,
        city: RequestBody?,
        address: RequestBody?
    ): UpdateUser {
        return apiAuth.updateUser(
            token = "Bearer $token",
            picture,
            name,
            phoneNumber,
            city,
            address
        ).toUpdateUser()
    }

    override suspend fun getAllGames(platform: String): List<AllGames> {
        return apiMain.allGames(platform).map { response -> response.toAllGames() }
    }

    override suspend fun getGamesBySorted(
        platform: String,
        category: String
    ): List<AllGames> {
        return apiMain.gamesBySorted(platform, category).map { response -> response.toAllGames() }
    }

    override suspend fun getDetailGames(id: Int): DetailGames {
        return apiMain.detailGame(id).toDetailGames()
    }
}