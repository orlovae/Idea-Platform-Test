package ru.alexandrorlov.ideaplatformtest.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.alexandrorlov.ideaplatformtest.R
import ru.alexandrorlov.ideaplatformtest.ui.theme.Black
import ru.alexandrorlov.ideaplatformtest.ui.theme.TypographyIdeaPlatform

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMain(){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.title_main_topbar),
                style = MaterialTheme.TypographyIdeaPlatform.titleTopBar,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Black,
        ),
    )
}