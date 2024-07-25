package ru.alexandrorlov.ideaplatformtest.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import ru.alexandrorlov.ideaplatformtest.R
import ru.alexandrorlov.ideaplatformtest.ui.component.SearchBar
import ru.alexandrorlov.ideaplatformtest.ui.component.TopBarMain
import ru.alexandrorlov.ideaplatformtest.ui.component.card.CardItem
import ru.alexandrorlov.ideaplatformtest.ui.models.ItemUI
import ru.alexandrorlov.ideaplatformtest.ui.models.ManipulateEvent
import ru.alexandrorlov.ideaplatformtest.ui.models.SearchEvent

@Composable
fun MainScreen(
    products: List<ItemUI>,
    onSelectedChip: (String) -> Unit,
    onClickItem: (ManipulateEvent) -> Unit,
    searchQuery: String,
    onClickSearching: (SearchEvent) -> Unit,
){
    Scaffold(
        topBar = { TopBarMain() },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            SearchBar(
                searchQuery = searchQuery,
                onClickSearching = { onClickSearching(it) },
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_padding)),
                contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.extra_small_padding)),
            ) {
                items(products) { product ->
                    CardItem(
                        product = product,
                        onClick = { onClickItem(it) },
                        onSelectedChip = { onSelectedChip(it) },
                    )
                }
            }
        }
    }
}