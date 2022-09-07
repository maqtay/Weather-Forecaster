package com.maktay.weatherforecast.domain.model

data class WeatherInfoHourly(
    var timezone : String? = "",
    var timezoneOffset : Int? = 0,
    var current : Current? = Current(),
    var hourly : ArrayList<Hourly> = arrayListOf()
)

data class Hourly(
    var date : Int? = 0,
    var temp : Double? = .0,
    var humidity : Int? = 0,
    var uv : Int? = 0,
    var windSpeed : Double? = .0,
    var weather : Weather = Weather()
) {
    fun getHourlyTempDegree() : String {
        return temp?.minus(270)?.toInt().toString() + "°"
    }
}

class Current(
    var date : Int? = 0,
    var temp : Double? = .0,
    var humidity : Int? = 0,
    var uv : Int? = 0,
    var windSpeed : Double? = .0,
    var weather : Weather = Weather(),
    var backgroundImageUrl : String? = ""
) {
    fun getTempDegree() : String {
        return temp?.minus(270)?.toInt().toString() + "°"
    }

    fun getHumidityWithPercent() : String {
        return "%" + humidity.toString()
    }

    fun getUVWithIndex() : String {
        return when {
            uv!! < 2 -> "Low $uv"
            uv!! in 3..5 -> "Moderate $uv"
            uv!! in 6..7 -> "High $uv"
            uv!! in 8..10 -> "Very High $uv"
            uv!! > 11 -> "Extreme $uv"
            else -> "Low $uv"
        }
    }

    fun getWindSpeedWithKM() : String {
        return "${windSpeed?.toInt()} KM"
    }
}

data class Weather(
    var id : Int? = 0,
    var main : String? = "",
    var description : String? = "",
    var icon : String? = ""
)
