package com.maktay.weatherforecast.domain.repository

import com.maktay.weatherforecast.common.RequestState
import kotlinx.coroutines.flow.Flow

interface FirebaseFirestoreRepository {
    suspend fun getImage(id : String?) : Flow<RequestState<String>>
}