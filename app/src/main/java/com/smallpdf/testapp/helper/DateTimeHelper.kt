package com.smallpdf.testapp.helper

import java.text.SimpleDateFormat
import java.util.*

object DateTimeHelper {
    const val SIMPLE_DATE_FORMAT = "dd-MM-yyyy"
    fun convertDateToStringUTC(date: Date?, format: String?): String {
        val sdf = SimpleDateFormat(format)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(date)
    }
}