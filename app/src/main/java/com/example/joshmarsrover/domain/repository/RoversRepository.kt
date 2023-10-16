package com.example.joshmarsrover.domain.repository

import com.example.joshmarsrover.data.model.Photo
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.domain.model.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface RoversRepository {
    var cachedRovers: List<Rover>?
    suspend fun getRoversFromNetwork(): Flow<ResponseWrapper<List<Rover>>>
    suspend fun getRoverPhotosFromNetwork(rover: Rover, date: String): Flow<ResponseWrapper<List<Photo>>>
}