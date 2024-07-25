package ru.alexandrorlov.ideaplatformtest.ui.component.dialog

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.alexandrorlov.ideaplatformtest.R
import ru.alexandrorlov.ideaplatformtest.ui.theme.BackgroundDialog
import ru.alexandrorlov.ideaplatformtest.ui.theme.Black
import ru.alexandrorlov.ideaplatformtest.ui.theme.IconDialog
import ru.alexandrorlov.ideaplatformtest.ui.theme.TextDialog
import ru.alexandrorlov.ideaplatformtest.ui.theme.TypographyIdeaPlatform

@Composable
fun EditDialog(
    amount: Int,
    openDialog: MutableState<Boolean>,
    onClick: (Int) -> Unit,
    @DrawableRes
    iconId: Int,
    @StringRes
    titleId: Int,
) {
    val changeAmount: MutableState<Int> = rememberSaveable {
        mutableStateOf(amount)
    }

    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        confirmButton = {
            TextButton(
                onClick = {
                    onClick(changeAmount.value)
                    openDialog.value = false
                },
            ) {
                Text(
                    text = stringResource(id = R.string.dialog_edit_OK),
                    style = MaterialTheme.TypographyIdeaPlatform.textButtonDialog,
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                },
            ) {
                Text(
                    text = stringResource(id = R.string.dialog_edit_NO),
                    style = MaterialTheme.TypographyIdeaPlatform.textButtonDialog,
                )
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "",
            )
        },
        title = {
            Text(
                text = stringResource(id = titleId),
                modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.TypographyIdeaPlatform.textTitleDialog,
                textAlign = TextAlign.Center,
            )
        },
        text = {
            ChangeAmount(
                amount = amount,
                onChangeAmount = { changeAmount.value = it },
            )
        },
        containerColor = BackgroundDialog,
        titleContentColor = Black,
        textContentColor = TextDialog,
        iconContentColor = IconDialog,
    )
}

@Composable
private fun ChangeAmount(
    amount: Int,
    onChangeAmount: (Int) -> Unit,
){
    val changeAmount: MutableState<Int> = rememberSaveable {
        mutableStateOf(amount)
    }

    Row(
        modifier = Modifier.
        fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = {
                changeAmount.value = changeAmount.value.dec()
                onChangeAmount(changeAmount.value)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_remove_circle_outline_24),
                contentDescription = "",
                tint = IconDialog,
            )
        }

        Spacer(modifier = Modifier
            .size(dimensionResource(id = R.dimen.extra_large_padding)),
        )

        Text(
            text = changeAmount.value.toString(),
            style = MaterialTheme.TypographyIdeaPlatform.textBodyDialog,
        )

        Spacer(modifier = Modifier
            .size(dimensionResource(id = R.dimen.extra_large_padding)),
        )

        IconButton(
            onClick = {
                changeAmount.value = changeAmount.value.inc()
                onChangeAmount(changeAmount.value)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                contentDescription = "",
                tint = IconDialog,
            )
        }
    }
}