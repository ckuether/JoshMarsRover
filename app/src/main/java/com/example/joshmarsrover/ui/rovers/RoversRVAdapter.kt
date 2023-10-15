package com.example.joshmarsrover.ui.rovers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joshmarsrover.R
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.databinding.ViewHolderRoverBinding
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

        holder.b.nameTv.text = rover.name
        holder.b.photoCountTv.text = "Photo Count ${rover.photoCount}"

        Picasso.get().load(rover.firstPhotoImageUri)
            .placeholder(R.drawable.default_image_placeholder)
            .fit()
            .into(holder.b.mainPhotoIv)

    }

}

class RoverViewHolder(val b: ViewHolderRoverBinding): RecyclerView.ViewHolder(b.root)