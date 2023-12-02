package com.buiducha.speedyfood.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

// Get current location listener
@SuppressLint("MissingPermission")
fun getCurrentLocation(
    context: Context,
    onGetLocationSuccess: (Location) -> Unit
) {
    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    fusedLocationProviderClient.lastLocation.addOnSuccessListener { currentLocation ->
        if (currentLocation != null) {
            onGetLocationSuccess(currentLocation)
        } else {
            Log.d(TAG, "location is null")
        }
    }
}

const val TAG = "LocationTracker"