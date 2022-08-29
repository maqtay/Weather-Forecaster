package com.maktay.weatherforecast.domain.use_case

import com.maktay.weatherforecast.domain.repository.FirebaseFirestoreRepository
import javax.inject.Inject

class FirebaseFirestoreUseCase @Inject constructor(var firebaseFirestoreRepository : FirebaseFirestoreRepository) {
    suspend operator fun invoke(id : String?) = firebaseFirestoreRepository.getImage(id)
}