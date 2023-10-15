package com.example.joshmarsrover.ui.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class DateFormat(val value: String){
    STANDARD_FORMAT("yyyy-MM-dd"),
    USER_FORMAT("MM/dd/yyyy")
}

fun String.standardToFormattedDateString(
    inputFormat: DateFormat,
    outputFormat: DateFormat = DateFormat.USER_FORMAT
): String = try {
    val inputDate = this.toDate(inputFormat)!!
    inputDate.toFormattedString(outputFormat)
}catch (e: java.lang.Exception){
    e.printStackTrace()
    ""
}

fun String.toDate(dateFormat: DateFormat): Date? = try {
    val parser = SimpleDateFormat(dateFormat.value, Locale.getDefault())
    parser.parse(this)
}catch (e: Exception){
    e.printStackTrace()
    null
}

fun Date.toFormattedString(format: DateFormat): String{
    val dateFormat = SimpleDateFormat(format.value)
    return dateFormat.format(this)
}