package com.maktay.weatherforecast.common

import android.util.TimeFormatException
import java.lang.Exception
import java.sql.Timestamp
import java.text.SimpleDateFormat

object Utils {
    fun getDateTime(timestamp : Int = 0) : String {
        return try {
            val sdf = SimpleDateFormat("HH:mm")
            sdf.format(Timestamp(timestamp.toLong() * 1000))
        } catch (e : Exception) {
            "00:00"
        }
    }
}
