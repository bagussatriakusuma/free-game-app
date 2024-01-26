package com.example.challengechapter6.data.remote.request.auth

import com.google.gson.annotations.SerializedName

data class LoginRequest (

    @SerializedName("email"    ) var email    : String? = null,
    @SerializedName("password" ) var password : String? = null

)

