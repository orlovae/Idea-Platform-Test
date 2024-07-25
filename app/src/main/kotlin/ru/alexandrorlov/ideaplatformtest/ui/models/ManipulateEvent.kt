package ru.alexandrorlov.ideaplatformtest.ui.models

sealed class ManipulateEvent {
    data object Init : ManipulateEvent()
    data class DeleteItem(val id: Long) : ManipulateEvent()
    data class ChangeAmountItem(
        val item: ItemUI,
        val changeAmount: Int,
    ) : ManipulateEvent()
}