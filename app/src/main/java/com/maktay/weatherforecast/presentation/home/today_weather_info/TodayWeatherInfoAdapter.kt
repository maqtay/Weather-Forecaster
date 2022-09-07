package com.maktay.weatherforecast.presentation.home.today_weather_info

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maktay.weatherforecast.common.Utils
import com.maktay.weatherforecast.databinding.ItemWeatherTimeBinding
import com.maktay.weatherforecast.domain.model.Hourly
import com.squareup.picasso.Picasso

class TodayWeatherInfoAdapter(val context : Context) :
    RecyclerView.Adapter<TodayWeatherInfoAdapter.ModelViewHolder>() {
    private lateinit var itemWeatherTimeBinding : ItemWeatherTimeBinding
    private var list = mutableListOf<Hourly>()

    class ModelViewHolder(var binding : ItemWeatherTimeBinding, var context : Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(hourly : Hourly) {
            binding.weatherItemDegree.text = hourly.getHourlyTempDegree()
            binding.weatherItemTime.text = Utils.getDateTime(hourly.date!!)
            Picasso.with(context)
                .load("http://openweathermap.org/img/wn/${hourly.weather.icon}@4x.png")
                .into(binding.weatherItemIcon)
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ModelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        itemWeatherTimeBinding = ItemWeatherTimeBinding.inflate(inflater, parent, false)
        return ModelViewHolder(itemWeatherTimeBinding, context)
    }

    override fun onBindViewHolder(holder : ModelViewHolder, position : Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount() : Int {
        return list.size
    }

    fun setList(newList : MutableList<Hourly>?) {
        newList?.let {
            list.clear()
            list.addAll(it)
        }
        notifyDataSetChanged()
    }
}