package ru.alexandrorlov.ideaplatformtest.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography()

data class TypographyIdeaPlatform(
    val titleTopBar: TextStyle = TextStyle(
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
    ),

    val titleCard: TextStyle = TextStyle(
        fontWeight = FontWeight.W800,
        fontSize = 18.sp,
    ),

    val textChipsCard: TextStyle = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 12.sp,
    ),

    val textDescriptionCard: TextStyle = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
    ),

    val textDataCard: TextStyle = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
    ),

    val textSearchBar: TextStyle = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
    ),

    val textPlaceholderSearchBar: TextStyle = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        color = TextPlaceholderSearchBar,
    ),

    val textButtonDialog: TextStyle = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        color = TextButtonDialog,
    ),

    val textTitleDialog: TextStyle = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
    ),

    val textBodyDialog: TextStyle = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
    ),
)

val LocalTextStyle = staticCompositionLocalOf { TypographyIdeaPlatform() }

val MaterialTheme.TypographyIdeaPlatform
    @Composable
    @ReadOnlyComposable
    get() = LocalTextStyle.current