package ru.alexandrorlov.ideaplatformtest.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.alexandrorlov.ideaplatformtest.domain.repository.MainRepository
import ru.alexandrorlov.ideaplatformtest.ui.viewmodel.MainViewModel
import ru.alexandrorlov.ideaplatformtest.ui.viewmodel.ManipulateItemViewModel

@Module(includes = [
    AppBindModule::class,
])
class AppModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Provides
    fun provideMainViewModel(mainRepository: MainRepository): ViewModel =
        MainViewModel(mainRepository)

    @IntoMap
    @ViewModelKey(ManipulateItemViewModel::class)
    @Provides
    fun provideManipulateViewModel(mainRepository: MainRepository): ViewModel =
        ManipulateItemViewModel(mainRepository)
}