package com.example.joshmarsrover.data.model

import com.example.joshmarsrover.common.DateFormat
import com.example.joshmarsrover.common.standardToFormattedDateString

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
    val camerasCount: Int
        get() = cameras.size

    val camerasAvailableString
        get() = "Cameras Available: $camerasCount"

    val photoCount: Int
        get() = photos?.size ?: 0

    val photoCountString
        get() = "Photo Count: $photoCount"

    val firstPhoto: Photo?
        get() = if(photoCount > 0) photos!![0] else null

    val formattedLaunchDate: String
        get() = launch_date.standardToFormattedDateString(DateFormat.STANDARD_FORMAT)

    val launchDateString: String
        get() = "Launch: $formattedLaunchDate"

    val formattedLandingDate: String
        get() = landing_date.standardToFormattedDateString(DateFormat.STANDARD_FORMAT)

    val landingDateString: String
        get() = "Landing: $formattedLandingDate"
}
