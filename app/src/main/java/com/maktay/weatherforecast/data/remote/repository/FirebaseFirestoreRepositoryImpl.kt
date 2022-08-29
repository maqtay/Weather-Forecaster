package com.maktay.weatherforecast.data.remote.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.maktay.weatherforecast.common.Constants
import com.maktay.weatherforecast.common.RequestState
import com.maktay.weatherforecast.domain.repository.FirebaseFirestoreRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseFirestoreRepositoryImpl @Inject constructor(private val db : FirebaseFirestore) :
    FirebaseFirestoreRepository {
    override suspend fun getImage(id : String?) = callbackFlow {
        val snapshotListener =
            db.collection(Constants.FIREBASE_IMAGE_DB_NAME).document(id.toString())
                .addSnapshotListener { snapshot, error ->
                    var response =
                        if (snapshot != null) RequestState.Success((snapshot.data?.get("urls") as ArrayList<*>)[0].toString()) else RequestState.Error(
                            error?.message ?: "An unpexpected error occured!"
                        )
                    trySend(response).isSuccess
                }
        awaitClose {
            snapshotListener.remove()
        }
    }
}
