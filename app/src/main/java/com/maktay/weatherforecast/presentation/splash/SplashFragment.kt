package com.maktay.weatherforecast.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maktay.weatherforecast.R
import com.maktay.weatherforecast.domain.model.SearchResult
import com.maktay.weatherforecast.presentation.home.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val splashViewModel : SplashViewModel by viewModels()
    private val homeFragmentViewModel : HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        val state = splashViewModel.state.value
        val bundle = bundleOf("selectedSearchResult" to state)
        val isStateSelected = state.country
        if (isStateSelected?.isBlank() == false) homeFragmentViewModel.run {  }

        Handler(Looper.getMainLooper()).postDelayed({
            if (isStateSelected?.isBlank() == true)
                findNavController().navigate(R.id.action_splashFragment_to_cityChooseFragment)
            else {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment, bundle)
            }
        }, 500)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}
