package com.byn.analytics.domain

import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.requests.WeatherAndTideRequest
import id.fishku.fishersellercore.response.WeatherAndTideResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherAndTideInfo(request: WeatherAndTideRequest): Flow<Resource<WeatherAndTideResponse>>
}