package com.byn.analytics.data.repository

import com.byn.analytics.data.datasource.FishPriceRemoteDataSource
import com.byn.analytics.domain.FishPriceRepository
import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.requests.FishPriceRequest
import id.fishku.fishersellercore.response.FishPriceResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FishPriceRepositoryImpl @Inject constructor(
    private val data: FishPriceRemoteDataSource
) : FishPriceRepository {
    override fun getFishPrice(request: FishPriceRequest): Flow<Resource<FishPriceResponse>> =
        data.getFishPrice(request)
}