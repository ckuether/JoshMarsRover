package com.example.joshmarsrover.data.repository

import com.example.joshmarsrover.R
import com.example.joshmarsrover.api.RoversDeserializer
import com.example.joshmarsrover.common.LocalFileReader
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.domain.model.ResponseWrapper
import com.example.joshmarsrover.domain.repository.RoversRepository
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoversRepositoryTestImpl @Inject constructor(var inputStreamReader: LocalFileReader): RoversRepository {

    override var cachedRovers: List<Rover>? = null

    override suspend fun getRoversFromNetwork() = callbackFlow {
        withContext(Dispatchers.IO) {
            try {
                trySend(ResponseWrapper.Loading)
                delay(2000)
                val roversJson = inputStreamReader.getRawResource(R.raw.rovers_test)
                cachedRovers = RoversDeserializer().deserialize(roversJson, TypeToken.getParameterized(List::class.java, Rover::class.java).type, null)
                trySend(ResponseWrapper.Success(cachedRovers!!))
            }catch (e: Exception){
                e.printStackTrace()
                trySend(ResponseWrapper.Failure(e))
            }
        }
        awaitClose {

        }
    }

    override suspend fun getRoverPhotosFromNetwork(rover: Rover) = callbackFlow {
        withContext(Dispatchers.IO) {
            try {
                trySend(-1)
            } catch (e: Exception) {
                e.printStackTrace()
                trySend(-1)
            }
        }
        awaitClose{

        }
    }

}