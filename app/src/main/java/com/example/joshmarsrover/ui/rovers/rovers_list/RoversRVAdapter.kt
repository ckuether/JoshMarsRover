package com.example.joshmarsrover.ui.rovers.rovers_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joshmarsrover.R
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.databinding.ViewHolderRoverBinding
import com.example.joshmarsrover.ui.rovers.RoversViewModel
import com.squareup.picasso.Picasso


class RoversRVAdapter(private val viewModel: RoversViewModel,
                      private val callback: RoversCallback): RecyclerView.Adapter<RoverViewHolder>() {

    interface RoversCallback {
        fun getPhotos(pos: Int)
        fun onRoverSelected(rover: Rover)

    }

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
            callback.getPhotos(position)
        }

        Picasso.get().load(rover.firstPhoto?.img_src)
            .placeholder(R.drawable.default_image_placeholder)
            .fit()
            .into(holder.b.mainPhotoIv)

        holder.b.nameTv.text = rover.name
        holder.b.launchDateTv.detailsTv.text = rover.launchDateDescription
        holder.b.landingDateTv.detailsTv.text = rover.landingDateDescription
        holder.b.photoCountTv.detailsTv.text = rover.photoCountString
        holder.b.camerasAvailableTv.detailsTv.text = rover.camerasAvailableString

        holder.b.container.setOnClickListener {
            callback.onRoverSelected(rover)
        }
    }
}

class RoverViewHolder(val b: ViewHolderRoverBinding): RecyclerView.ViewHolder(b.root)