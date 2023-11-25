package com.example.data.remote.response.auth

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
