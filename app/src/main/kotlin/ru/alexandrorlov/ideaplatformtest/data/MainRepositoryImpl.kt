package ru.alexandrorlov.ideaplatformtest.data

import kotlinx.coroutines.flow.Flow
import ru.alexandrorlov.ideaplatformtest.database.dao.ItemDao
import ru.alexandrorlov.ideaplatformtest.database.models.ItemDBO
import ru.alexandrorlov.ideaplatformtest.di.AppScope
import ru.alexandrorlov.ideaplatformtest.domain.repository.MainRepository
import javax.inject.Inject

@AppScope
class MainRepositoryImpl @Inject constructor(
    private val localSource: ItemDao,
): MainRepository {

    override fun getAll(): Flow<List<ItemDBO>> =
        localSource.getAll()

    override fun getSearching(query: String): Flow<List<ItemDBO>> =
        localSource.getSearchingItem(query)

    override suspend fun insertItem(item: ItemDBO) =
        localSource.insertItem(item)

    override suspend fun removeItem(id: Long) =
        localSource.removeItem(id)
}