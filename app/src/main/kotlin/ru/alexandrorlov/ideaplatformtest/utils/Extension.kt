package ru.alexandrorlov.ideaplatformtest.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Long.convertDateToString(): String {
    val date: Date = Date(this)
    val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return simpleDateFormat.format(date)
}

fun String.convertDateToLong(): Long {
    val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return simpleDateFormat.parse(this)?.time ?: throw IllegalArgumentException("Input Date not valid")
}