package com.example.joshmarsrover.ui.common

import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class DatePickerManager @Inject constructor(private val fragmentManager: FragmentManager) {

    companion object {
        const val TAG_DATE_PICKER = "DATE_PICKER"
    }

    val datePicker: MaterialDatePicker<Long> by lazy {
        MaterialDatePicker.Builder.datePicker().build()
    }

    fun showDatePicker(){
        datePicker.show(fragmentManager, TAG_DATE_PICKER)
    }
}