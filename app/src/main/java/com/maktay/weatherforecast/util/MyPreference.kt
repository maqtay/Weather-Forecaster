package com.maktay.weatherforecast.util

import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import javax.inject.Inject

class MyPreference @Inject constructor(val sharedPreferences : SharedPreferences) {
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

    inline fun <reified T> getModel(key : String) : T? {
        val value = sharedPreferences.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)

    }

    fun <T> setModel(`object` : T, key : String) {
        val jsonString = GsonBuilder().create().toJson(`object`)
        sharedPreferences.edit().putString(key, jsonString).apply()
    }
}
