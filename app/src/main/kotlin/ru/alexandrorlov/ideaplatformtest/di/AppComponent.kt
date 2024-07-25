package ru.alexandrorlov.ideaplatformtest.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.alexandrorlov.ideaplatformtest.MainActivity
import ru.alexandrorlov.ideaplatformtest.database.di.DatabaseModule

@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
    ]
)
@AppScope
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    val factory: MultiViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context,
        ): AppComponent
    }
}