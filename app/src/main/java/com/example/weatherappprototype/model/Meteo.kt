package com.example.weatherappprototype.model

import kotlinx.serialization.Serializable

@Serializable
data class Meteo(
    val location: String,
    val temperature: Double,
    val ww_code: Int
    )
