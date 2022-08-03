package com.maktay.weatherforecast.domain.repository

import com.maktay.weatherforecast.data.remote.dto.SearchResultDto

interface CityChooseRepository {
    suspend fun search(query : String) : List<SearchResultDto>
}
