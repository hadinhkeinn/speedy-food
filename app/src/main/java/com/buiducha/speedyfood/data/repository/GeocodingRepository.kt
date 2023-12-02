package com.buiducha.speedyfood.data.repository

import com.buiducha.speedyfood.data.model.geo_coding.GeocodingResult
import com.buiducha.speedyfood.data.remote.RetrofitInstance

class GeocodingRepository {
    suspend fun getGeocoding(latLng: String): GeocodingResult =
        RetrofitInstance.api.getGeocoding(latLng)
}