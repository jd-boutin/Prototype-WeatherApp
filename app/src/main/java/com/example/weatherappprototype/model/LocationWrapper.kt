package com.example.weatherappprototype.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LocationWrapper (
    @SerialName(value = "id")
    val id: Int,
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "country")
    val country: String,
    @SerialName(value = "latitude")
    val latitude: Float,
    @SerialName(value = "longitude")
    val longitude: Float,
)