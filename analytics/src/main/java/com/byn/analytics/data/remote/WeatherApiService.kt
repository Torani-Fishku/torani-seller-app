package com.byn.analytics.data.remote

import id.fishku.fishersellercore.response.WeatherAndTideResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("location")
    suspend fun getWeatherAndASeaTidesInfo(
        @Query("longitude") longitude: String,
        @Query("latitude") latitude: String
    ): Response<WeatherAndTideResponse>
}