package com.example.joshmarsrover.ui.rovers.rover_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.joshmarsrover.R
import com.example.joshmarsrover.ui.rovers.RoversCallback
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.databinding.FragmentRoverDetailsBinding
import com.example.joshmarsrover.ui.rovers.RoversViewModel

class RoverDetailsFragment: Fragment(R.layout.fragment_rover_details) {

    private lateinit var roversCallback: RoversCallback

    private lateinit var binding: FragmentRoverDetailsBinding

    private val viewModel: RoversViewModel
        get() = roversCallback.viewModel

    companion object {
        fun newInstance(): RoverDetailsFragment {
            val frag = RoverDetailsFragment()
            return frag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        roversCallback = (requireActivity() as RoversCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRoverDetailsBinding.bind(view)

        binding.toolbar.toolbar.setNavigationIcon(com.google.android.material.R.drawable.ic_arrow_back_black_24)
        binding.toolbar.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }
}