package com.example.weatherappprototype.model

import android.content.Context
import com.example.weatherappprototype.network.GeocodingApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LocationRepository {
    suspend fun fetchLocationSuggestions(name: String): List<LocationWrapper> {
        try {
            val apiResult = GeocodingApi.retrofitService.getLocation(name, count = 3)
            return apiResult.results
        }
        catch (e: Exception) {
            return emptyList()
        }
    }

    fun locationWrapperToLocation(locationWrapper: LocationWrapper): Location {
        return Location(locationWrapper.name,locationWrapper.country,locationWrapper.latitude,locationWrapper.longitude)
    }

    fun getSavedLocations(context: Context): List<Location> {
        val sharedPreferences = context.getSharedPreferences("com.example.weatherappprototype.pinned_locations", Context.MODE_PRIVATE)
        val pinnedLocations: List<Location> = Json.decodeFromString<List<Location>>(sharedPreferences.getString("pinned_locations", "[]") ?: "[]")
        return pinnedLocations
    }

    fun getMainInterestingLocations() : List<Location> {
        return listOf(Location("Chambéry", "France", 45.56628F, 5.92079F), Location("Lyon", "France", 45.74846F, 4.84671F), Location("Paris", "France", 48.85341F, 2.3488F), Location("Toulouse", "France", 43.60426F, 1.44367F))
    }

    fun findLocation(location: Location, context: Context) : Int {
        return -1
    }

    fun pinLocation(location: Location, context: Context) {
        val sharedPreferences = context.getSharedPreferences("com.example.weatherappprototype.pinned_locations", Context.MODE_PRIVATE)
        val pinnedLocations: MutableList<Location> = Json.decodeFromString<MutableList<Location>>(sharedPreferences.getString("pinned_locations", "[]") ?: "[]")
        pinnedLocations.add(0, location)
        with (sharedPreferences.edit()) {
            putString("pinned_locations", Json.encodeToString(pinnedLocations))
            apply()
        }
    }

    fun unpinLocation(location: Location, context: Context) {
        val sharedPreferences = context.getSharedPreferences("com.example.weatherappprototype.pinned_locations", Context.MODE_PRIVATE)
        val pinnedLocations: MutableList<Location> = Json.decodeFromString<MutableList<Location>>(sharedPreferences.getString("pinned_locations", "[]") ?: "[]")
        pinnedLocations.removeAt(findLocation(location, context))
        with (sharedPreferences.edit()) {
            putString("pinned_locations", Json.encodeToString(pinnedLocations))
            apply()
        }
    }
}