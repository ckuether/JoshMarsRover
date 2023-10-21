package com.example.joshmarsrover.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Camera(
    val full_name: String,
    val name: String
): Parcelable