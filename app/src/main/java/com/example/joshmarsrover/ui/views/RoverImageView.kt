package com.example.joshmarsrover.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.floor

class RoverImageView: AppCompatImageView {

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    companion object {
        private const val ROVER_ASPECT_RATIO: Double = .574
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        val height = floor(width * ROVER_ASPECT_RATIO).toInt()
        setMeasuredDimension(width, height)
    }
}