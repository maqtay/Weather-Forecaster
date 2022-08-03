package com.maktay.weatherforecast.util

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

class MyPreference @Inject constructor(private val sharedPreferences : SharedPreferences) {
    fun getString(tag : String, default : String) : String? {
        return sharedPreferences.getString(tag, default)
    }

    fun putString(tag : String, value : String) {
        sharedPreferences.edit().putString(tag, value)
    }

    fun getBoolean(tag : String) : Boolean {
        return sharedPreferences.getBoolean(tag, false)
    }

    fun putBoolean(tag : String, value : Boolean) {
        sharedPreferences.edit().putBoolean(tag, value)
    }
}
