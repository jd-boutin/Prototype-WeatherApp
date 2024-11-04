package com.example.weatherappprototype.network
import com.example.weatherappprototype.model.MeteoAPIResult
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

private const val BASE_URL = "https://api.open-meteo.com/v1/"

private val json = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()




interface OpenMeteoApiService {
    @GET("forecast")
    suspend fun getMeteo(
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float,
        @Query("hourly") hourly: String
    ): MeteoAPIResult
}

object OpenMeteoApi {
    val retrofitService : OpenMeteoApiService by lazy {
        retrofit.create(OpenMeteoApiService::class.java)
    }
}


