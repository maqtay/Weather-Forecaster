package com.maktay.weatherforecast.domain.repository

import com.maktay.weatherforecast.data.remote.dto.HourlyWeatherDto

interface WeatherInfoRepository {
    suspend fun getHourlyWeatherInfo(lat :Double, lon : Double) : HourlyWeatherDto
}
