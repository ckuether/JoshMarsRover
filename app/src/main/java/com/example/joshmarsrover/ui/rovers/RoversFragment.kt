package com.example.joshmarsrover.ui.rovers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.joshmarsrover.R
import com.example.joshmarsrover.RoversCallback
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.databinding.FragmentRoversBinding
import com.example.joshmarsrover.domain.model.ResponseWrapper
import com.example.joshmarsrover.ui.common.AppResourceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoversFragment : Fragment(R.layout.fragment_rovers) {

    lateinit var callback: RoversCallback
    private lateinit var binding: FragmentRoversBinding
    private val recyclerView: RecyclerView
        get() = binding.roversRv

    @Inject lateinit var resourceManager: AppResourceManager

    companion object {
        fun newInstance() = RoversFragment()
    }

    private lateinit var viewModel: RoversViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callback = (requireActivity() as RoversCallback)
        viewModel = callback.viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRoversBinding.bind(view)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RoversRVAdapter(callback)

        val spacerDecoration = DividerItemDecoration(requireContext(), VERTICAL)
        spacerDecoration.setDrawable(resourceManager.getDrawableResource(R.drawable.spacer)!!)
        recyclerView.addItemDecoration(spacerDecoration)

        viewModel.roversResponse.observe(viewLifecycleOwner){
            updateRoverViews(it)
        }

        viewModel.updateRoverAtPos.observe(viewLifecycleOwner){
            recyclerView.adapter?.notifyItemChanged(it)
        }
    }

    private fun updateRoverViews(response: ResponseWrapper<List<Rover>>){
        binding.progressBar.visibility = if(response is ResponseWrapper.Loading)  View.VISIBLE else View.GONE
        recyclerView.visibility = if(response is ResponseWrapper.Success) View.VISIBLE else View.GONE

        if(response is ResponseWrapper.Success){
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }
}