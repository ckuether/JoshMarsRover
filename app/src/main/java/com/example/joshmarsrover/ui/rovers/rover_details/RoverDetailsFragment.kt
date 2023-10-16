package com.example.joshmarsrover.ui.rovers.rover_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.joshmarsrover.R
import com.example.joshmarsrover.common.Contstants.KEY_ROVER_POS
import com.example.joshmarsrover.data.model.Photo
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.ui.rovers.RoversCallback
import com.example.joshmarsrover.databinding.FragmentRoverDetailsBinding
import com.example.joshmarsrover.domain.model.ResponseWrapper
import com.example.joshmarsrover.ui.common.DatePickerManager
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
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
        viewModel.initRoverPosition(roverPos)
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

        setGridAdapterPhotos(rover.photos)

        binding.datePickerContainer.setOnClickListener {
            datePickerManager.showDatePicker()
        }

        datePicker.addOnPositiveButtonClickListener {
            //TODO:
            val date = Date(it)
            viewModel.updateSelectedDate(date)
            updateSelectedDateText()
        }

        viewModel.updatePhotosResponse.observe(viewLifecycleOwner){
            it?.let {
                binding.progressBar.visibility = if(it is ResponseWrapper.Loading) View.VISIBLE else View.GONE

                if(it is ResponseWrapper.Success) {
                    setGridAdapterPhotos(it.data)
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