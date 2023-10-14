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

    private var _roversResponse = MutableLiveData<ResponseWrapper<List<Rover>>>()
    val roversResponse: LiveData<ResponseWrapper<List<Rover>>>
        get() = _roversResponse


    init {
        getRovers()
    }

    private fun getRovers() = viewModelScope.launch {
        roversRepo.getRoversFromNetwork().collect {
            _roversResponse.postValue(it)
        }
    }
}