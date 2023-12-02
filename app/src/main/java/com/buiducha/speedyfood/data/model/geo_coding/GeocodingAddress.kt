package com.buiducha.speedyfood.data.model.geo_coding

import com.google.gson.annotations.SerializedName

data class GeocodingAddress(
    @SerializedName("formatted_address")
    val formattedAddress: String
)
