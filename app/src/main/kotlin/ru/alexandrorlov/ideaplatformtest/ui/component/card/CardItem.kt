package ru.alexandrorlov.ideaplatformtest.ui.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import ru.alexandrorlov.ideaplatformtest.R
import ru.alexandrorlov.ideaplatformtest.ui.component.Chip
import ru.alexandrorlov.ideaplatformtest.ui.component.dialog.DeleteDialog
import ru.alexandrorlov.ideaplatformtest.ui.component.dialog.EditDialog
import ru.alexandrorlov.ideaplatformtest.ui.models.ItemUI
import ru.alexandrorlov.ideaplatformtest.ui.models.ManipulateEvent
import ru.alexandrorlov.ideaplatformtest.ui.theme.Black
import ru.alexandrorlov.ideaplatformtest.ui.theme.IconDelete
import ru.alexandrorlov.ideaplatformtest.ui.theme.IconEdit
import ru.alexandrorlov.ideaplatformtest.ui.theme.TypographyIdeaPlatform
import ru.alexandrorlov.ideaplatformtest.ui.theme.White

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardItem(
    product: ItemUI,
    onSelectedChip: (String) -> Unit,
    onClick: (ManipulateEvent) -> Unit,
){
    val openChangeAmountDialog: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(false)
    }

    val openDeleteItemDialog: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.extra_small_padding))
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_radius))),
        colors = CardColors(
            containerColor = White,
            contentColor = Black,
            disabledContainerColor = White,
            disabledContentColor = Black,
        )
    ) {
        Spacer(modifier = Modifier
            .size(dimensionResource(id = R.dimen.medium_padding)),
        )

        FirstRow(
            name = product.name,
            onClickEditIcon = { openChangeAmountDialog.value = true },
            onClickDeleteItem = { openDeleteItemDialog.value = true },
        )

        Spacer(modifier = Modifier
            .size(dimensionResource(id = R.dimen.medium_padding)),
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.medium_padding),
                ),
        ) {
            product.tags.forEach { item ->
                Chip(
                    title = item,
                    onSelected = {
                        onSelectedChip(it)
                    },
                )
                Spacer(modifier = Modifier
                    .size(dimensionResource(id = R.dimen.extra_small_padding)),
                )
            }
        }

        Spacer(modifier = Modifier
            .size(dimensionResource(id = R.dimen.medium_padding)),
        )

        LastRow(
            amount = product.amount.toString(),
            time = product.time,
        )

        Spacer(modifier = Modifier
            .size(dimensionResource(id = R.dimen.medium_padding)),
        )
    }

    if (openChangeAmountDialog.value) {
        EditDialog(
            amount = product.amount,
            openDialog = openChangeAmountDialog,
            onClick = {
                onClick(
                    ManipulateEvent.ChangeAmountItem(
                        item = product,
                        changeAmount = it,
                    )
                )
            },
            iconId = R.drawable.baseline_settings_24,
            titleId = R.string.dialog_edit_amount,
        )
    }

    if (openDeleteItemDialog.value) {
        DeleteDialog(
            openDialog = openDeleteItemDialog,
            onClick = {
                onClick(
                    ManipulateEvent.DeleteItem(id = product.id)
                )
            },
            iconId = R.drawable.baseline_warning_24,
            titleId = R.string.dialog_delete_title,
            descriptionId = R.string.dialog_delete_description,
        )
    }
}

@Composable
private fun FirstRow(
    name: String,
    onClickEditIcon: () -> Unit,
    onClickDeleteItem: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.medium_padding)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .wrapContentWidth(),
            text = name,
            style = MaterialTheme.TypographyIdeaPlatform.titleCard,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )

        Row(
            modifier = Modifier
                .wrapContentWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_edit_24),
                contentDescription = "",
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        onClickEditIcon()
                    },
                tint = IconEdit,
            )

            Spacer(modifier = Modifier
                .size(dimensionResource(id = R.dimen.extra_small_padding)),
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "",
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        onClickDeleteItem()
                    },
                tint = IconDelete,
            )
        }

    }
}

@Composable
private fun LastRow(
    amount: String,
    time: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.medium_padding)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            Text(
                text = stringResource(id = R.string.card_item_amount),
                style = MaterialTheme.TypographyIdeaPlatform.textDescriptionCard,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

            Spacer(modifier = Modifier
                .size(dimensionResource(id = R.dimen.medium_padding)),
            )

            Text(
                text = amount,
                style = MaterialTheme.TypographyIdeaPlatform.textDataCard,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }

        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            Text(
                text = stringResource(id = R.string.card_item_date),
                style = MaterialTheme.TypographyIdeaPlatform.textDescriptionCard,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

            Spacer(modifier = Modifier
                .size(dimensionResource(id = R.dimen.medium_padding)),
            )

            Text(
                text = time,
                style = MaterialTheme.TypographyIdeaPlatform.textDataCard,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }

    }
}