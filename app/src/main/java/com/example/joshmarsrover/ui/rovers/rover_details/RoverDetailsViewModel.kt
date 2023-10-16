package com.example.joshmarsrover.ui.rovers.rover_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.joshmarsrover.common.DateFormat
import com.example.joshmarsrover.common.toDate
import com.example.joshmarsrover.common.toFormattedString
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.domain.repository.RoversRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RoverDetailsViewModel @Inject constructor(private val roversRepo: RoversRepository): ViewModel() {

    val rovers: List<Rover>
        get() = roversRepo.cachedRovers!!

    private var roverPos: Int = 0
    fun setRoverPosition(pos: Int){
        roverPos = pos
        updateSelectedDate(rover.max_date.toDate(DateFormat.NETWORK_FORMAT))
    }

    val rover: Rover
        get() = rovers[roverPos]


    private var _selectedDate = MutableLiveData<Date?>()
    val selectedDate: LiveData<Date?>
        get() = _selectedDate

    fun updateSelectedDate(date: Date?){
        _selectedDate.value = date
    }

    val selectedDateFormattedString: String
        get() = selectedDate.value?.toFormattedString() ?: ""
}