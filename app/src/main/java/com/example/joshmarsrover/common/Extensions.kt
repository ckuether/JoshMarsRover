package com.example.joshmarsrover.common

import android.content.Context
import android.widget.Toast
import com.example.joshmarsrover.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

enum class DateFormat(val value: String){
    NETWORK_FORMAT("yyyy-MM-dd"),
    OUTPUT_FORMAT("MM/dd/yyyy")
}

fun Long.datePickerValueToDate(): Date{
    val timeZoneUTC = TimeZone.getDefault()
    val offsetFromUTC = timeZoneUTC.getOffset(Date().time) * -1
    return Date(this + offsetFromUTC)
}

fun String.toDate(inputFormat: DateFormat): Date? = try {
    val parser = getSimpleDateFormat(inputFormat)
    parser.parse(this)
}catch (e: Exception){
    e.printStackTrace()
    null
}

fun Date.toFormattedString(format: DateFormat = DateFormat.OUTPUT_FORMAT): String{
    val dateFormat =  getSimpleDateFormat(format)
    return dateFormat.format(this)
}

private fun getSimpleDateFormat(format: DateFormat): SimpleDateFormat{
    return SimpleDateFormat(format.value, Locale.getDefault())
}

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}