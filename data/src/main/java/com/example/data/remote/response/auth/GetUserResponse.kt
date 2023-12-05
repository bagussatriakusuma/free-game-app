package com.example.data.remote.response.auth

import com.example.data.remote.response.main.GetDetailGameResponse
import com.example.domain.model.auth.UserData
import com.google.gson.annotations.SerializedName

data class GetUserResponse (

    @SerializedName("status" ) var status : String? = null,
    @SerializedName("data"   ) var data   : Data?   = Data()

) {
    data class Data (

        @SerializedName("id"           ) var id          : Int?    = null,
        @SerializedName("name"         ) var name        : String? = null,
        @SerializedName("email"        ) var email       : String? = null,
        @SerializedName("password"     ) var password    : String? = null,
        @SerializedName("picture"      ) var picture     : String? = null,
        @SerializedName("phone_number" ) var phoneNumber : String? = null,
        @SerializedName("address"      ) var address     : String? = null,
        @SerializedName("city"         ) var city        : String? = null,
        @SerializedName("createdAt"    ) var createdAt   : String? = null,
        @SerializedName("updatedAt"    ) var updatedAt   : String? = null

    )
}

fun GetUserResponse.Data.toUserData(): UserData {
    return UserData(
        id = id ?: 0,
        name = name.orEmpty(),
        email = email.orEmpty(),
        password = password.orEmpty(),
        picture = picture.orEmpty(),
        phoneNumber = phoneNumber.orEmpty(),
        address = address.orEmpty(),
        city = city.orEmpty(),
        createdAt = createdAt.orEmpty(),
        updatedAt = updatedAt.orEmpty(),
    )
}