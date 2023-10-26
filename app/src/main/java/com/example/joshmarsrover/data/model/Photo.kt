package com.example.joshmarsrover.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Photo(
    val camera: Camera,
    val earth_date: String,
    val id: Int,
    val img_src: String,
    val sol: Int
): Parcelable