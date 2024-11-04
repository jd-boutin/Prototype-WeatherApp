package com.example.weatherappprototype.model

import kotlinx.serialization.Serializable

@Serializable
data class Location (
    val name: String,
    val country: String,
    val latitude: Float,
    val longitude: Float
)