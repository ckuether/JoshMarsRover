package com.example.joshmarsrover.data.model

import android.os.Parcelable
import com.example.joshmarsrover.common.DateFormat
import com.example.joshmarsrover.common.toDate
import com.example.joshmarsrover.common.toFormattedString
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
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
): Parcelable {
    val maxDate: Date?
        get() = max_date.toDate(DateFormat.NETWORK_FORMAT)

    val formattedTotalPhotos: String
        get() = String.format("%,d", total_photos)

    val camerasCount: Int
        get() = cameras.size

    val camerasAvailableString
        get() = "Cameras Available: $camerasCount"

    val photoCount: Int
        get() = photos?.size ?: 0

    val photoCountString
        get() = "Photo Count: $formattedTotalPhotos"

    val firstPhoto: Photo?
        get() = if(photoCount > 0) photos!![0] else null

    private val launchDate: Date?
        get() = launch_date.toDate(DateFormat.NETWORK_FORMAT)

    private val formattedLaunchDate: String
        get() = launchDate?.toFormattedString() ?: ""

    val launchDateDescription: String
        get() = "Launch: $formattedLaunchDate"

    private val landingDate: Date?
        get() = landing_date.toDate(DateFormat.NETWORK_FORMAT)

    private val formattedLandingDate: String
        get() = landingDate?.toFormattedString() ?: ""

    val landingDateDescription: String
        get() = "Landing: $formattedLandingDate"
}
