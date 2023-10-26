package com.example.joshmarsrover.ui.common.fragment

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.joshmarsrover.ui.common.activity.BaseActivity

open class BaseFragment(@LayoutRes layoutId: Int): Fragment(layoutId) {

    val isTablet: Boolean
        get() = (activity as BaseActivity).isTablet
}