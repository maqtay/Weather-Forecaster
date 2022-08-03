package com.maktay.weatherforecast.presentation.city_choose

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maktay.weatherforecast.R
import com.maktay.weatherforecast.databinding.ItemCityBinding
import com.maktay.weatherforecast.domain.model.SearchResult

class CityChooseAdapter(private val onSearchItemClickListener : OnSearchItemClickListener) :
    RecyclerView.Adapter<CityChooseAdapter.ModelViewHolder>() {
    private lateinit var itemCityBinding : ItemCityBinding
    private var selectedItemPosition : Int = -1
    private var list = arrayListOf<SearchResult>()

    class ModelViewHolder(var binding : ItemCityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(s : SearchResult) {
            binding.cityTextView.text = s.name + ", ${s.country}"
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ModelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        itemCityBinding = ItemCityBinding.inflate(inflater, parent, false)
        return ModelViewHolder(itemCityBinding)
    }

    override fun onBindViewHolder(
        holder : ModelViewHolder,
        @SuppressLint("RecyclerView") position : Int
    ) {
        holder.bindItems(list[position])

        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
            onSearchItemClickListener.onClickSearchItems(list[position], false)
        }
        if (selectedItemPosition == position)
            holder.itemView.findViewById<TextView>(R.id.city_text_view)
                .setBackgroundResource(R.drawable.bg_city_item_selected)
        else
            holder.itemView.findViewById<TextView>(R.id.city_text_view)
                .setBackgroundResource(R.drawable.bg_city_item)
    }

    override fun getItemCount() : Int {
        return list.size
    }

    fun setList(searchResult : List<SearchResult>) {
        list.clear()
        list.addAll(searchResult)
        this.notifyDataSetChanged()
    }

    fun clearList() {
        selectedItemPosition = -1
        onSearchItemClickListener.onClickSearchItems(searchResult = null, isListCleared = true)
        list.clear()
        this.notifyDataSetChanged()
    }

    class OnSearchItemClickListener(val clickListener : (searchResult : SearchResult?, isListCleared : Boolean) -> Unit) {
        fun onClickSearchItems(searchResult : SearchResult?, isListCleared : Boolean) = clickListener(searchResult, isListCleared)
    }
}

