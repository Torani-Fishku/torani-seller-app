package com.byn.analytics.domain

import id.fishku.fishersellercore.core.Resource
import id.fishku.fishersellercore.requests.FishPriceRequest
import id.fishku.fishersellercore.response.FishPriceResponse
import kotlinx.coroutines.flow.Flow

interface FishPriceRepository {
    fun getFishPrice(request: FishPriceRequest): Flow<Resource<FishPriceResponse>>
}