package com.buiducha.speedyfood.data.model.geo_coding

data class GeocodingResult(
    val results: List<GeocodingAddress>,
    val status: String
)
