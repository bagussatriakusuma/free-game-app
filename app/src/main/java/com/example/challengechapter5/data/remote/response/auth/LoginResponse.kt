package com.example.challengechapter5.data.remote.response.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse (

    @SerializedName("id"        ) var id        : Int?    = null,
    @SerializedName("name"      ) var name      : String? = null,
    @SerializedName("email"     ) var email     : String? = null,
    @SerializedName("token"     ) var token     : String? = null,
    @SerializedName("createdAt" ) var createdAt : String? = null,
    @SerializedName("updatedAt" ) var updatedAt : String? = null

)
