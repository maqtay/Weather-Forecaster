package com.maktay.weatherforecast.data.remote.repository

import com.maktay.weatherforecast.data.remote.OpenWeatherApi
import com.maktay.weatherforecast.data.remote.dto.SearchResultDto
import com.maktay.weatherforecast.domain.repository.CityChooseRepository
import javax.inject.Inject

class CityChooseRepositoryImpl @Inject constructor(private val api : OpenWeatherApi) :
    CityChooseRepository {
    override suspend fun search(query : String) : List<SearchResultDto> {
        return api.searchCity(query, 5, "bae5c88745a5f7bf7fa799c77b48a946")
    }
}
