package com.maktay.weatherforecast.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.maktay.weatherforecast.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val args : HomeFragmentArgs by navArgs()
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {

    }
}