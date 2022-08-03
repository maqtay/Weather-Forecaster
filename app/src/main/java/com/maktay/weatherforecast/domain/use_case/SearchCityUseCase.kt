package com.maktay.weatherforecast.domain.use_case

import com.maktay.weatherforecast.common.RequestState
import com.maktay.weatherforecast.data.remote.dto.toSearchResult
import com.maktay.weatherforecast.domain.model.SearchResult
import com.maktay.weatherforecast.domain.repository.CityChooseRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(var cityChooseRepository : CityChooseRepository) {
    operator fun invoke(query : String) = flow {
        try {
            emit(RequestState.Loading<SearchResult>())
            val result = cityChooseRepository.search(query)
            emit(RequestState.Success<List<SearchResult>>(result.map { it.toSearchResult() }))
        } catch (e : Exception) {
            emit(
                RequestState.Error<SearchResult>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        }
    }
}
