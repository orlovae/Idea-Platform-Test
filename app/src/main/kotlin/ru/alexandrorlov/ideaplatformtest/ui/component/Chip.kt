package ru.alexandrorlov.ideaplatformtest.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import ru.alexandrorlov.ideaplatformtest.R
import ru.alexandrorlov.ideaplatformtest.ui.theme.Black
import ru.alexandrorlov.ideaplatformtest.ui.theme.TypographyIdeaPlatform
import ru.alexandrorlov.ideaplatformtest.ui.theme.White

@Composable
fun Chip(
    title: String,
    onSelected: (String) -> Unit,
) {
    var selected by rememberSaveable { mutableStateOf(false) }

    FilterChip(
        onClick = {
            selected = selected.not()
            if (selected) {
                onSelected(title)
            }
        },
        label = {
            Text(
                text = title,
                style = MaterialTheme.TypographyIdeaPlatform.textChipsCard,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        },
        selected = selected,
        enabled = true,
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.small_thickness),
            color = Black,
        ),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = White,
            selectedContainerColor = Black,
            labelColor = Black,
            selectedLabelColor = White,
        ),
    )

    Spacer(modifier = Modifier
        .size(dimensionResource(id = R.dimen.x_x_small_padding)),
    )
}
