package com.example.joshmarsrover.ui.rovers.rover_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joshmarsrover.common.DateFormat
import com.example.joshmarsrover.common.toFormattedString
import com.example.joshmarsrover.data.model.Photo
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.domain.model.ResponseWrapper
import com.example.joshmarsrover.domain.repository.RoversRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RoverDetailsViewModel @Inject constructor(private val roversRepo: RoversRepository): ViewModel() {

    lateinit var rover: Rover

    fun initViewModel(rover: Rover){
        this.rover = rover
        selectedDate = rover.maxDate
    }

    private var selectedDate: Date? = null
    private val networkFormatSelectedDate: String
        get() = selectedDate?.toFormattedString(DateFormat.NETWORK_FORMAT) ?: ""

    fun updateSelectedDate(date: Date?){
        selectedDate = date
        getRoverPhotosFromNetwork()
    }

    val selectedDateFormattedString: String
        get() = selectedDate?.toFormattedString() ?: ""

    private var _updatePhotosResponse = MutableLiveData<ResponseWrapper<List<Photo>>>()
    val updatePhotosResponse: LiveData<ResponseWrapper<List<Photo>>>
        get() = _updatePhotosResponse

    private fun getRoverPhotosFromNetwork() = viewModelScope.launch {
        roversRepo.getRoverPhotosFromNetwork(rover, networkFormatSelectedDate).collect{
            _updatePhotosResponse.value = it
        }
    }
}