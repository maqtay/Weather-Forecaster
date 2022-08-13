package com.maktay.weatherforecast.presentation.city_choose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maktay.weatherforecast.common.Constants
import com.maktay.weatherforecast.common.RequestState
import com.maktay.weatherforecast.domain.model.SearchResult
import com.maktay.weatherforecast.domain.use_case.SearchCityUseCase
import com.maktay.weatherforecast.util.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CityChooseViewModel @Inject constructor(private val searchCityUseCase : SearchCityUseCase, private val prefs : MyPreference) :
    ViewModel() {
    val state = MutableLiveData<CityChooseState>()

    fun search(query : String) {
        searchCityUseCase.invoke(query).onEach { result ->
            when (result) {
                is RequestState.Success -> {
                    state.value =
                        CityChooseState(searchResult = result.data!! as List<SearchResult>)
                }

                is RequestState.Error -> {
                    state.value = CityChooseState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is RequestState.Loading -> {
                    state.value = CityChooseState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setSelectedCity(searchResult : SearchResult) {
        prefs.setModel(searchResult, Constants.CHOSEN_CITY)
    }
}
