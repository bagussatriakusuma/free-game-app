package com.example.challengechapter5.data.remote.response.auth

import com.google.gson.annotations.SerializedName

data class GetUserResponse (

    @SerializedName("id"                ) var id                : Int?    = null,
    @SerializedName("name"              ) var name              : String? = null,
    @SerializedName("email"             ) var email             : String? = null,
    @SerializedName("encryptedPassword" ) var encryptedPassword : String? = null,
    @SerializedName("photo"             ) var photo             : String? = null,
    @SerializedName("phoneNumber"       ) var phoneNumber       : String? = null,
    @SerializedName("address"           ) var address           : String? = null,
    @SerializedName("cityId"            ) var cityId            : String? = null,
    @SerializedName("createdAt"         ) var createdAt         : String? = null,
    @SerializedName("updatedAt"         ) var updatedAt         : String? = null

)