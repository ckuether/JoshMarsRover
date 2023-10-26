package com.example.joshmarsrover.data.repository

import com.example.joshmarsrover.api.RoversApiService
import com.example.joshmarsrover.data.model.Photo
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.di.app.NASA_API_KEY
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
import javax.inject.Singleton

@Singleton
class RoversRepositoryImpl @Inject constructor(
    private val apiService: RoversApiService,
    @NASA_API_KEY private val apiKey: String
): RoversRepository {

    override var cachedRovers: List<Rover>? = null

    override suspend fun getRoversFromNetwork() = callbackFlow {
        val apiCall: Call<List<Rover>> = apiService.getRovers(apiKey)
        withContext(Dispatchers.IO) {
            try{
                if(cachedRovers == null){
                    trySend(Loading)
                    cachedRovers = apiCall.await()
                }
                trySend(Success(cachedRovers!!))
            }catch (e: Exception){
                e.printStackTrace()
                trySend(Failure(e))
            }
        }
        awaitClose {
            apiCall.cancel()
        }
    }

    override suspend fun getRoverPhotosFromNetwork(rover: Rover, date: String) = callbackFlow {
        val apiCall = apiService.getRoverPhotos(rover.name, date, apiKey)
        withContext(Dispatchers.IO){
            try{
                trySend(Loading)
                val roverPhotos: List<Photo> = apiCall.await()
                val roverIndex = cachedRovers?.indexOf(rover) ?: -1
                if(roverIndex != -1){
                    cachedRovers!![roverIndex].photos = roverPhotos
                }
                trySend(Success(roverPhotos))
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