package com.example.joshmarsrover.data.repository

import com.example.joshmarsrover.api.RoversApiService
import com.example.joshmarsrover.common.Contstants.API_KEY
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.domain.repository.RoversRepository
import javax.inject.Inject
import com.example.joshmarsrover.domain.model.ResponseWrapper.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.await
import java.lang.Exception


class RoversRepositoryImpl @Inject constructor(private val apiService: RoversApiService): RoversRepository {

    override suspend fun getRoversFromNetwork() = callbackFlow {
        val apiCall: Call<List<Rover>> = apiService.getRovers(API_KEY)
        withContext(Dispatchers.IO) {
            try{
                trySend(Loading)
                val roverList = apiCall.await()
                trySend(Success(roverList))
            }catch (e: Exception){
                e.printStackTrace()
                trySend(Failure(e))
            }
        }
        awaitClose {
            apiCall.cancel()
        }
    }
}