package com.example.joshmarsrover.data.model

import android.os.Parcel
import android.os.Parcelable
import com.example.joshmarsrover.ui.common.DateFormat
import com.example.joshmarsrover.ui.common.standardToFormattedDateString

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
): Parcelable{
    val camerasCount
        get() = cameras.size

    val photoCount
        get() = photos?.size ?: 0

    val firstPhoto: Photo?
        get() = if(photoCount > 0) photos!![0] else null

    val firstPhotoImageUri: String?
        get() = firstPhoto?.img_src

    val formattedLaunchDate: String
        get() = launch_date.standardToFormattedDateString(DateFormat.STANDARD_FORMAT)

    val formattedLandingDate: String
        get() = landing_date.standardToFormattedDateString(DateFormat.STANDARD_FORMAT)

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {

    }
}
