package com.example.joshmarsrover.ui.rover_list

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

    private var _roverResponse = MutableLiveData<ResponseWrapper<List<Rover>>>()
    val roverResponse: LiveData<ResponseWrapper<List<Rover>>>
        get() = _roverResponse

    init {
        getRovers()
    }

    private fun getRovers() = viewModelScope.launch {
        roversRepo.getRoversFromNetwork().collect {
            _roverResponse.postValue(it)
        }
    }
}