package com.maktay.weatherforecast.presentation.city_choose

import com.maktay.weatherforecast.domain.model.SearchResult

data class CityChooseState(
    val isLoading : Boolean? = null,
    val error : String? = null,
    val searchResult : List<SearchResult> = arrayListOf<SearchResult>()
)
