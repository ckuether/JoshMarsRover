package com.example.joshmarsrover.ui.rovers.rover_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.joshmarsrover.R
import com.example.joshmarsrover.data.model.Photo
import com.example.joshmarsrover.databinding.ViewPhotoBinding
import com.squareup.picasso.Picasso

class RoverPhotosGridAdapter(private val photos: List<Photo>): BaseAdapter() {

    override fun getCount(): Int {
        return photos.size
    }

    override fun getItem(position: Int): Any {
        return photos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val photo = photos[position]
        val binding = ViewPhotoBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

        Picasso.get().load(photo.img_src)
            .placeholder(R.drawable.default_image_placeholder)
            .fit()
            .into(binding.photoIv)

        return binding.root
    }
}