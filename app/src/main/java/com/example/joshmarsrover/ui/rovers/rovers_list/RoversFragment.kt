package com.example.joshmarsrover.ui.rovers.rovers_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.joshmarsrover.R
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.databinding.FragmentRoversBinding
import com.example.joshmarsrover.domain.model.ResponseWrapper
import com.example.joshmarsrover.ui.common.AppResourceManager
import com.example.joshmarsrover.ui.rovers.RoversActivity
import com.example.joshmarsrover.ui.rovers.RoversViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoversFragment : Fragment(R.layout.fragment_rovers) {

    private lateinit var roversActivity: RoversActivity

    private lateinit var binding: FragmentRoversBinding
    private val viewModel: RoversViewModel by activityViewModels()

    private val recyclerView: RecyclerView
        get() = binding.roversRv

    @Inject lateinit var resourceManager: AppResourceManager

    companion object{
        fun newInstance(): RoversFragment{
            return RoversFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roversActivity = activity as RoversActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRoversBinding.bind(view)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RoversRVAdapter(viewModel, object: RoversRVAdapter.RoversCallback {
            override fun getPhotos(pos: Int) {
                viewModel.getRoverPhotosFromNetwork(pos)
            }

            override fun onRoverSelected(rover: Rover) {
                roversActivity.attachRoverDetailsFragment(rover)
            }
        })

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