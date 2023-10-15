package com.example.joshmarsrover.ui.rovers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.domain.model.ResponseWrapper
import com.example.joshmarsrover.domain.repository.RoversRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoversViewModel @Inject constructor(
    private var roversRepo: RoversRepository
    ):ViewModel() {

    val rovers: List<Rover>
        get() = roversRepo.cachedRovers ?: listOf()

    private var _roversResponse = MutableLiveData<ResponseWrapper<List<Rover>>>()
    val roversResponse: LiveData<ResponseWrapper<List<Rover>>>
        get() = _roversResponse

    private var _updateRoverAtPos = MutableLiveData<Int>()
    val updateRoverAtPos: LiveData<Int>
        get() = _updateRoverAtPos

    init {
        collectRoversFlow()
    }

    private fun collectRoversFlow() = viewModelScope.launch {
        roversRepo.getRoversFromNetwork()
            .collect { roversRepsonse ->
                _roversResponse.postValue(roversRepsonse)
                if(roversRepsonse is ResponseWrapper.Success){
                    updateRoverListPhotos(roversRepsonse.data)
                }
            }
    }

    private fun updateRoverListPhotos(rovers: List<Rover>){
        rovers.forEach { rover ->
            if(rover.photos == null)
                getRoverPhotosFromNetwork(rover)
        }
    }

    fun getRoverPhotosFromNetwork(rover: Rover) = viewModelScope.launch {
        roversRepo.getRoverPhotosFromNetwork(rover)
            .collect{
                _updateRoverAtPos.postValue(it)
            }
    }
}