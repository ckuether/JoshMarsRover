package com.example.joshmarsrover.ui.rovers.rover_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.joshmarsrover.R
import com.example.joshmarsrover.common.Contstants.KEY_ROVER
import com.example.joshmarsrover.common.datePickerValueToDate
import com.example.joshmarsrover.common.toast
import com.example.joshmarsrover.data.model.Photo
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.databinding.FragmentRoverDetailsBinding
import com.example.joshmarsrover.domain.model.ResponseWrapper
import com.example.joshmarsrover.ui.common.DatePickerManager
import com.example.joshmarsrover.ui.rovers.RoversActivity
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoverDetailsFragment: Fragment(R.layout.fragment_rover_details) {

    private lateinit var binding: FragmentRoverDetailsBinding
    private lateinit var viewModel: RoverDetailsViewModel

    @Inject lateinit var datePickerManager: DatePickerManager

    private val rover: Rover
        get() = viewModel.rover

    private val datePicker: MaterialDatePicker<Long> by lazy {
        datePickerManager.createDatePicker(rover.maxDate)
    }

    companion object {
        fun newInstance(rover: Rover): RoverDetailsFragment {
            val frag = RoverDetailsFragment()
            val b = Bundle()
            b.putParcelable(KEY_ROVER, rover)
            frag.arguments = b
            return frag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rover: Rover = arguments?.getParcelable(KEY_ROVER)!!
        viewModel = ViewModelProvider(this)[RoverDetailsViewModel::class.java]
        viewModel.initViewModel(rover)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRoverDetailsBinding.bind(view)

        binding.nameTv.text = rover.name
        binding.launchDateTv.detailsTv.text = rover.launchDateDescription
        binding.landingDateTv.detailsTv.text = rover.landingDateDescription
        binding.photoCountTv.detailsTv.text = rover.photoCountString
        binding.camerasAvailableTv.detailsTv.text = rover.camerasAvailableString

        updateSelectedDateText()

        setGridAdapterPhotos(rover.photos)

        binding.datePickerContainer.setOnClickListener {
            datePickerManager.showDatePicker(datePicker)
        }

        datePicker.addOnPositiveButtonClickListener {
            viewModel.updateSelectedDate(it.datePickerValueToDate())
            updateSelectedDateText()
        }

        viewModel.updatePhotosResponse.observe(viewLifecycleOwner){
            it?.let {
                binding.progressBar.visibility = if(it is ResponseWrapper.Loading) View.VISIBLE else View.GONE

                if(it is ResponseWrapper.Success) {
                    setGridAdapterPhotos(it.data)
                    if(it.data.isEmpty()){
                        requireContext().toast("No Photos Found")
                    }
                }
            }
        }
    }

    private fun updateSelectedDateText(){
        binding.selectedDateTv.text = viewModel.selectedDateFormattedString
    }

    private fun setGridAdapterPhotos(photos: List<Photo>?){
        binding.photosGrid.adapter = RoverPhotosGridAdapter(photos ?: listOf())
    }
}