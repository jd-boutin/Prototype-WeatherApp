package com.example.weatherappprototype.network

import com.example.weatherappprototype.model.LocationAPIResult
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://geocoding-api.open-meteo.com/v1/"

private val json = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()




interface GeocodingApiService {
    @GET("search")
    suspend fun getLocation(
        @Query("name") name: String,
        @Query("count") count: Int=10,
        @Query("language") language: String = "fr",
        @Query("format") format: String = "json"
    ): LocationAPIResult
}

object GeocodingApi {
    val retrofitService : GeocodingApiService by lazy {
        retrofit.create(GeocodingApiService::class.java)
    }
}