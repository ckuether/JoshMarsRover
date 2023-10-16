package com.example.joshmarsrover.ui.rovers.rover_details

import androidx.lifecycle.ViewModel
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.domain.repository.RoversRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoverDetailsViewModel @Inject constructor(private val roversRepo: RoversRepository): ViewModel() {

    val rovers: List<Rover>
        get() = roversRepo.cachedRovers!!

    private var roverPos: Int = 0

    fun updateRoverPosition(pos: Int){
        roverPos = pos
    }

    val rover: Rover
        get() = rovers[roverPos]

}