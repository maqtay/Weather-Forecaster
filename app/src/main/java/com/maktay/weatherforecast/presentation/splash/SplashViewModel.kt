package com.maktay.weatherforecast.presentation.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.maktay.weatherforecast.common.Constants
import com.maktay.weatherforecast.domain.model.SearchResult
import com.maktay.weatherforecast.util.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val prefs : MyPreference) : ViewModel() {
    private val _state = mutableStateOf(SearchResult())
    val state : MutableState<SearchResult> = _state

    init {
        setHomePage()
    }

    private fun setHomePage() {
        prefs.getModel<SearchResult>(Constants.CHOSEN_CITY)?.let { _state.value = it }
    }
}
