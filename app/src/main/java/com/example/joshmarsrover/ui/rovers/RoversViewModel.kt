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

enum class RoversNavigation{
    ROVERS,
    ROVER_DETAILS
}

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
            .collect { roversResponse ->
                _roversResponse.postValue(roversResponse)
            }
    }

    fun getRoverPhotosFromNetwork(pos: Int) = viewModelScope.launch {
        val rover = rovers[pos]
        roversRepo.getRoverPhotosFromNetwork(rover, rover.max_date)
            .collect{
                if(it is ResponseWrapper.Success){
                    _updateRoverAtPos.postValue(pos)
                }
            }
    }
}