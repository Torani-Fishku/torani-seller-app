package com.byn.analytics.data.datasource

import com.byn.analytics.data.remote.FishPriceApiService
import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.core.ErrorParser
import id.fishku.fishersellercore.core.SafeCall
import id.fishku.fishersellercore.requests.FishPriceRequest
import id.fishku.fishersellercore.response.FishPriceResponse
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
class FishPriceRemoteDataSource @Inject constructor(
    private val safeCall: SafeCall,
    private val converter: ErrorParser,
    private val service: FishPriceApiService,
) {

    fun getFishPrice(request: FishPriceRequest): Flow<Resource<FishPriceResponse>> =
        flow {
            emit(Resource.loading(null))
            val res = safeCall.enqueueFishPrice(
                request.fishType ?: "bandeng",
                request.date ?: "2023-02-08",
                service::getFishPrice
            )
            emit(res)
        }.flowOn(Dispatchers.IO)

}