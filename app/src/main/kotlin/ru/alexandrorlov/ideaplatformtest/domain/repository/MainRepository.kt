package ru.alexandrorlov.ideaplatformtest.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.alexandrorlov.ideaplatformtest.database.models.ItemDBO

interface MainRepository {

    fun getAll(): Flow<List<ItemDBO>>

    fun getSearching(query: String): Flow<List<ItemDBO>>

    suspend fun insertItem(item: ItemDBO)

    suspend fun removeItem(id: Long)
}