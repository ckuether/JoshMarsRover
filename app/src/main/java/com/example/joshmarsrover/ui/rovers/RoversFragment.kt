package com.example.joshmarsrover.ui.rovers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joshmarsrover.MainActivity
import com.example.joshmarsrover.R
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.databinding.FragmentRoversBinding
import com.example.joshmarsrover.domain.model.ResponseWrapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoversFragment : Fragment(R.layout.fragment_rovers) {

    lateinit var binding: FragmentRoversBinding

    companion object {
        fun newInstance() = RoversFragment()
    }

    private lateinit var viewModel: RoversViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as MainActivity).viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRoversBinding.bind(view)

        viewModel.roversResponse.observe(viewLifecycleOwner){
            updateRoverViews(it)
        }

        viewModel.updateRoverAtPos.observe(viewLifecycleOwner){
            binding.roversRv.adapter?.notifyItemChanged(it)
        }
    }

    private fun updateRoverViews(response: ResponseWrapper<List<Rover>>){
        binding.progressBar.visibility = if(response is ResponseWrapper.Loading)  View.VISIBLE else View.GONE
        binding.roversRv.visibility = if(response is ResponseWrapper.Success) View.VISIBLE else View.GONE

        if(response is ResponseWrapper.Success){
            binding.roversRv.layoutManager = LinearLayoutManager(requireContext())
            binding.roversRv.adapter = RoversRVAdapter(viewModel)
        }
    }
}