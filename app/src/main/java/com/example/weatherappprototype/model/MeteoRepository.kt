package com.example.weatherappprototype.model

class MeteoRepository {
    fun fetchMeteoData(location: Location): Meteo {
        return Meteo(location.location, 16.7F, 1)
    }
}