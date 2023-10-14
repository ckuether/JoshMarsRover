package com.example.joshmarsrover.ui.rovers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joshmarsrover.data.model.Rover
import com.example.joshmarsrover.databinding.ViewHolderRoverBinding

class RoversRVAdapter(val rovers: List<Rover>): RecyclerView.Adapter<RoverViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverViewHolder {
        val binding = ViewHolderRoverBinding.inflate(LayoutInflater.from(parent.context))
        return RoverViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return rovers.size
    }

    override fun onBindViewHolder(holder: RoverViewHolder, position: Int) {
        val rover = rovers[position]
        holder.b.nameText.text = rover.name
    }

}

class RoverViewHolder(val b: ViewHolderRoverBinding): RecyclerView.ViewHolder(b.root)