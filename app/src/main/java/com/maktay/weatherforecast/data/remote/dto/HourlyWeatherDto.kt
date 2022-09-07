package com.maktay.weatherforecast.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.maktay.weatherforecast.domain.model.Current
import com.maktay.weatherforecast.domain.model.Hourly
import com.maktay.weatherforecast.domain.model.Weather
import com.maktay.weatherforecast.domain.model.WeatherInfoHourly

data class HourlyWeatherDto(
    @SerializedName("lat") var lat : Double? = null,
    @SerializedName("lon") var lon : Double? = null,
    @SerializedName("timezone") var timezone : String? = null,
    @SerializedName("timezone_offset") var timezoneOffset : Int? = null,
    @SerializedName("current") var currentDto : CurrentDto? = CurrentDto(),
    @SerializedName("minutely") var minutely : ArrayList<MinutelyDto> = arrayListOf(),
    @SerializedName("hourly") var hourlyDto : ArrayList<HourlyDto> = arrayListOf()
) {

}

fun HourlyWeatherDto.toWeatherInfoHourly() : WeatherInfoHourly {
    return WeatherInfoHourly(timezone, timezoneOffset, currentDto?.toCurrent(),
        hourlyDto.map { it.toHourly() } as ArrayList<Hourly>)
}

data class HourlyDto(
    @SerializedName("dt") var dt : Int? = null,
    @SerializedName("temp") var temp : Double? = null,
    @SerializedName("feels_like") var feelsLike : Double? = null,
    @SerializedName("pressure") var pressure : Int? = null,
    @SerializedName("humidity") var humidity : Int? = null,
    @SerializedName("dew_point") var dewPoint : Double? = null,
    @SerializedName("uvi") var uvi : Double? = null,
    @SerializedName("clouds") var clouds : Int? = null,
    @SerializedName("visibility") var visibility : Int? = null,
    @SerializedName("wind_speed") var windSpeed : Double? = null,
    @SerializedName("wind_deg") var windDeg : Int? = null,
    @SerializedName("wind_gust") var windGust : Double? = null,
    @SerializedName("weather") var weather : ArrayList<WeatherDto> = arrayListOf(),
    @SerializedName("pop") var pop : Double? = null
)

fun HourlyDto.toHourly() : Hourly {
    return Hourly(dt, temp, humidity, uvi?.toInt(), windSpeed, weather[0].toWeather())
}

data class MinutelyDto(
    @SerializedName("dt") var dt : Int? = null,
    //@SerializedName("precipitation") var precipitation : Int? = null
)

data class CurrentDto(
    @SerializedName("dt") var dt : Int? = null,
    @SerializedName("sunrise") var sunrise : Int? = null,
    @SerializedName("sunset") var sunset : Int? = null,
    @SerializedName("temp") var temp : Double? = null,
    @SerializedName("feels_like") var feelsLike : Double? = null,
    @SerializedName("pressure") var pressure : Int? = null,
    @SerializedName("humidity") var humidity : Int? = null,
    @SerializedName("dew_point") var dewPoint : Double? = null,
    @SerializedName("uvi") var uvi : Double? = null,
    @SerializedName("clouds") var clouds : Int? = null,
    @SerializedName("visibility") var visibility : Int? = null,
    @SerializedName("wind_speed") var windSpeed : Double? = null,
    @SerializedName("wind_deg") var windDeg : Int? = null,
    @SerializedName("weather") var weather : ArrayList<WeatherDto> = arrayListOf()
)

fun CurrentDto.toCurrent() : Current {
    return Current(dt, temp, humidity, uvi?.toInt(), windSpeed, weather[0].toWeather())
}

data class WeatherDto(
    @SerializedName("id") var id : Int? = null,
    @SerializedName("main") var main : String? = null,
    @SerializedName("description") var description : String? = null,
    @SerializedName("icon") var icon : String? = null
)

fun WeatherDto.toWeather() : Weather {
    return Weather(id, main, description, icon)
}