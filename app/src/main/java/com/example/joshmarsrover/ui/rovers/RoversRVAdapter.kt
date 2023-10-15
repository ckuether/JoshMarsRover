package com.example.joshmarsrover.ui.rovers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.data.model.photoCount
import com.example.joshmarsrover.databinding.ViewHolderRoverBinding
import com.squareup.picasso.BuildConfig
import com.squareup.picasso.Picasso

class RoversRVAdapter(val viewModel: RoversViewModel): RecyclerView.Adapter<RoverViewHolder>() {

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

        if(rover.photos != null){
            val firstPhoto = rover.photos!![0]
            Picasso.get().load(firstPhoto.img_src).into(holder.b.mainPhotoIv)
        }
    }

}

class RoverViewHolder(val b: ViewHolderRoverBinding): RecyclerView.ViewHolder(b.root)