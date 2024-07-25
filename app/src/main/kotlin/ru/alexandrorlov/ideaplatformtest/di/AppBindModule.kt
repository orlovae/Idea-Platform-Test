package ru.alexandrorlov.ideaplatformtest.di

import dagger.Binds
import dagger.Module
import ru.alexandrorlov.ideaplatformtest.data.MainRepositoryImpl
import ru.alexandrorlov.ideaplatformtest.domain.repository.MainRepository

@Module
interface AppBindModule {

    @Binds
    fun bindMainRepositoryImplToMainRepository(
        mainRepositoryImpl: MainRepositoryImpl,
    ): MainRepository
}