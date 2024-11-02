package com.example.weatherappprototype.model

import com.example.weatherappprototype.network.OpenMeteoApi
import java.io.IOException

class MeteoRepository {
    suspend fun fetchMeteoData(location: Location): Meteo {
        try {
            val apiResult = OpenMeteoApi.retrofitService.getMeteo(location.latitude, location.longitude, "temperature_2m,weather_code")
            val currTemperature = apiResult.hourly.temperature2m[0]
            val currWW = apiResult.hourly.weatherCode[0]
            return Meteo(location.location, currTemperature, currWW)
        }
        catch (e: Exception) {
            return Meteo(e.toString()?:"Paris", 18.7, 1)
        }

    }
}