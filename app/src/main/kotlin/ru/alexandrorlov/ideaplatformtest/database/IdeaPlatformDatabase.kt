package ru.alexandrorlov.ideaplatformtest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.alexandrorlov.ideaplatformtest.database.dao.ItemDao
import ru.alexandrorlov.ideaplatformtest.database.models.ItemDBO
import ru.alexandrorlov.ideaplatformtest.database.utils.Converters

@Database(entities = [ItemDBO::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        const val DB_NAME = "idea_platform.db"
    }

}
