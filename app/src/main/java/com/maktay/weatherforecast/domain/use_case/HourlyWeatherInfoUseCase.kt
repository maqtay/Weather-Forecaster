package com.maktay.weatherforecast.domain.use_case

import com.maktay.weatherforecast.common.RequestState
import com.maktay.weatherforecast.data.remote.dto.HourlyWeatherDto
import com.maktay.weatherforecast.data.remote.dto.toWeatherInfoHourly
import com.maktay.weatherforecast.domain.model.WeatherInfoHourly
import com.maktay.weatherforecast.domain.repository.WeatherInfoRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HourlyWeatherInfoUseCase @Inject constructor(var weatherInfoRepository : WeatherInfoRepository) {
    operator fun invoke(lat : Double, lon : Double) = flow {
        try {
            emit(RequestState.Loading<WeatherInfoHourly>())
            val result = weatherInfoRepository.getHourlyWeatherInfo(lat, lon)
            emit(RequestState.Success<WeatherInfoHourly>(result.toWeatherInfoHourly()))
        } catch (e : Exception) {
            emit(
                RequestState.Error<HourlyWeatherDto>(
                    e.localizedMessage ?: "An unexpected error occured."
                )
            )
        }
    }
}
