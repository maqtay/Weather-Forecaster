package com.maktay.weatherforecast.presentation.home.today_weather_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maktay.weatherforecast.databinding.ItemWeatherTimeBinding

class TodayWeatherInfoAdapter : RecyclerView.Adapter<TodayWeatherInfoAdapter.ModelViewHolder>() {
    private lateinit var itemWeatherTimeBinding : ItemWeatherTimeBinding

    class ModelViewHolder(binding : ItemWeatherTimeBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ModelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        itemWeatherTimeBinding = ItemWeatherTimeBinding.inflate(inflater, parent, false)
        return ModelViewHolder(itemWeatherTimeBinding)
    }

    override fun onBindViewHolder(holder : ModelViewHolder, position : Int) {

    }

    override fun getItemCount() : Int {
        return 10
    }
}