package com.example.joshmarsrover.ui.common.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.joshmarsrover.ui.common.AppResourceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity: AppCompatActivity() {

    @Inject protected lateinit var appResourceManager: AppResourceManager

    val isTablet: Boolean
        get() = appResourceManager.isTablet

    val isPhone: Boolean
        get() = !isTablet
}