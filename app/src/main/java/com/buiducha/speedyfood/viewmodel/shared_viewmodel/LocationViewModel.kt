package com.buiducha.speedyfood.viewmodel.shared_viewmodel

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buiducha.speedyfood.data.location.getCurrentLocation
import com.buiducha.speedyfood.data.repository.GeocodingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {
    private val _currentLocation = MutableStateFlow<Location?>(null)
    private val _geocoding = MutableStateFlow("")
    val currentLocation: StateFlow<Location?> = _currentLocation.asStateFlow()
    val geocoding: StateFlow<String> = _geocoding.asStateFlow()

    fun getLocation(
        context: Context
    ) {
        Log.d(TAG, "getLocation: ")
        getCurrentLocation(
            context = context,
            onGetLocationSuccess = {location ->
                _currentLocation.value = location
                val latLng = "${location.latitude},${location.longitude}"
                viewModelScope.launch {
                    val geoResult = GeocodingRepository().getGeocoding(latLng).results
                    if (geoResult.isNotEmpty()) {
                        _geocoding.value = geoResult[0].formattedAddress
                    }
                }
                Log.d(TAG, latLng)
                Log.d(TAG, geocoding.value)
                Log.d(TAG, location.toString())
            }
        )
    }

    companion object {
        const val TAG = "LocationViewModel"
    }
}