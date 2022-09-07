package com.maktay.weatherforecast.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.maktay.weatherforecast.R
import com.maktay.weatherforecast.common.Utils
import com.maktay.weatherforecast.databinding.FragmentHomeWithBottomsheetBinding
import com.maktay.weatherforecast.presentation.home.today_weather_info.TodayWeatherInfoAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), View.OnClickListener {
    private val args : HomeFragmentArgs by navArgs()
    private lateinit var binding : FragmentHomeWithBottomsheetBinding
    private lateinit var todayWeatherInfoAdapter : TodayWeatherInfoAdapter
    private val homeFragmentViewModel : HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        homeFragmentViewModel.getWeatherData()
        binding = FragmentHomeWithBottomsheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initBottomSheet()
        setViewVariables()
        observeData()
        observeBackgroundImage()
        initTodayRecyclerView()
    }

    private fun initViews() {
        binding.mainLayout.header.refresh.setOnClickListener(this)
        binding.mainLayout.header.locationIcon.setOnClickListener(this)
    }

    private fun setViewVariables() {
        binding.mainLayout.header.cityName.text =
            args.selectedSearchResult.name + ", " + args.selectedSearchResult.country
    }

    private fun observeData() {
        homeFragmentViewModel.state.observe(viewLifecycleOwner) {
            if (it.isLoading == false && it.error == null) {
                binding.mainLayout.header.refresh.clearAnimation()
                binding.mainLayout.weatherModel = it.result
                binding.mainLayout.compWeatherInfo.weatherInfoModel = it.result?.current
                binding.mainLayout.header.timeText.text =
                    Utils.getDateTime(it.result?.current?.date!!)
                setTodayWeatherInfo(it)
            }
        }
    }

    private fun setTodayWeatherInfo(hourlyWeatherInfoState : HourlyWeatherInfoState) {
        val newList = hourlyWeatherInfoState.result?.hourly?.subList(0, 12)
        todayWeatherInfoAdapter.setList(newList)
    }

    private fun observeBackgroundImage() {
        homeFragmentViewModel.backgroundImageUrl.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                setBackgroundImage(it)
            }
        }
    }

    private fun setBackgroundImage(url : String) {
        Picasso.with(requireContext()).load(url).fit().into(binding.mainLayout.background)
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
        todayWeatherInfoAdapter = TodayWeatherInfoAdapter(requireContext())
        binding.bottomsheet.recyclerViewWeatherInfoToday.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.bottomsheet.recyclerViewWeatherInfoToday.adapter = todayWeatherInfoAdapter
    }

    private fun refresh() {
        binding.mainLayout.header.refresh.clearAnimation()
        val anim = RotateAnimation(
            30f,
            360f,
            (binding.mainLayout.header.refresh.width / 2).toFloat(),
            (binding.mainLayout.header.refresh.height / 2).toFloat(),
        )
        anim.fillAfter = true
        anim.repeatCount = 1
        anim.duration = 500
        binding.mainLayout.header.refresh.animation = anim
        homeFragmentViewModel.getWeatherData()
    }

    override fun onClick(p0 : View?) {
        when (p0?.id) {
            R.id.refresh -> {
                refresh()
            }

            R.id.location_icon -> {
                findNavController().navigate(R.id.action_homeFragment_to_cityChooseFragment)
            }
        }
    }
}
