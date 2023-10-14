package com.example.joshmarsrover.domain.repository

import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.domain.model.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface RoversRepository {
    suspend fun getRoversFromNetwork(): Flow<ResponseWrapper<List<Rover>>>
}