package com.example.weatherappprototype.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyWrapper(
    @SerialName(value = "time")
    val time: List<String>,
    @SerialName(value = "temperature_2m")
    val temperature2m: List<Double>,
    @SerialName(value = "weather_code")
    val weatherCode: List<Int>
)