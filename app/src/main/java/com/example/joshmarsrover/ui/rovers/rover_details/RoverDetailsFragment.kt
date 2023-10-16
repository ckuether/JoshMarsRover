package com.example.joshmarsrover.ui.rovers.rover_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.joshmarsrover.R
import com.example.joshmarsrover.common.Contstants.KEY_ROVER_POS
import com.example.joshmarsrover.common.toFormattedDate
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.ui.rovers.RoversCallback
import com.example.joshmarsrover.databinding.FragmentRoverDetailsBinding
import com.example.joshmarsrover.ui.common.DatePickerManager
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class RoverDetailsFragment: Fragment(R.layout.fragment_rover_details) {

    private lateinit var roversCallback: RoversCallback
    private lateinit var binding: FragmentRoverDetailsBinding
    private lateinit var viewModel: RoverDetailsViewModel

    @Inject lateinit var datePickerManager: DatePickerManager

    private val datePicker: MaterialDatePicker<Long>
        get() = datePickerManager.datePicker

    private val rover: Rover
        get() = viewModel.rover

    companion object {
        fun newInstance(roverPos: Int): RoverDetailsFragment {
            val frag = RoverDetailsFragment()
            val b = Bundle()
            b.putInt(KEY_ROVER_POS, roverPos)
            frag.arguments = b
            return frag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val roverPos = arguments?.getInt(KEY_ROVER_POS)!!
        viewModel = ViewModelProvider(this)[RoverDetailsViewModel::class.java]
        //TODO:
        viewModel.setRoverPosition(roverPos)
        roversCallback = (requireActivity() as RoversCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRoverDetailsBinding.bind(view)

        binding.toolbar.toolbar.setNavigationIcon(com.google.android.material.R.drawable.ic_arrow_back_black_24)
        binding.toolbar.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.nameTv.text = rover.name
        binding.launchDateTv.detailsTv.text = rover.launchDateDescription
        binding.landingDateTv.detailsTv.text = rover.landingDateDescription
        binding.photoCountTv.detailsTv.text = rover.photoCountString
        binding.camerasAvailableTv.detailsTv.text = rover.camerasAvailableString

        binding.photosGrid.adapter = RoverPhotosGridAdapter(rover.photos ?: listOf())

        viewModel.selectedDate.observe(viewLifecycleOwner){
            updateSelectedDateText()
        }

        binding.datePickerContainer.setOnClickListener {
            datePickerManager.showDatePicker()
        }

        datePicker.addOnPositiveButtonClickListener {
            viewModel.updateSelectedDate(Date(it))
        }
    }

    private fun updateSelectedDateText(){
        binding.selectedDateTv.text = viewModel.selectedDateFormattedString
    }
}