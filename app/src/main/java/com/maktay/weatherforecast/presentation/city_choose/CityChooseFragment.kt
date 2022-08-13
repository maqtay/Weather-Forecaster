package com.maktay.weatherforecast.presentation.city_choose

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.maktay.weatherforecast.R
import com.maktay.weatherforecast.databinding.FragmentCityChooseBinding
import com.maktay.weatherforecast.domain.model.SearchResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CityChooseFragment : Fragment(), TextWatcher, View.OnClickListener {
    private lateinit var binding : FragmentCityChooseBinding
    private lateinit var cityChooseAdapter : CityChooseAdapter
    private val cityChooseViewModel : CityChooseViewModel by viewModels()
    var runnable = Runnable {}
    private val handler = Handler(Looper.myLooper()!!)
    private lateinit var selectedSearchResult : SearchResult

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        binding = FragmentCityChooseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        initRecyclerView()
        initViews()
        observeData()
    }

    private fun observeData() {
        cityChooseViewModel.state.observe(viewLifecycleOwner) {
            if (it.searchResult.isNotEmpty()) {
                cityChooseAdapter.setList(it.searchResult)
            }
        }
    }

    private fun initRecyclerView() {
        cityChooseAdapter =
            CityChooseAdapter(CityChooseAdapter.OnSearchItemClickListener { searchResult, isListCleared ->
                val colorRes = if (isListCleared)
                    R.color.search_fab_with_opacity
                else
                    R.color.search_fab

                binding.fabGoHomePage.imageTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            colorRes
                        )
                    )
                searchResult?.let {
                    selectedSearchResult = it
                }
            })

        binding.cityRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.cityRecyclerView.adapter = cityChooseAdapter
    }

    private fun initViews() {
        binding.clearTextButton.setOnClickListener(this)
        binding.fabGoHomePage.setOnClickListener(this)
        binding.searchBarEdittext.addTextChangedListener(this)
    }

    override fun beforeTextChanged(p0 : CharSequence?, p1 : Int, p2 : Int, p3 : Int) {

    }

    override fun onTextChanged(p0 : CharSequence?, p1 : Int, p2 : Int, p3 : Int) {

    }

    override fun afterTextChanged(p0 : Editable?) {
        binding.clearTextButton.isVisible = p0?.isBlank() == false
        binding.selectCityText.isVisible = p0?.isBlank() == true

        runnable = Runnable {
            if (p0?.length!! > 3) getCityFromApi(p0.toString())
            if (p0.isEmpty()) cityChooseAdapter.clearList()
        }
        handler.postDelayed(runnable, 250)
    }

    private fun getCityFromApi(s : String) {
        cityChooseViewModel.search(s)
    }

    override fun onClick(p0 : View?) {
        when (p0?.id) {
            R.id.clear_text_button -> {
                binding.searchBarEdittext.text.clear()
            }

            R.id.fab_go_home_page -> {
                val bundle = bundleOf("selectedSearchResult" to selectedSearchResult)
                cityChooseViewModel.setSelectedCity(selectedSearchResult)
                findNavController().navigate(
                    R.id.action_cityChooseFragment_to_homeFragment,
                    bundle
                )
            }
        }
    }
}
