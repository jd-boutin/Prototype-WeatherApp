package com.example.weatherappprototype.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeteoAPIResult(
    @SerialName(value = "latitude")
    val latitude: Double,

    @SerialName(value = "longitude")
    val longitude: Double,
    @SerialName(value = "generationtime_ms")
    val generationtimeMs: Double,
    @SerialName(value = "utc_offset_seconds")
    val utcOffsetSeconds: Int,
    @SerialName(value = "timezone")
    val timezone: String,
    @SerialName(value = "elevation")
    val elevation: Double,
    @SerialName(value = "hourly_units")
    val hourlyUnits: Map<String, String>,

    @SerialName(value = "hourly")
    val hourly: HourlyWrapper


)