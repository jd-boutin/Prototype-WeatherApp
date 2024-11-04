package com.example.weatherappprototype.model

import android.content.Context
import android.util.Log
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

    fun getSavedLocations(context: Context): List<LocationWrapper> {
        val sharedPreferences = context.getSharedPreferences("com.example.weatherappprototype.pinned_locations", Context.MODE_PRIVATE)
        val pinnedLocations: List<LocationWrapper> = Json.decodeFromString<List<LocationWrapper>>(sharedPreferences.getString("pinned_locations", "[]") ?: "[]")
        return pinnedLocations
    }

    fun getMainInterestingLocations() : List<LocationWrapper> {
        return listOf(LocationWrapper(3027422,"Chambéry", "France", 45.56628F, 5.92079F), LocationWrapper(2996944,"Lyon", "France", 45.74846F, 4.84671F), LocationWrapper(2988507, "Paris", "France", 48.85341F, 2.3488F), LocationWrapper(2972315, "Toulouse", "France", 43.60426F, 1.44367F))
    }

    fun findLocation(location: LocationWrapper, context: Context) : Int {
        val sharedPreferences = context.getSharedPreferences("com.example.weatherappprototype.pinned_locations", Context.MODE_PRIVATE)
        val pinnedLocations: MutableList<LocationWrapper> = Json.decodeFromString<MutableList<LocationWrapper>>(sharedPreferences.getString("pinned_locations", "[]") ?: "[]")
        var i =0;
        for (pinnedLocation in pinnedLocations) {
            if (location.id == pinnedLocation.id){
                return i
            }
            i++;
        }
        return -1
    }

    fun pinLocation(location: LocationWrapper, context: Context) {
        val sharedPreferences = context.getSharedPreferences("com.example.weatherappprototype.pinned_locations", Context.MODE_PRIVATE)
        val pinnedLocations: MutableList<LocationWrapper> = Json.decodeFromString<MutableList<LocationWrapper>>(sharedPreferences.getString("pinned_locations", "[]") ?: "[]")
        pinnedLocations.add(0, location)
        with (sharedPreferences.edit()) {
            putString("pinned_locations", Json.encodeToString(pinnedLocations))
            apply()
        }
    }

    fun unpinLocation(location: LocationWrapper, context: Context) {
        val sharedPreferences = context.getSharedPreferences("com.example.weatherappprototype.pinned_locations", Context.MODE_PRIVATE)
        val pinnedLocations: MutableList<LocationWrapper> = Json.decodeFromString<MutableList<LocationWrapper>>(sharedPreferences.getString("pinned_locations", "[]") ?: "[]")
        pinnedLocations.removeAt(findLocation(location, context))
        with (sharedPreferences.edit()) {
            putString("pinned_locations", Json.encodeToString(pinnedLocations))
            apply()
        }
    }
}