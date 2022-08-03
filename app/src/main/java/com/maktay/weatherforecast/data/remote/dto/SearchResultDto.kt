package com.maktay.weatherforecast.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.maktay.weatherforecast.domain.model.SearchResult

data class SearchResultDto(
    @SerializedName("name") var name : String? = null,
    @SerializedName("local_names") var localNames : LocalNames? = LocalNames(),
    @SerializedName("lat") var lat : Double? = null,
    @SerializedName("lon") var lon : Double? = null,
    @SerializedName("country") var country : String? = null
)

fun SearchResultDto.toSearchResult() : SearchResult {
    return SearchResult(
        name = name, lat = lat, lon = lon, country = country, localName = localNames?.tr
    )
}

data class LocalNames(
    @SerializedName("tr") var tr : String? = null
)
