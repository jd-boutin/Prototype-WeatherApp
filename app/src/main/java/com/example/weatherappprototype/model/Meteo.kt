package com.example.weatherappprototype.model

import kotlinx.serialization.Serializable

@Serializable
data class Meteo(
    val location: Location,
    val temperature: Double,
    val ww_code: Int
    )
