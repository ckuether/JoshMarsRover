package com.example.joshmarsrover.data.model

data class Rover(
    val cameras: List<Camera>,
    val id: Int,
    val landing_date: String,
    val launch_date: String,
    val max_date: String,
    val max_sol: Int,
    val name: String,
    val status: String,
    val total_photos: Int,
    var photos: List<Photo>?
){
    val photoCount
        get() = photos?.size ?: 0

    val firstPhoto: Photo?
        get() = if(photoCount > 0) photos!![0] else null

    val firstPhotoImageUri: String?
        get() = firstPhoto?.img_src
}
