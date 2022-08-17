package com.maktay.weatherforecast.di

import com.maktay.weatherforecast.common.Constants
import com.maktay.weatherforecast.data.remote.OpenWeatherApi
import com.maktay.weatherforecast.data.remote.repository.CityChooseRepositoryImpl
import com.maktay.weatherforecast.data.remote.repository.WeatherInfoRepositoryImpl
import com.maktay.weatherforecast.domain.repository.CityChooseRepository
import com.maktay.weatherforecast.domain.repository.WeatherInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideWeatherAPI() : OpenWeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCityChooseRepository(api : OpenWeatherApi) : CityChooseRepository {
        return CityChooseRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideWeatherInfoRepository(api : OpenWeatherApi) : WeatherInfoRepository {
        return WeatherInfoRepositoryImpl(api)
    }
}
