package ru.alexandrorlov.ideaplatformtest.ui.models


data class ItemUI(
    val id: Long,
    val name: String,
    val time: String,
    val tags: List<String>,
    val amount: Int,
)
