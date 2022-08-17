package com.maktay.weatherforecast.data.remote

import com.maktay.weatherforecast.data.remote.dto.HourlyWeatherDto
import com.maktay.weatherforecast.data.remote.dto.SearchResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    //https://api.openweathermap.org/geo/1.0/direct?q=Sivas&limit=5&appid=
    @GET("/geo/1.0/direct")
    suspend fun searchCity(
        @Query("q") query : String,
        @Query("limit") limit : Int,
        @Query("appid") appId : String
    ) : List<SearchResultDto>

    //https://api.openweathermap.org/data/3.0/onecall?lat=39.732501&lon=37.023393&exclude=daily&appid=
    @GET("/data/3.0/onecall")
    suspend fun getHourlyWeather(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
        @Query("exclude") exclude : String,
        @Query("appid") appId : String
    ) : HourlyWeatherDto
}