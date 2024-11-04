package com.example.weatherappprototype.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationAPIResult (
    @SerialName(value = "results")
    val results: List<LocationWrapper>,
    @SerialName(value = "generationtime_ms")
    val generationtimeMs: Double
)