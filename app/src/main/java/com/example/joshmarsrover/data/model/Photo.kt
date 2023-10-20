package com.example.joshmarsrover.data.model

import android.os.Parcel
import android.os.Parcelable


data class Photo(
    val camera: Camera,
    val earth_date: String,
    val id: Int,
    val img_src: String,
    val sol: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Camera::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(camera, flags)
        parcel.writeString(earth_date)
        parcel.writeInt(id)
        parcel.writeString(img_src)
        parcel.writeInt(sol)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}