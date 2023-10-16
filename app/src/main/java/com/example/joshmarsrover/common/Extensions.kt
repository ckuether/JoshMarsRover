package com.example.joshmarsrover.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

enum class DateFormat(val value: String){
    NETWORK_FORMAT("yyyy-MM-dd"),
    OUTPUT_FORMAT("MM/dd/yyyy")
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