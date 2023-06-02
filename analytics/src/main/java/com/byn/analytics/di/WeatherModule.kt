package com.byn.analytics.di

import com.byn.analytics.BuildConfig.BASE_URL_WEATHER_SEA_TIDES
import com.byn.analytics.BuildConfig.DEBUG
import com.byn.analytics.data.datasource.WeatherRemoteDataSource
import com.byn.analytics.data.remote.WeatherApiService
import com.byn.analytics.data.repository.WeatherRepositoryImpl
import com.byn.analytics.domain.WeatherRepository
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
object WeatherModule {

    @Provides
    @Singleton
    @ParserWeather
    fun provideErrorParser(@RetrofitWeather retrofit: Retrofit) =
        ErrorParser(retrofit)

    @Provides
    @Singleton
    @HttpWeather
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
    @RetrofitWeather
    fun provideRetrofit( @HttpWeather okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL_WEATHER_SEA_TIDES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideWeatherApiService(@RetrofitWeather retrofit: Retrofit): WeatherApiService =
        retrofit.create(WeatherApiService::class.java)

    @Provides
    @Singleton
    fun provideDataSource(
        safeCall: SafeCall,
        @ParserWeather converter: ErrorParser,
        service: WeatherApiService
    ) = WeatherRemoteDataSource(safeCall, converter, service)

    @Provides
    @Singleton
    fun provideRepository(data: WeatherRemoteDataSource): WeatherRepository =
        WeatherRepositoryImpl(data)

}