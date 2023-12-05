package com.example.data.remote.response.auth

import com.example.domain.model.auth.UpdateUser
import com.google.gson.annotations.SerializedName

data class UpdateUserResponse(

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

fun UpdateUserResponse.toUpdateUser(): UpdateUser {
    return UpdateUser(
        id = data?.id ?: 0,
        name = data?.name.orEmpty(),
        email = data?.email.orEmpty(),
        password = data?.password.orEmpty(),
        picture = data?.picture.orEmpty(),
        phoneNumber = data?.phoneNumber.orEmpty(),
        address = data?.address.orEmpty(),
        city = data?.city.orEmpty(),
        createdAt = data?.createdAt.orEmpty(),
        updatedAt = data?.updatedAt.orEmpty(),
    )
}
