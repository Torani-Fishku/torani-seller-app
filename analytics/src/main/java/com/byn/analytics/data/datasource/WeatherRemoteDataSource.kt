package com.byn.analytics.data.datasource

import com.byn.analytics.data.remote.WeatherApiService
import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.core.ErrorParser
import id.fishku.fishersellercore.core.SafeCall
import id.fishku.fishersellercore.requests.WeatherAndTideRequest
import id.fishku.fishersellercore.response.WeatherAndTideResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Data source
 *
 * @property safeCall
 * @property converter
 * @constructor Create empty Data source
 */
class WeatherRemoteDataSource @Inject constructor(
    private val safeCall: SafeCall,
    private val converter: ErrorParser,
    private val service: WeatherApiService,
) {

    fun getWeatherAndASeaTidesInfo(request: WeatherAndTideRequest): Flow<Resource<WeatherAndTideResponse>> =
        flow {
            emit(Resource.loading(null))
            val res = safeCall.enqueueWeather(
                request.longitute ?: "106.93995574192542",
                request.latitude ?: "-6.097743497444588",
                service::getWeatherAndASeaTidesInfo
            )
            emit(res)
        }.flowOn(Dispatchers.IO)

}