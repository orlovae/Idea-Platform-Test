package ru.alexandrorlov.ideaplatformtest.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.alexandrorlov.ideaplatformtest.database.AppDatabase
import ru.alexandrorlov.ideaplatformtest.database.AppDatabase.Companion.DB_NAME
import ru.alexandrorlov.ideaplatformtest.database.dao.ItemDao
import ru.alexandrorlov.ideaplatformtest.di.AppScope

@Module
class DatabaseModule {

    @[Provides AppScope]
    fun localSource(database: AppDatabase): ItemDao =
        database.itemDao()


    @[Provides AppScope]
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME,
        )
            .createFromAsset("data.db")
            .build()
}
