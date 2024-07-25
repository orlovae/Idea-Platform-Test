package ru.alexandrorlov.ideaplatformtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import ru.alexandrorlov.ideaplatformtest.common.models.ScreenState
import ru.alexandrorlov.ideaplatformtest.ui.screen.MainScreen
import ru.alexandrorlov.ideaplatformtest.ui.theme.IdeaPlatformTestTheme
import ru.alexandrorlov.ideaplatformtest.ui.viewmodel.MainViewModel
import ru.alexandrorlov.ideaplatformtest.ui.viewmodel.ManipulateItemViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var manipulateItemViewModel: ManipulateItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            IdeaPlatformTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state = mainViewModel.state.collectAsState()
                    val stateSearch = mainViewModel.querySearch.collectAsState()

                    when (val data = state.value) {
                        ScreenState.Loading -> {  }

                        is ScreenState.Content -> {
                            MainScreen(
                                products = data.content,
                                onSelectedChip = {  },
                                onClickItem = {
                                    manipulateItemViewModel.observeManipulateEvent.tryEmit(it)
                                },
                                searchQuery = stateSearch.value,
                                onClickSearching = {
                                    mainViewModel.querySearchMainScreen.tryEmit(it)
                                },
                            )
                        }

                        is ScreenState.Error -> {  }
                    }
                }
            }
        }
    }
}
