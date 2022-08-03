package com.maktay.weatherforecast.domain.model

import java.io.Serializable

data class SearchResult(
    val name : String? = "",
    val lat : Double? = .0,
    val lon : Double? = .0,
    val country : String? = "",
    val localName : String? = ""
) : Serializable
