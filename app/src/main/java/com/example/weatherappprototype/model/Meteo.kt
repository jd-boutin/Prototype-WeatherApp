package com.example.weatherappprototype.model

import kotlinx.serialization.Serializable

@Serializable
data class Meteo(
    val location: LocationWrapper,
    val temperature: Double,
    val ww_code: Int,
    val pinned: Boolean
    )
