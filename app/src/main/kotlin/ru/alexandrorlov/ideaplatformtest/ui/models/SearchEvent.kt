package ru.alexandrorlov.ideaplatformtest.ui.models

sealed class SearchEvent {
    data object EraseSearchQuery : SearchEvent()
    data class ContentSearchQuery(val searchQuery: String) : SearchEvent()
}