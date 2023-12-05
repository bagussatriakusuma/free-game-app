package com.example.data.remote.response.auth

import com.example.domain.model.auth.UserLogin
import com.google.gson.annotations.SerializedName

data class LoginResponse (

    @SerializedName("status" ) var status : String? = null,
    @SerializedName("data"   ) var data   : Data?   = Data()

) {
    data class Data (

        @SerializedName("id"    ) var id    : Int?    = null,
        @SerializedName("name"  ) var name  : String? = null,
        @SerializedName("email" ) var email : String? = null,
        @SerializedName("token" ) var token : String? = null

    )
}

fun LoginResponse.toUserLogin(): UserLogin {
    return UserLogin(
        id = data?.id ?: 0,
        name = data?.name.orEmpty(),
        email = data?.email.orEmpty(),
        token = data?.token.orEmpty(),
        status = status.orEmpty()
    )
}