package com.example.joshmarsrover.ui.common

import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.scopes.ActivityScoped
import java.util.Date
import javax.inject.Inject

@ActivityScoped
class DatePickerManager @Inject constructor(private val fragmentManager: FragmentManager) {

    companion object {
        const val TAG_DATE_PICKER = "DATE_PICKER"
    }

    fun createDatePicker(maxDateConstraint: Date? = null): MaterialDatePicker<Long> {
        val constraintBuilder = CalendarConstraints.Builder()
        if(maxDateConstraint != null)
            constraintBuilder.setEnd(maxDateConstraint.time)

        return MaterialDatePicker.Builder.datePicker()
            .setCalendarConstraints(constraintBuilder.build())
            .build()
    }

    fun showDatePicker(picker: MaterialDatePicker<Long>){
        picker.show(fragmentManager, TAG_DATE_PICKER)
    }
}