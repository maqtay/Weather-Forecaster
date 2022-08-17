package com.maktay.weatherforecast.data.remote.repository

import com.maktay.weatherforecast.data.remote.OpenWeatherApi
import com.maktay.weatherforecast.data.remote.dto.HourlyWeatherDto
import com.maktay.weatherforecast.domain.repository.WeatherInfoRepository
import javax.inject.Inject

class WeatherInfoRepositoryImpl @Inject constructor(private val api : OpenWeatherApi) :
    WeatherInfoRepository {
    override suspend fun getHourlyWeatherInfo(lat : Double, lon : Double) : HourlyWeatherDto {
        return api.getHourlyWeather(lat, lon, "daily", "bae5c88745a5f7bf7fa799c77b48a946")
    }
}
