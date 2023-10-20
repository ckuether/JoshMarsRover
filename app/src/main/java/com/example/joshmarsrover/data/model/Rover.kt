package com.example.joshmarsrover.data.model

import android.os.Parcel
import android.os.Parcelable
import com.example.joshmarsrover.common.DateFormat
import com.example.joshmarsrover.common.toDate
import com.example.joshmarsrover.common.toFormattedString
import java.util.Date

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
    val maxDate: Date?
        get() = max_date.toDate(DateFormat.NETWORK_FORMAT)

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

    private val launchDate: Date?
        get() = launch_date.toDate(DateFormat.NETWORK_FORMAT)

    private val formattedLaunchDate: String
        get() = launchDate?.toFormattedString() ?: ""

    val launchDateDescription: String
        get() = "Launch: $formattedLaunchDate"

    private val landingDate: Date?
        get() = launch_date.toDate(DateFormat.NETWORK_FORMAT)

    private val formattedLandingDate: String
        get() = landingDate?.toFormattedString() ?: ""

    val landingDateDescription: String
        get() = "Landing: $formattedLandingDate"

    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Camera)!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.createTypedArrayList(Photo.CREATOR)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(cameras)
        parcel.writeInt(id)
        parcel.writeString(landing_date)
        parcel.writeString(launch_date)
        parcel.writeString(max_date)
        parcel.writeInt(max_sol)
        parcel.writeString(name)
        parcel.writeString(status)
        parcel.writeInt(total_photos)
        parcel.writeTypedList(photos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rover> {
        override fun createFromParcel(parcel: Parcel): Rover {
            return Rover(parcel)
        }

        override fun newArray(size: Int): Array<Rover?> {
            return arrayOfNulls(size)
        }
    }

}
