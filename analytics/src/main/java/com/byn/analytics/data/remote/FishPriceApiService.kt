package com.byn.analytics.data.remote

import id.fishku.fishersellercore.response.FishPriceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FishPriceApiService {
    @GET("predict/{fishType}/{date}")
    suspend fun getFishPrice(
        @Path("fishType") fishType: String,
        @Path("date") date: String
    ): Response<FishPriceResponse>
}