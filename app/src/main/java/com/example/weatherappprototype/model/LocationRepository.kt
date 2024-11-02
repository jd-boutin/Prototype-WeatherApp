package com.example.weatherappprototype.model

class LocationRepository {
    fun fetchLocations(): List<Location> {
        return listOf(Location("Paris", 48.85341F, 2.3488F), Location("Lyon", 45.74846F, 4.84671F), Location("Toulouse", 43.60426F, 1.44367F))
    }
}