package com.example.joshmarsrover.ui.rovers.rover_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.joshmarsrover.R
import com.example.joshmarsrover.common.Contstants.KEY_ROVER_POS
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.ui.rovers.RoversCallback
import com.example.joshmarsrover.databinding.FragmentRoverDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoverDetailsFragment: Fragment(R.layout.fragment_rover_details) {

    private lateinit var roversCallback: RoversCallback

    private lateinit var binding: FragmentRoverDetailsBinding

    private lateinit var viewModel: RoverDetailsViewModel

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
        viewModel.updateRoverPosition(roverPos)
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
        binding.launchDateTv.detailsTv.text = "Launch: ${rover.formattedLaunchDate}"
        binding.landingDateTv.detailsTv.text = "Landing: ${rover.formattedLandingDate}"
        binding.photoCountTv.detailsTv.text = "Photo Count ${rover.photoCount}"
        binding.camerasAvailableTv.detailsTv.text = "Cameras Available: ${rover.camerasCount}"
    }
}