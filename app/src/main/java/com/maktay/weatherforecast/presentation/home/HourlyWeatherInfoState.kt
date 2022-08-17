package com.maktay.weatherforecast.presentation.home

import com.maktay.weatherforecast.domain.model.WeatherInfoHourly

data class HourlyWeatherInfoState(
    val isLoading : Boolean? = false,
    val error : String? = null,
    val result : WeatherInfoHourly? = WeatherInfoHourly()
)
