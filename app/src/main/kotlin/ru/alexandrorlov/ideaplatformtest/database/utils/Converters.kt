package ru.alexandrorlov.ideaplatformtest.database.utils

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun listToString(list: List<String>) : String = list.joinToString(",")

    @TypeConverter
    fun stringToList(string: String) : List<String> = string
        .replace("\\S+", " ")
        .replace("[", "")
        .replace("]", "")
        .replace("\"", "")
        .split(",")
        .filter { it.isNotBlank() }
}