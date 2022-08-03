package com.maktay.weatherforecast.di

import android.content.Context
import android.content.SharedPreferences
import com.maktay.weatherforecast.common.Constants
import com.maktay.weatherforecast.util.MyPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceModel {
    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context : Context) : SharedPreferences {
        return context.getSharedPreferences(Constants.SHARED_PREF_FILE, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideMySharedPreferenceManager(sharedPreferences : SharedPreferences) =
        MyPreference(sharedPreferences)
}
