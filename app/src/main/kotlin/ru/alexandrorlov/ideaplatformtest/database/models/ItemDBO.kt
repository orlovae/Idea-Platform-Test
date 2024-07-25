package ru.alexandrorlov.ideaplatformtest.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ItemDBO(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("time")
    val timestamp: Long,
    @ColumnInfo("tags")
    val tags: List<String>,
    @ColumnInfo("amount")
    val amount: Int,
)
