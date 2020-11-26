package com.example.anderson.eventsproject.app.util

import java.text.SimpleDateFormat
import java.util.*

object FormatDate {
    fun formatDate(date: Long): String{
        val date = Date(date)
        val format = SimpleDateFormat("EEE, dd MMMM yyyy HH:mm")
        return format.format(date)
    }
}