package com.example.weatherappprototype.model

import android.content.Context
import com.example.weatherappprototype.network.OpenMeteoApi

class MeteoRepository {
    val locationRepository: LocationRepository = LocationRepository()
    suspend fun fetchMeteoData(location: LocationWrapper, context: Context): Meteo {
        try {
            val apiResult = OpenMeteoApi.retrofitService.getMeteo(location.latitude, location.longitude, "temperature_2m,weather_code")
            val currTemperature = apiResult.hourly.temperature2m[0]
            val currWW = apiResult.hourly.weatherCode[0]
            return Meteo(location, currTemperature, currWW, locationRepository.findLocation(location, context)!=-1)
        }
        catch (e: Exception) {
            return Meteo(LocationWrapper(-1, e.toString(), "Erreur", 0.0F, 0.0F), 18.7, 1, false)
        }

    }
}