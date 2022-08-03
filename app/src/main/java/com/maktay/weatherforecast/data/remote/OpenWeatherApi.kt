package com.maktay.weatherforecast.data.remote

import com.maktay.weatherforecast.data.remote.dto.SearchResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("/geo/1.0/direct")
    suspend fun searchCity(
        @Query("q") query : String,
        @Query("limit") limit : Int,
        @Query("appid") appId : String
    ) : List<SearchResultDto>
}