package com.byn.analytics.data.repository

import com.byn.analytics.data.datasource.WeatherRemoteDataSource
import com.byn.analytics.domain.WeatherRepository
import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.requests.WeatherAndTideRequest
import id.fishku.fishersellercore.response.WeatherAndTideResponse

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val data: WeatherRemoteDataSource
) : WeatherRepository {
    override fun getWeatherAndTideInfo(request: WeatherAndTideRequest): Flow<Resource<WeatherAndTideResponse>> =
        data.getWeatherAndASeaTidesInfo(request)
}