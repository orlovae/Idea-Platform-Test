package ru.alexandrorlov.ideaplatformtest.ui.component.dialog

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
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
fun DeleteDialog(
    openDialog: MutableState<Boolean>,
    onClick: () -> Unit,
    @DrawableRes
    iconId: Int,
    @StringRes
    titleId: Int,
    @StringRes
    descriptionId: Int,
) {
    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        confirmButton = {
            TextButton(
                onClick = {
                    onClick()
                    openDialog.value = false
                },
            ) {
                Text(
                    text = stringResource(id = R.string.dialog_delete_OK),
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
                    text = stringResource(id = R.string.dialog_delete_NO),
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
            Text(
                text = stringResource(id = descriptionId),
                style = MaterialTheme.TypographyIdeaPlatform.textDataCard,
            )
        },
        containerColor = BackgroundDialog,
        titleContentColor = Black,
        textContentColor = TextDialog,
        iconContentColor = IconDialog,
    )
}