package com.example.weatherappprototype.model

class LocationRepository {
    fun fetchLocations(): List<Location> {
        return listOf(Location("Paris"), Location("Lyon"), Location("Toulouse"))
    }
}