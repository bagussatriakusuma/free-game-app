package com.example.challengechapter5.data.remote.response.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse (

    @SerializedName("id"           ) var id          : Int?    = null,
    @SerializedName("full_name"    ) var fullName    : String? = null,
    @SerializedName("email"        ) var email       : String? = null,
    @SerializedName("access_token" ) var accessToken : String? = null

)
