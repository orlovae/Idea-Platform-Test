package ru.alexandrorlov.ideaplatformtest.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.alexandrorlov.ideaplatformtest.database.models.ItemDBO

@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getAll(): Flow<List<ItemDBO>>

    @Query("SELECT * FROM item WHERE name LIKE '%' || :query || '%'")
    fun getSearchingItem(query: String): Flow<List<ItemDBO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ItemDBO)

    @Query("DELETE FROM item WHERE id LIKE '%' || :id || '%'")
    suspend fun removeItem(id: Long)
}