package com.buiducha.speedyfood.data.remote

import com.buiducha.speedyfood.data.model.geo_coding.GeocodingResult
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingApi {
    @GET("geocode/json?key=AIzaSyB5DKqQAkt4lMRAv5XWr4ijiOl_39GFIEE")
    suspend fun getGeocoding(
        @Query("latlng") latLng: String
    ): GeocodingResult
}