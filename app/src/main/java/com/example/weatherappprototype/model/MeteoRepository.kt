package com.example.weatherappprototype.model

import com.example.weatherappprototype.network.OpenMeteoApi

class MeteoRepository {
    suspend fun fetchMeteoData(location: Location): Meteo {
        try {
            val apiResult = OpenMeteoApi.retrofitService.getMeteo(location.latitude, location.longitude, "temperature_2m,weather_code")
            val currTemperature = apiResult.hourly.temperature2m[0]
            val currWW = apiResult.hourly.weatherCode[0]
            return Meteo(location, currTemperature, currWW)
        }
        catch (e: Exception) {
            return Meteo(Location(e.toString(), "Erreur", 0.0F, 0.0F), 18.7, 1)
        }

    }
}