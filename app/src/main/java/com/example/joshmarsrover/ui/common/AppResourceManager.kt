package com.example.joshmarsrover.ui.common

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.joshmarsrover.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppResourceManager @Inject constructor(@ApplicationContext val context: Context) {

    val isTablet: Boolean
        get() = context.resources.getBoolean(R.bool.is_tablet)

    fun getDrawableResource(resourceId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resourceId)
    }
}