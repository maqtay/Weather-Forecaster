package com.maktay.weatherforecast.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.maktay.weatherforecast.databinding.FragmentHomeWithBottomsheetBinding
import com.maktay.weatherforecast.presentation.home.today_weather_info.TodayWeatherInfoAdapter


class HomeFragment : Fragment() {
    private val args : HomeFragmentArgs by navArgs()
    private lateinit var binding : FragmentHomeWithBottomsheetBinding
    private lateinit var todayWeatherInfoAdapter : TodayWeatherInfoAdapter

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        binding = FragmentHomeWithBottomsheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomSheet()
        initTodayRecyclerView()
    }

    private fun initBottomSheet() {

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomsheet.root)
        val coords = intArrayOf(0, 0)
        binding.bottomsheet.recyclerViewWeatherInfoToday.getLocationOnScreen(coords)

        bottomSheetBehavior.peekHeight =
            coords[1] + binding.bottomsheet.recyclerViewWeatherInfoToday.height

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet : View, newState : Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING, BottomSheetBehavior.STATE_EXPANDED -> {}

                    BottomSheetBehavior.STATE_COLLAPSED -> {}

                    else -> {}
                }
            }

            override fun onSlide(bottomSheet : View, slideOffset : Float) {}
        })
    }

    private fun initTodayRecyclerView() {
        todayWeatherInfoAdapter = TodayWeatherInfoAdapter()
        binding.bottomsheet.recyclerViewWeatherInfoToday.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.bottomsheet.recyclerViewWeatherInfoToday.adapter = todayWeatherInfoAdapter
    }
}
