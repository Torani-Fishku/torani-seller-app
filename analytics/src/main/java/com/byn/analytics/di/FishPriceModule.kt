package com.byn.analytics.di

import com.byn.analytics.BuildConfig.BASE_URL_PRICE_PREDICTION
import com.byn.analytics.BuildConfig.DEBUG
import com.byn.analytics.data.datasource.FishPriceRemoteDataSource
import com.byn.analytics.data.remote.FishPriceApiService
import com.byn.analytics.data.repository.FishPriceRepositoryImpl
import com.byn.analytics.domain.FishPriceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.fishku.fishersellercore.core.ErrorParser
import id.fishku.fishersellercore.core.SafeCall
import id.fishku.fishersellercore.di.RetrofitQualifier.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FishPriceModule {

    @Provides
    @Singleton
    @ParserPrice
    fun provideErrorParser(@RetrofitPrice retrofit: Retrofit) =
        ErrorParser(retrofit)

    @Provides
    @Singleton
    @HttpPrice
    fun provideOkHttpClient(
    ): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(
            if (DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        )

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @RetrofitPrice
    fun provideRetrofit( @HttpPrice okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL_PRICE_PREDICTION)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePriceApiService(@RetrofitPrice retrofit: Retrofit): FishPriceApiService =
        retrofit.create(FishPriceApiService::class.java)

    @Provides
    @Singleton
    fun provideDataSource(
        safeCall: SafeCall,
        @ParserPrice converter: ErrorParser,
        service: FishPriceApiService
    ) = FishPriceRemoteDataSource(safeCall, converter, service)

    @Provides
    @Singleton
    fun provideRepository(data: FishPriceRemoteDataSource): FishPriceRepository =
        FishPriceRepositoryImpl(data)

}