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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val splashViewModel : SplashViewModel by viewModels()

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        val state = splashViewModel.state.value
        val bundle = bundleOf("selectedSearchResult" to state)
        Handler(Looper.getMainLooper()).postDelayed({
            if (state.country?.isBlank() == true)
                findNavController().navigate(R.id.action_splashFragment_to_cityChooseFragment)
            else
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment, bundle)
        }, 100)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}
