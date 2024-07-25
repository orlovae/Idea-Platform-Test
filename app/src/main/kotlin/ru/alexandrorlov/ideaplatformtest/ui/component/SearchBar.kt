package ru.alexandrorlov.ideaplatformtest.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.alexandrorlov.ideaplatformtest.R
import ru.alexandrorlov.ideaplatformtest.ui.models.SearchEvent
import ru.alexandrorlov.ideaplatformtest.ui.theme.Black
import ru.alexandrorlov.ideaplatformtest.ui.theme.TypographyIdeaPlatform
import ru.alexandrorlov.ideaplatformtest.ui.theme.White

@Composable
fun SearchBar(
    searchQuery: String,
    onClickSearching: (SearchEvent) -> Unit,
) {
    TextField(
        value = searchQuery,
        onValueChange = {
            onClickSearching(
                SearchEvent.ContentSearchQuery(it)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.extra_small_padding)),
        textStyle = MaterialTheme.TypographyIdeaPlatform.textSearchBar,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_placeholder),
                style = MaterialTheme.TypographyIdeaPlatform.textPlaceholderSearchBar,
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.medium_padding)),
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "",
                tint = Black,
            )
        },
        trailingIcon = {
            if (searchQuery.isNotBlank()) {
                IconButton(
                    onClick = {
                        onClickSearching(
                            SearchEvent.EraseSearchQuery)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close_24),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.medium_padding)),
                        tint = Black,
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Black,
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            disabledContainerColor = White,
            errorContainerColor = White,
            focusedPlaceholderColor = Color.Green,
            cursorColor = Black,
            errorCursorColor = Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        ),
    )
}