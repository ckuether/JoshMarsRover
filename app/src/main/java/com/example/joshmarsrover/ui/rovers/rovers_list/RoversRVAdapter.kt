package com.example.joshmarsrover.ui.rovers.rovers_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joshmarsrover.R
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.databinding.ViewHolderRoverBinding
import com.example.joshmarsrover.ui.rovers.RoversViewModel
import com.squareup.picasso.Picasso

class RoversRVAdapter(private val viewModel: RoversViewModel): RecyclerView.Adapter<RoverViewHolder>() {

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
        holder.b.launchDateTv.detailsTv.text = "Launch: ${rover.formattedLaunchDate}"
        holder.b.landingDateTv.detailsTv.text = "Landing: ${rover.formattedLandingDate}"
        holder.b.photoCountTv.detailsTv.text = "Photo Count ${rover.photoCount}"
        holder.b.camerasAvailableTv.detailsTv.text = "Cameras Available: ${rover.camerasCount}"

        holder.b.container.setOnClickListener {
            viewModel.updateNavToRoverPos(position)
        }
    }
}

class RoverViewHolder(val b: ViewHolderRoverBinding): RecyclerView.ViewHolder(b.root)