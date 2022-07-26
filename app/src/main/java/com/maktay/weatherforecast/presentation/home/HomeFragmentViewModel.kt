package com.maktay.weatherforecast.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maktay.weatherforecast.common.Constants
import com.maktay.weatherforecast.common.RequestState
import com.maktay.weatherforecast.domain.model.SearchResult
import com.maktay.weatherforecast.domain.model.WeatherInfoHourly
import com.maktay.weatherforecast.domain.use_case.FirebaseFirestoreUseCase
import com.maktay.weatherforecast.domain.use_case.HourlyWeatherInfoUseCase
import com.maktay.weatherforecast.util.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val hourlyWeatherInfoUseCase : HourlyWeatherInfoUseCase,
    private val firebaseFirestoreUseCase : FirebaseFirestoreUseCase,
    private val prefs : MyPreference
) :
    ViewModel() {
    val state = MutableLiveData<HourlyWeatherInfoState>()
    private val chosenCity = MutableLiveData<SearchResult>()
    val backgroundImageUrl = MutableLiveData<String>()

    init {
        chosenCity.value = prefs.getModel<SearchResult>(Constants.CHOSEN_CITY)
    }

    fun getWeatherData() {
        chosenCity.value.let {
            hourlyWeatherInfoUseCase.invoke(chosenCity.value?.lat!!, chosenCity.value?.lon!!)
                .onEach { result ->
                    when (result) {
                        is RequestState.Success -> {
                            state.value =
                                HourlyWeatherInfoState(result = result.data as WeatherInfoHourly)
                            getBackgroundImage(result.data.current?.weather?.id.toString())
                        }

                        is RequestState.Error -> {
                            state.value = HourlyWeatherInfoState(
                                error = result.message ?: "An unexpected error occured."
                            )
                        }

                        is RequestState.Loading -> {
                            state.value = HourlyWeatherInfoState(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private suspend fun getBackgroundImage(id : String?) {
        firebaseFirestoreUseCase.invoke(id).onEach { requestState ->
            when (requestState) {
                is RequestState.Success -> {
                    state.value?.result?.current?.backgroundImageUrl = requestState.data
                    backgroundImageUrl.value = requestState.data
                }

                is RequestState.Error -> state.value?.result?.current?.backgroundImageUrl = ""

                is RequestState.Loading -> Log.d("Loading", "Loading")
            }
        }.launchIn(viewModelScope)
    }
}