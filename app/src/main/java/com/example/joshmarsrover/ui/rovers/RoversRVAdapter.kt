package com.example.joshmarsrover.ui.rovers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joshmarsrover.R
import com.example.joshmarsrover.RoversCallback
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.databinding.ViewHolderRoverBinding
import com.squareup.picasso.Picasso

class RoversRVAdapter(private val callback: RoversCallback): RecyclerView.Adapter<RoverViewHolder>() {

    private val viewModel: RoversViewModel
        get() = callback.viewModel

    private val rovers: List<Rover>
        get() = viewModel.rovers

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverViewHolder {
        val binding = ViewHolderRoverBinding.inflate(LayoutInflater.from(parent.context))
        return RoverViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return rovers.size
    }

    override fun onBindViewHolder(holder: RoverViewHolder, position: Int) {
        val rover = rovers[position]
        if(rover.photos == null){
            viewModel.getRoverPhotosFromNetwork(rover)
        }

        Picasso.get().load(rover.firstPhotoImageUri)
            .placeholder(R.drawable.default_image_placeholder)
            .fit()
            .into(holder.b.mainPhotoIv)

        holder.b.nameTv.text = rover.name
        holder.b.launchDateTv.text = "Launch: ${rover.formattedLaunchDate}"
        holder.b.landingDateTv.text = "Landing: ${rover.formattedLandingDate}"
        holder.b.photoCountTv.text = "Photo Count ${rover.photoCount}"
        holder.b.camerasAvailableTv.text = "Cameras Available: ${rover.camerasCount}"

        holder.b.container.setOnClickListener {
            callback.navigateToRoverDetails(rover)
        }
    }
}

class RoverViewHolder(val b: ViewHolderRoverBinding): RecyclerView.ViewHolder(b.root)