package com.example.weatherappprototype.model

import com.example.weatherappprototype.network.GeocodingApi

class LocationRepository {
    suspend fun fetchLocationSuggestions(name: String): List<LocationWrapper> {
        try {
            val apiResult = GeocodingApi.retrofitService.getLocation(name, count = 3)
            return apiResult.results
        }
        catch (e: Exception) {
            return emptyList<LocationWrapper>()
        }
    }

    fun locationWrapperToLocation(locationWrapper: LocationWrapper): Location {
        return Location(locationWrapper.name,locationWrapper.latitude,locationWrapper.longitude)
    }

    fun getSavedLocations(): List<Location> {
        return listOf(Location("Paris", 48.85341F, 2.3488F), Location("Lyon", 45.74846F, 4.84671F), Location("Toulouse", 43.60426F, 1.44367F))
    }
}