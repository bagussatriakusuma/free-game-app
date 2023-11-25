package com.example.data.remote.request.auth

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest (

    @SerializedName("name"              ) var name              : String? = null,
    @SerializedName("picture"           ) var picture           : String? = null,
    @SerializedName("phone_number"      ) var phoneNumber       : String? = null,
    @SerializedName("city"              ) var city              : String? = null,
    @SerializedName("address"           ) var address           : String? = null

)
