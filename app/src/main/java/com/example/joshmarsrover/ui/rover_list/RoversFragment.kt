package com.example.joshmarsrover.ui.rover_list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.joshmarsrover.R
import com.example.joshmarsrover.databinding.FragmentRoverListBinding
import com.example.joshmarsrover.domain.model.ResponseWrapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoversFragment : Fragment(R.layout.fragment_rover_list) {

    lateinit var binding: FragmentRoverListBinding

    companion object {
        fun newInstance() = RoversFragment()
    }

    private lateinit var viewModel: RoversViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RoversViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRoverListBinding.bind(view)

        viewModel.roverResponse.observe(viewLifecycleOwner){
            when (it) {
                is ResponseWrapper.Success -> {
                    binding.roversTv.text = it.data[0].name
                }

                is ResponseWrapper.Loading -> {
                    binding.roversTv.text = "Loading"
                }

                is ResponseWrapper.Failure -> {
                    binding.roversTv.text = "Failure"
                }
            }
        }
    }
}